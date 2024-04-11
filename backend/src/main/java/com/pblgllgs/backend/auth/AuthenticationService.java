package com.pblgllgs.backend.auth;
/*
 *
 * @author pblgl
 * Created on 10-04-2024
 *
 */

import com.pblgllgs.backend.email.EmailService;
import com.pblgllgs.backend.email.EmailServiceProperties;
import com.pblgllgs.backend.email.EmailTemplateName;
import com.pblgllgs.backend.exception.ResourceNotFoundException;
import com.pblgllgs.backend.role.Role;
import com.pblgllgs.backend.role.RoleRepository;
import com.pblgllgs.backend.user.Token;
import com.pblgllgs.backend.user.TokenRepository;
import com.pblgllgs.backend.user.User;
import com.pblgllgs.backend.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final EmailServiceProperties emailServiceProperties;

    @Transactional
    public void register(RegistrationRequest registrationRequest) throws MessagingException {
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new ResourceNotFoundException("ROLE_NOT_FOUND"));
        User user = User.builder()
                .firstname(registrationRequest.firstname())
                .lastname(registrationRequest.lastname())
                .email(registrationRequest.email())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(role))
                .build();
        User userSaved = userRepository.save(user);
        sendValidationEmail(userSaved);
    }

    private void sendValidationEmail(User userSaved) throws MessagingException {
        var newToken = generateAndSaveActivationToken(userSaved);
        emailService.sendEmail(
                userSaved.getEmail(),
                userSaved.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                emailServiceProperties.getActivationUrl(),
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(User userSaved) {
        String generatedCode = generateActivationCode(6);
        var token = Token.builder()
                .code(generatedCode)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(userSaved)
                .build();
        tokenRepository.save(token);
        return generatedCode;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder code = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }
        return code.toString();
    }
}
