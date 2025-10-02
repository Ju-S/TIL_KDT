package com.kedu.study02.controllers;

import com.kedu.study02.dto.LoginDTO;
import com.kedu.study02.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginInfo) {
        if(true) {
            //JWT 생성 자리
            String token = jwtUtil.createToken(loginInfo.getId());
            return ResponseEntity.ok(token);
        } else {

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);

        try {
            System.out.println(jwtUtil.verifyToken(token).getSubject());
            System.out.println(jwtUtil.verifyToken(token).getClaim("name"));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("장난치지말것");
        }

        return ResponseEntity.ok("정상유저");
    }
}
