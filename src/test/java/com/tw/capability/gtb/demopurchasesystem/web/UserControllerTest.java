package com.tw.capability.gtb.demopurchasesystem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.capability.gtb.demopurchasesystem.service.UserService;
import com.tw.capability.gtb.demopurchasesystem.support.exception.UserDuplicateException;
import com.tw.capability.gtb.demopurchasesystem.web.dto.request.UserRegisterRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;


    @Test
    void should_register_successfully() throws Exception {
        UserRegisterRequest registerRequest = UserRegisterRequest.builder().username("wangbingbing").password("Wangbingbing@123").build();
        Mockito.doNothing().when(userService).register(registerRequest);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(new ObjectMapper().writeValueAsString(registerRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isCreated());
    }

    @Test
    void should_throw_conflict_when_username_already_exists() throws Exception {
        UserRegisterRequest registerRequest = UserRegisterRequest.builder().username("wangbingbing").password("Wangbingbing@123").build();
        Mockito.doThrow(new UserDuplicateException(registerRequest.getUsername())).when(userService).register(any());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(new ObjectMapper().writeValueAsString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The username: wangbingbing has been registered"));
    }
}
