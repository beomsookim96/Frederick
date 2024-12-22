package com.frederick.server.controller.login;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {

        String id = request.get("id");
        String password = request.get("password");

        // id, password 암호화
        if (id.equals("admin") && password.equals("admin")) {
            return "success";
        }
        return String.format("Hello admin %s!", id);
    }
}
