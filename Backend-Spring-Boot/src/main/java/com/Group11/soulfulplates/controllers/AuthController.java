package com.Group11.SoulfulPlates.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.Group11.SoulfulPlates.payload.request.ForgetPasswordRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Group11.SoulfulPlates.models.ERole;
import com.Group11.SoulfulPlates.models.Role;
import com.Group11.SoulfulPlates.models.User;
import com.Group11.SoulfulPlates.payload.request.LoginRequest;
import com.Group11.SoulfulPlates.payload.request.SignupRequest;
import com.Group11.SoulfulPlates.payload.response.JwtResponse;
import com.Group11.SoulfulPlates.payload.response.MessageResponse;
import com.Group11.SoulfulPlates.repository.RoleRepository;
import com.Group11.SoulfulPlates.repository.UserRepository;
import com.Group11.SoulfulPlates.security.jwt.JwtUtils;
import com.Group11.SoulfulPlates.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(Object::toString)
            .collect(Collectors.toList());

    JwtResponse jwtResponse = new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles);

    return ResponseEntity.ok(new MessageResponse(1, "User authenticated successfully!", jwtResponse));
  }



  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse(-1, "Error: Username is already taken!", null));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse(-1, "Error: Email is already in use!", null));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(buyerRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "seller":
            Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(sellerRole);

            break;
          default:
            Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(buyerRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse(1, "User registered successfully!", null));
  }

  @PostMapping("/forget-password")
  @PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> generateForgetPasswordCode(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {

    if (userRepository.existsByEmail(forgetPasswordRequest.getEmail())) {
      try {
        String generatedCode = generateRandomCode();
        return ResponseEntity.ok(new MessageResponse(1, "Forget password code generated successfully!", new HashMap<String, Object>() {{
          put("OTP_Code", generatedCode);
        }}));
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(new MessageResponse(-1, "Error occurred while generating forget password code.", null));
      }
    }else{
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse(-1, "Error: Email does not exists!", null));
    }


  }

  private String generateRandomCode() {
    Random random = new Random();
    int code = 1000 + random.nextInt(9000); // Random 4-digit code
    return String.valueOf(code);
  }

}
