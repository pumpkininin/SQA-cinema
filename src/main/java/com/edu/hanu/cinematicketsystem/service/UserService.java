package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.dto.UserDTO;
import com.edu.hanu.cinematicketsystem.exception.InvalidPasswordException;
import com.edu.hanu.cinematicketsystem.exception.UserNotFoundException;
import com.edu.hanu.cinematicketsystem.model.Role;
import com.edu.hanu.cinematicketsystem.model.User;
import com.edu.hanu.cinematicketsystem.payload.ChangePassword;
import com.edu.hanu.cinematicketsystem.payload.SignUpRequest;
import com.edu.hanu.cinematicketsystem.repository.RoleRepository;
import com.edu.hanu.cinematicketsystem.repository.UserRepository;
import com.edu.hanu.cinematicketsystem.response.UserResponse;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.bouncycastle.openssl.PasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

  private final Logger log = LoggerFactory.getLogger(UserService.class);
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public Optional<UserDTO> updateUser(UserDTO userDTO) {
    return Optional
        .of(userRepository.findById(userDTO.getId()))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(user -> {
          user.setUsername(userDTO.getUsername().toLowerCase());
          user.setFullname(userDTO.getFullname());
          Set<Role> managedAuthorities = user.getRoleSet();
          managedAuthorities.clear();
          userDTO
              .getRoleSet()
              .stream()
              .map(roleRepository::findByRole)
              .filter(Optional::isPresent)
              .map(Optional::get)
              .forEach(managedAuthorities::add);
          log.debug("Changed Information for User: {}", user);
          return user;
        })
        .map(UserDTO::new);
  }

  public void deleteUser(String login) {
    userRepository
        .findByUsername(login)
        .ifPresent(user -> {
          userRepository.delete(user);
          log.debug("Deleted User: {}", user);
        });
  }


  public User findByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    return user.get();
  }

  public boolean isExistsByUsername(String username) {
    return userRepository.findByUsername(username).isPresent();
  }

  public User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
    User newUser = new User();
    newUser.setUsername(signUpRequest.getUsername());
    newUser.setPassword(signUpRequest.getPassword());
    newUser.setFullname(signUpRequest.getFullname());
    return newUser;
  }

  public User signUp(User user) {
    return userRepository.save(user);
  }

  public UserResponse getProfile(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    UserResponse response = new UserResponse();
    user.ifPresentOrElse(
        user1 -> {
          response.setFullname(user1.getFullname());
          response.setRoles(
              user1.getRoleSet().stream().map(Role::getRole).collect(Collectors.toList()));
          response.setUsername(username);
        },
        () -> {
        }
    );
    return response;
  }

  public void changePassword(String username, ChangePassword changePassword)
      throws PasswordException {
    User user = findByUsername(username);
    boolean isMatch = passwordEncoder.matches(changePassword.getCurrentPass(), user.getPassword());
    if (isMatch){
      user.setPassword(passwordEncoder.encode(changePassword.getNewPass()));
      userRepository.save(user);
    }else{
      throw new PasswordException("Your entered password is not correct");
    }
  }

  public void updateProfile(String username, String fullName) {
    Optional<User> user = userRepository.findByUsername(username);
    user.ifPresentOrElse(user1 -> {
      user1.setFullname(fullName);
      userRepository.save(user1);
    }, () -> {
      throw new UserNotFoundException();
    });
  }

}

