package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Role;
import com.edu.hanu.cinematicketsystem.model.User;
import com.edu.hanu.cinematicketsystem.exception.InvalidPasswordException;
import com.edu.hanu.cinematicketsystem.repository.RoleRepository;
import com.edu.hanu.cinematicketsystem.repository.UserRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import com.edu.hanu.cinematicketsystem.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    

//    public User createUser(UserDTO userDTO) {
//        User user = new User();
//        user.setUsername(userDTO.getUsername().toLowerCase());
//        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
//        user.setPassword(encryptedPassword);
//        user.setResetKey(RandomUtil.generateResetKey());
//        user.setResetDate(Timestamp.from(Instant.now()));
//        user.setActivated(true);
//        if (userDTO.getRoleSet() != null) {
//            Set<Role> authorities = userDTO
//                    .getRoleSet()
//                    .stream()
//                    .map(roleRepository::findByRole)
//                    .filter(Optional::isPresent)
//                    .map(Optional::get)
//                    .collect(Collectors.toSet());
//            user.setRoleSet(authorities);
//        }
//        userRepository.save(user);
//        log.debug("Created Information for User: {}", user);
//        return user;
//    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
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
                .findOneByUsername(login)
                .ifPresent(user -> {
                    userRepository.delete(user);
                    log.debug("Deleted User: {}", user);
                });
    }



    public User findByUsername(String username) {
        Optional<User> user = userRepository.findOneByUsername(username);
        return user.get();
    }
}

