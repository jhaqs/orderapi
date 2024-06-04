package com.codmind.orderapi.services;

import com.codmind.orderapi.dtos.LoginRequestDTO;
import com.codmind.orderapi.dtos.LoginResponseDTO;
import com.codmind.orderapi.entity.User;

public interface UserService {

    public User createUser(User user);

    public LoginResponseDTO login(LoginRequestDTO requet);
}
