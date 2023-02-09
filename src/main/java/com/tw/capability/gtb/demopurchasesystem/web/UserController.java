package com.tw.capability.gtb.demopurchasesystem.web;

import com.tw.capability.gtb.demopurchasesystem.service.UserService;
import com.tw.capability.gtb.demopurchasesystem.web.dto.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    public static final String SUCCESS = "You have registered successfully.";
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid UserRegisterRequest request) {
        userService.register(request);
        return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
    }
}
