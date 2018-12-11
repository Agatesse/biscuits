package com.agathevaisse.biscuits.presentation;

import com.agathevaisse.biscuits.application.UserService;
import com.agathevaisse.biscuits.domain.authentication.messages.request.SignInForm;
import com.agathevaisse.biscuits.domain.authentication.messages.request.SignUpForm;
import com.agathevaisse.biscuits.domain.authentication.messages.response.JwtResponse;
import com.agathevaisse.biscuits.domain.authentication.messages.response.ResponseMessage;
import com.agathevaisse.biscuits.domain.authentication.user.RoleName;
import com.agathevaisse.biscuits.domain.authentication.user.User;
import com.agathevaisse.biscuits.security.jwt.JwtProvider;
import com.agathevaisse.biscuits.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getId(), userDetails.getAuthorities()));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String stringRole = signUpRequest.getRole();
        RoleName role;

            switch (stringRole) {
                case "admin":
                    role = RoleName.ROLE_ADMIN;
                    break;
                default:
                    role = RoleName.ROLE_USER;
            }

        user.setRole(role);
        userService.createUser(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @PutMapping(value = "/account/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/account/delete/{id}")
    public boolean deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    @GetMapping(value = ("/account/{username}"))
    public Optional<User> findUserByUsername(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping(value = ("/auth/getusernames"))
    public List<String> getUsernames(){
        return userService.getUsernames();
    }

    @GetMapping(value = ("/auth/getemails"))
    public List<String> getEmails(){
        return userService.getEmails();
    }
}
