package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.ERole;
import com.Group11.soulfulplates.models.Role;
import com.Group11.soulfulplates.models.Seller;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.request.ForgetPasswordRequest;
import com.Group11.soulfulplates.payload.request.LoginRequest;
import com.Group11.soulfulplates.payload.request.ResetPasswordRequest;
import com.Group11.soulfulplates.payload.request.SignupRequest;
import com.Group11.soulfulplates.payload.response.JwtResponse;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.OtpResponse;
import com.Group11.soulfulplates.repository.RoleRepository;
import com.Group11.soulfulplates.repository.SellerRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.security.jwt.JwtUtils;
import com.Group11.soulfulplates.security.services.UserDetailsImpl;
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

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")

public class AuthController {
  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
              .body(new MessageResponse(-1, "Error: Username is already taken!", null));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
              .body(new MessageResponse(-1, "Error: Email is already in use!", null));
    }

    // Create a new user
    User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()), signUpRequest.getContactNumber(), signUpRequest.getFirstname());

    Set<Role> roles = new HashSet<>();

    if (signUpRequest.getRole() == null) {
      Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(buyerRole);
    } else {
      signUpRequest.getRole().forEach(role -> {
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

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  SellerRepository sellerRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<MessageResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(Object::toString)
            .collect(Collectors.toList());


    // Fetch seller information if exists
    Optional<Seller> sellerOptional = sellerRepository.findByUser_Id(userDetails.getId());
    JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
            userDetails.getEmail(), roles, userDetails.getContactNumber(), userDetails.getFirstname(), userDetails.isNotificationFlag());

    if (sellerOptional.isPresent()) {
      Seller seller = sellerOptional.get();
      jwtResponse.setSeller(seller); // Append Seller to JwtResponse
    }
    return ResponseEntity.ok(new MessageResponse(1, "User authenticated successfully!", jwtResponse));


  }


  @PostMapping("/forget-password")
  @PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
  public ResponseEntity<MessageResponse> generateForgetPasswordCode(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
    if (userRepository.existsByEmail(forgetPasswordRequest.getEmail())) {
      try {
        return ResponseEntity.ok(new MessageResponse(1, "Forget password code generated successfully!",
                Collections.singletonMap("OTP_Code", OtpResponse.OtpCode())));
      } catch (Exception e) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse(-1, "Error occurred while generating forget password code.", null));
      }
    } else{
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse(-1, "Error: Email does not exists!", null));
    }
  }
  @PostMapping("/reset-password")
  @PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
  public ResponseEntity<MessageResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
    if (userRepository.existsByEmail(resetPasswordRequest.getEmail())) {
      try {
        Optional<User> optionalUser = userRepository.findByEmail(resetPasswordRequest.getEmail());
        if (optionalUser.isPresent()) {
          User user = optionalUser.get();
          user.setPassword(encoder.encode(resetPasswordRequest.getNewPassword()));
          userRepository.save(user);

          return ResponseEntity.ok(new MessageResponse(1, "Password reset successfully!", null));
        } else {
          return ResponseEntity.badRequest()
                  .body(new MessageResponse(-1, "Error: User with provided email not found.", null));
        }
      } catch (Exception e) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse(-1, "Error occurred while resetting password.", null));
      }

    } else {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse(-1, "Error: Email does not exist!", null));
    }
  }
}
