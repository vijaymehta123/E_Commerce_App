package com.myFirstProject.myFirstProject.Controller;


import com.myFirstProject.myFirstProject.DTO.LoginRequest;
import com.myFirstProject.myFirstProject.DTO.RegisterRequest;
import com.myFirstProject.myFirstProject.Repository.UserRepository;
import com.myFirstProject.myFirstProject.Service.UserService;
import com.myFirstProject.myFirstProject.entity.Users;
import com.myFirstProject.myFirstProject.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, String> login (@RequestBody LoginRequest loginRequest){

        Users user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getId());
        return Map.of("token", token);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return "Email already exists";
        }
           Users newUser = new Users();
           newUser.setName(registerRequest.getName());
           newUser.setEmail(registerRequest.getEmail());
           newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
           newUser.setRole("USER");
         Users  x=   userRepository.save(newUser);
            userService.add(x);
        return "User regsiterrd succesfully with role admin";
    }


}
