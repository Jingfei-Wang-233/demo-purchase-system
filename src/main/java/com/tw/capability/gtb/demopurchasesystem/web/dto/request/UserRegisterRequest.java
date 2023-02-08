package com.tw.capability.gtb.demopurchasesystem.web.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class UserRegisterRequest {
    public static final String PATTERN_USERNAME = "^[A-Za-z0-9]*$";
    private static final String PATTERN_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-~!@#$%^&*()_+=<>?:{}|,./;'·\"\\\\\\[\\]])"
            + "[a-zA-Z0-9-~!@#$%^&*()_+=<>?:{}|,./;'·\"\\\\\\[\\]]+$";
    public static final String ERR_MSG_PASSWORD = "password must contain both uppercase and lowercase letters, numbers and special symbols";
    public static final String ERR_MSG_USERNAME = "username should only contain letters or numbers";
    @NotBlank(message = "username cannot be blank")
    @Size(min = 8, max = 16, message = "username should have 8~16 characters")
    @Pattern(regexp = PATTERN_USERNAME, message = ERR_MSG_USERNAME)
    private String username;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, max = 16, message = "password should have 8~16 characters")
    @Pattern(regexp = PATTERN_PASSWORD, message = ERR_MSG_PASSWORD)
    private String password;
}
