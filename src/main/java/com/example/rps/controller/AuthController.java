package com.example.rps.controller;

import com.example.rps.model.User;
import com.example.rps.model.LoginRequest;
import com.example.rps.model.RegisterRequest;
import com.example.rps.repository.UserRepository;
import com.example.rps.service.AuditService;
import com.example.rps.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    private final UserRepository userRepository;

    private final AuditService auditService;

    public AuthController(AuthService authService, UserRepository userRepository, AuditService auditService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.auditService = auditService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        logger.info("Register Request: {}", req.getUsername());

        if (userRepository.findByUsername(req.getUsername()).isPresent()){
            logger.warn("Register FAIL: {} ", req.getUsername());
            auditService.record("REGISTER_FAIL",req.getUsername(),"User failed trying to register");
            return ResponseEntity.badRequest().body("Register FAIL");
        }
        User created = authService.register(req.getUsername(), req.getPassword(), req.getEmail(), req.getLevel());
        logger.info("Register OK: {} ", created.getUsername());
        auditService.record("REGISTER_OK",req.getUsername(),"User register with this email:"+created.getEmail());
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        logger.info("Login Request: {}", req.getUsername());

        boolean valid = authService.login(req.getUsername(), req.getPassword());
        if (!valid){
            logger.warn("Login FAIL: {}", req.getUsername());
            auditService.record("LOGIN_FAILED",req.getUsername(),
                    "There was an attempt to log into this username: "+req.getUsername());
            return ResponseEntity.status(401).body(Map.of("error", "Login FAIL"));
        }
        logger.info("Login OK: {}", req.getUsername());
        auditService.record("LOGIN_OK",req.getUsername(),"User logged in");
        return ResponseEntity.ok("Login OK");
    }
}
