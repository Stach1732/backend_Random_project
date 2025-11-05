package com.example.rps.service;

import com.example.rps.model.User;
import com.example.rps.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String plainPassword, String email, int level){
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        User user = new User(username, hashed, email, level);
        User saved = userRepository.save(user);

        return saved;
    }

    public boolean login(String username, String plainPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return false;
        }
        //Is good pass? valid-> true
        User user = userOpt.get();
        boolean valid = BCrypt.checkpw(plainPassword, user.getPassword());

        return valid;
    }
}
