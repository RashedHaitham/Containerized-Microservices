package com.example.BookResult.Controllers;

import com.example.BookResult.Service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.example.BookResult.Model.User;

@Controller
@RequestMapping( "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletResponse response) {
        User user = new User(username, password);
        ResponseEntity<String> authResponse = authenticationService.authenticate(user);
        HttpStatus status = (HttpStatus) authResponse.getStatusCode();
        String body = authResponse.getBody();
        if (status == HttpStatus.OK) {
            Cookie cookie = new Cookie("auth_token", body);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/showBook/BookAnalysis";
        } else if (status == HttpStatus.UNAUTHORIZED) {
            model.addAttribute("errorMessage", "Wrong credentials. Please try again.");
        } else {
            model.addAttribute("errorMessage", "Authentication failed.");
        }
        return "login";
    }
}