package com.pblgllgs.backend.auth;
/*
 *
 * @author pblgl
 * Created on 10-04-2024
 *
 */

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

//    private final AuthenticationService authenticationService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest registrationRequest) throws MessagingException {
//        authenticationService.register(registrationRequest);
//        return ResponseEntity.accepted().build();
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest){
//        return new ResponseEntity<>(authenticationService.authenticate(authenticationRequest), HttpStatus.OK);
//    }
//
//    @GetMapping("/activate-account")
//    public void validate(@RequestParam String code) throws MessagingException {
//        authenticationService.activateAccount(code);
//    }

}
