package com.onlinebanking.team3.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.onlinebanking.team3.onlinebanking.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

@RestController
public class PasswordResetController {

    @Autowired
    private UserService userService; // Inject your UserService

    @Autowired
    private JavaMailSender mailSender; // Inject JavaMailSender

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String resetToken = TokenGenerator.generateToken();
        
//        email = "rohithvepery8@gmail.com";
//        resetToken = "12345";
//        sendResetPasswordEmail(email, resetToken);
        
        if (userService.createPasswordResetToken(email, resetToken)) {
            try {
                sendResetPasswordEmail(email, resetToken);
                return ResponseEntity.ok(Map.of("message", "Reset email sent successfully."));
            } catch (MessagingException e) {
                return ResponseEntity.badRequest().body(Map.of("message", "Email sending failed."));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Failed to generate reset token."));
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateResetToken(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String token = request.get("token");
        
        if (userService.isResetTokenValid(email, token)) {
            return ResponseEntity.ok(Map.of("message", "Token is valid."));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid token."));
        }
    }

    private void sendResetPasswordEmail(String recipientEmail, String resetToken) throws MessagingException {
    	System.out.println(recipientEmail);
    	System.out.println(resetToken);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Password Reset Request");
        helper.setText("Click the link below to reset your password: "
                + "http://yourapp.com/reset-password?token=" + resetToken, true);

        mailSender.send(message);
    }
}


class TokenGenerator {
    private static final int TOKEN_LENGTH = 16; // Token length in bytes

    public static String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
