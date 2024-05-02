package com.utkubayrak.FoodOrdering.controller;


import com.utkubayrak.FoodOrdering.business.services.CustomerUserDetailsService;
import com.utkubayrak.FoodOrdering.config.JwtProvider;
import com.utkubayrak.FoodOrdering.data.USER_ROLE;
import com.utkubayrak.FoodOrdering.data.entities.CartEntity;
import com.utkubayrak.FoodOrdering.data.entities.UserEntity;
import com.utkubayrak.FoodOrdering.data.repository.CartRepository;
import com.utkubayrak.FoodOrdering.data.repository.UserRepository;
import com.utkubayrak.FoodOrdering.payload.request.LoginRequest;
import com.utkubayrak.FoodOrdering.payload.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody UserEntity userEntity) throws Exception {
        
        UserEntity isEmailExist = userRepository.findByEmail(userEntity.getEmail());

        if (isEmailExist != null) {
            throw new Exception("Email is already used with another account");
        }
        UserEntity createdUser = new UserEntity();
        createdUser.setEmail(userEntity.getEmail());
        createdUser.setFullName(userEntity.getFullName());
        createdUser.setRole(userEntity.getRole());
        createdUser.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        UserEntity savedUser = userRepository.save(createdUser);

        CartEntity cartEntity = new CartEntity();
        cartEntity.setCustomer(savedUser);
        cartRepository.save(cartEntity);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity.getEmail(),userEntity.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register succes");
        authResponse.setRole(savedUser.getRole());


        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        Collection<?extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login succes");
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username...");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}

