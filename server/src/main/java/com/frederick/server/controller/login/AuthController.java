package com.frederick.server.controller.login;

import com.frederick.server.document.User;
import com.frederick.server.service.user.UserService;
import com.frederick.server.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String username, @RequestParam String role, @RequestParam String password) {

        log.info("Registering user: " + username);
        if (userService.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        User regUser = new User(username, role, password);
        userService.registerUser(regUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            // 인증 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            // 인증 수행
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // JWT 생성
            String token = jwtUtil.generateToken(username);

            // 로그인 성공 시 JWT 반환
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            // 인증 실패 시 예외 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test successful");
    }
}