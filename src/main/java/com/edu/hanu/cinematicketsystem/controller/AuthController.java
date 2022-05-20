package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.exception.UserNotFoundException;
import com.edu.hanu.cinematicketsystem.model.Role;
import com.edu.hanu.cinematicketsystem.model.User;
import com.edu.hanu.cinematicketsystem.payload.ApiResponse;
import com.edu.hanu.cinematicketsystem.payload.AuthResponse;
import com.edu.hanu.cinematicketsystem.payload.ChangePassword;
import com.edu.hanu.cinematicketsystem.payload.LoginRequest;
import com.edu.hanu.cinematicketsystem.payload.SignUpRequest;
import com.edu.hanu.cinematicketsystem.response.UserResponse;
import com.edu.hanu.cinematicketsystem.security.UserDetailServiceImpl;
import com.edu.hanu.cinematicketsystem.security.jwt.JWTUtil;
import com.edu.hanu.cinematicketsystem.service.RoleService;
import com.edu.hanu.cinematicketsystem.service.UserService;
import java.util.Collections;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private JWTUtil jwtUtil;


  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * @effects
   * <pre>
   *	if account is incorrect
   *		throw new exception
   *	else
   *		generate new Token and send back to client
   * </pre>
   * @param loginRequest
   * @return
   * @throws Exception
   */
  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody @Validated LoginRequest loginRequest) throws Exception{
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    }catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password",e);
    }

    final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(jwt,userDetails));
  }

  /**
   * @effects <pre>
   * 	if user input is not valid
   * 		return fail message
   * 	else
   * 		create new user and send success message
   * </pre>
   * @param signUpRequest
   * @return
   */
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
    //Validate user name
    if(userService.isExistsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity(new ApiResponse(false, "Username is already exist"),
          HttpStatus.BAD_REQUEST);
    }

    User user = userService.mapSignUpRequestToUser(signUpRequest);

    Role userRole = roleService.findByName("ROLE_STAFF");

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    user.setRoleSet(Collections.singleton(userRole));



//		user.setEnabled(true);

    //Create new cart for user


    User result = userService.signUp(user);
    return ResponseEntity.ok(result);

  }

  @PostMapping
  public ResponseEntity<UserResponse> getUserInfo(@RequestBody String token){
    String username = jwtUtil.extractUsername(token);
    return ResponseEntity.ok().body(userService.getProfile(username));
  }
  @PostMapping(value = "/changeProfile")
  public ResponseEntity changeProfile(@RequestBody String fullName, HttpServletRequest request){
    final String authorizationHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null;
    try{
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

        jwt = authorizationHeader.substring(7);
        username = jwtUtil.extractUsername(jwt);
        userService.updateProfile(username, fullName);
        return ResponseEntity.ok().body("Change success");
      }else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
      }
    }catch (UserNotFoundException e){
      return ResponseEntity.status(404).body("User incorrect");
    }
  }
  @PostMapping(value = "/changePassword")
  public ResponseEntity changePassword(@RequestBody ChangePassword changePassword, HttpServletRequest request)
      throws PasswordException {
    final String authorizationHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null;
    try{
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

        jwt = authorizationHeader.substring(7);
        username = jwtUtil.extractUsername(jwt);
        userService.changePassword(username, changePassword);
        return ResponseEntity.ok().body("Change success");
      }else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
      }
    }catch (PasswordException e){
      return ResponseEntity.status(404).body("Password incorrect");
    }


    //verify access token

  }
}