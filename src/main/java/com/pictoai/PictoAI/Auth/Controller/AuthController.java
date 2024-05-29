package com.pictoai.PictoAI.Auth.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping(value = "login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Login from the endpoint");
    }

    @PostMapping(value = "register")
    public ResponseEntity<String> register(){
        return ResponseEntity.ok("Register from the endpoint");
    }



}
