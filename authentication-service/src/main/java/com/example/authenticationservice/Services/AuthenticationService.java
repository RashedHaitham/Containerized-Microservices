package com.example.authenticationservice.Services;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.authenticationservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.authenticationservice.Repository.UserRepository;
import com.auth0.jwt.JWT;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    private static final String SECRET_KEY = "This is a secret";
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return Objects.equals(user.getPassword(), password);
    }

    public  String generateAccessToken(User user) {
        Algorithm secureAlgorithm = Algorithm.HMAC512(SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 minutes
                .sign(secureAlgorithm);
    }

    public boolean validateToken(String token) {
        try {
            Algorithm secureAlgorithm = Algorithm.HMAC512(SECRET_KEY.getBytes());
            JWTVerifier verifier = JWT.require(secureAlgorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
