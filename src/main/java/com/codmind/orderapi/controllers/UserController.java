package com.codmind.orderapi.controllers;

import com.codmind.orderapi.converters.UserConverter;
import com.codmind.orderapi.dtos.LoginRequestDTO;
import com.codmind.orderapi.dtos.LoginResponseDTO;
import com.codmind.orderapi.dtos.SignupRequestDTO;
import com.codmind.orderapi.dtos.UserDTO;
import com.codmind.orderapi.entity.User;
import com.codmind.orderapi.services.UserService;
import com.codmind.orderapi.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @PostMapping(value = "/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request){

        User user = userService.createUser(userConverter.signup(request));

        return new WrapperResponse<UserDTO>(true, "Success", userConverter.fromEntity(user)).createResponse();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<WrapperResponse<LoginResponseDTO>>login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response = userService.login(request);

        return new WrapperResponse<LoginResponseDTO>(true, "Success", response).createResponse();
    }
}
