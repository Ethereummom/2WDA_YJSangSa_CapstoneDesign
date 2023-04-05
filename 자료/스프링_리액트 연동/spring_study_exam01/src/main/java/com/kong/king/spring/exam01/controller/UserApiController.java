package com.kong.king.spring.exam01.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kong.king.spring.exam01.dto.User;

@RestController
public class UserApiController {
	
	@PostMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> user() {
        System.out.println("UserApiController 진입");

        User user = new User(1, "홍길동", "ma123", "1234");

        return ResponseEntity.ok(user);
    }
}