package com.frederick.server.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCommonController {


    @GetMapping("/admin/start")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello admin %s!", name);
    }
}