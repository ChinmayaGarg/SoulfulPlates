package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.ERole;
import com.Group11.soulfulplates.models.Role;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.request.ForgetPasswordRequest;
import com.Group11.soulfulplates.payload.request.LoginRequest;
import com.Group11.soulfulplates.payload.request.ResetPasswordRequest;
import com.Group11.soulfulplates.payload.request.SignupRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.RoleRepository;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.security.jwt.JwtUtils;
import com.Group11.soulfulplates.security.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @Mock
    JwtUtils jwtUtils;


    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private AuthController authController;
    static int expected200= 200;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        openMocks(this);

    }


    @Test
    void testRegisterUserUsernameTaken() {
        MessageResponse expectedResponse = new MessageResponse(-1, "Error: Username is already taken!", null);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("tester");
        signupRequest.setEmail("test@tester.com");
        signupRequest.setPassword("testing123");

        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        ResponseEntity<MessageResponse> responseEntity = authController.registerUser(signupRequest);

        assertEquals(ResponseEntity.badRequest().body(expectedResponse), responseEntity);
    }

    @Test
    void testRegisterUserEmailTaken() {
        MessageResponse expectedResponse = new MessageResponse(-1, "Error: Email is already in use!", null);

        when(userRepository.existsByEmail(any())).thenReturn(true);

        ResponseEntity<MessageResponse> responseEntity = authController.registerUser(new SignupRequest());

        assertEquals(ResponseEntity.badRequest().body(expectedResponse), responseEntity);
    }

    @Test
    void testRegisterSuccessful() {
        MessageResponse expectedResponse = new MessageResponse(1, "User registered successfully!", null);

        when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(ERole.ROLE_BUYER)));
        when(userRepository.save(any())).thenReturn(new User());

        ResponseEntity<MessageResponse> responseEntity = authController.registerUser(new SignupRequest());

        assertEquals(ResponseEntity.ok(expectedResponse), responseEntity);
    }

    @Test
    void testGenerateForgetPasswordCode_ValidEmail() {
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setEmail("test@test.com");

        when(userRepository.existsByEmail(any())).thenReturn(true);

        ResponseEntity<MessageResponse> responseEntity = authController.generateForgetPasswordCode(request);

        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getCode());
    }

    @Test
    void testGenerateForgetPasswordCode_InvalidEmail() {
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setEmail("invalid@test.com");

        when(userRepository.existsByEmail(any())).thenReturn(false);

        ResponseEntity<MessageResponse> responseEntity = authController.generateForgetPasswordCode(request);

        assertEquals(-1, responseEntity.getBody().getCode());
    }

    @Test
    void testGenerateForgetPasswordCode_Exception() {
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setEmail("test@test.com");

        when(userRepository.existsByEmail(any())).thenThrow(RuntimeException.class);

        ResponseEntity<MessageResponse> responseEntity = authController.generateForgetPasswordCode(request);

        assertEquals(-1, responseEntity.getBody().getCode());
    }

    @Test
    void testResetPassword_ValidEmail_NotFound() {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@test.com");
        request.setNewPassword("newPassword123");

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        ResponseEntity<MessageResponse> responseEntity = authController.resetPassword(request);

        assertEquals(-1, responseEntity.getBody().getCode());
    }

    @Test
    void testGenerateForgetPasswordCode_EmailDoesNotExist() {
        // Prepare request with a non-existing email
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setEmail("nonexisting@test.com");

        // Mock UserRepository to return false for email existence check
        when(userRepository.existsByEmail(any())).thenReturn(false);

        // Call the method under test
        ResponseEntity<MessageResponse> responseEntity = authController.generateForgetPasswordCode(request);

        // Verify the response
        assertEquals(-1, responseEntity.getBody().getCode());
        assertEquals("Error: Email does not exist!", responseEntity.getBody().getDescription());
        assertNull(responseEntity.getBody().getData());
    }



    @Test
    void testResetPassword_InvalidEmail() {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("invalid@test.com");
        request.setNewPassword("newPassword123");

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        ResponseEntity<MessageResponse> responseEntity = authController.resetPassword(request);

        assertEquals(-1, responseEntity.getBody().getCode());
    }

    @Test
    void testResetPassword_Exception() {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@test.com");
        request.setNewPassword("newPassword123");

        when(userRepository.findByEmail(any())).thenThrow(RuntimeException.class);

        ResponseEntity<MessageResponse> responseEntity = authController.resetPassword(request);

        assertEquals(-1, responseEntity.getBody().getCode());
    }

    @Test
    void testAuthenticateUser_Success() {
        String jwtToken = "fakeJwtToken";

        // Mock authentication result
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);


        // Create a mock User object
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@test.com");
        user.setPassword("encodedPassword");

        // Mock UserDetailsImpl.build method
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        // Mock JWT token generation
        JwtUtils jwtUtils = mock(JwtUtils.class);
        when(jwtUtils.generateJwtToken(any())).thenReturn(jwtToken);

        // Test login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password123");

        ResponseEntity<MessageResponse> responseEntity = authController.authenticateUser(loginRequest);

        System.out.println(responseEntity.getStatusCodeValue());


        // Verify response
        assertEquals(AuthControllerTest.expected200, responseEntity.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getCode());
    }



    @Test
    void testAuthenticateUser_Failure() {
        // Mock authentication result
        when(authenticationManager.authenticate(any()))
                .thenThrow(BadCredentialsException.class);

        // Test login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password123");

        ResponseEntity<MessageResponse> responseEntity = authController.authenticateUser(loginRequest);

        // Verify response
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCodeValue());
        assertEquals(-1, responseEntity.getBody().getCode());
        assertNull(responseEntity.getBody().getData());
    }

}
