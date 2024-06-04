package com.codmind.orderapi.services.impl;

import com.codmind.orderapi.converters.UserConverter;
import com.codmind.orderapi.dtos.LoginRequestDTO;
import com.codmind.orderapi.dtos.LoginResponseDTO;
import com.codmind.orderapi.entity.User;
import com.codmind.orderapi.exceptions.GeneralServiceException;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.exceptions.ValidateServiceException;
import com.codmind.orderapi.repository.UserRepository;
import com.codmind.orderapi.services.UserService;
import com.codmind.orderapi.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public User createUser(User user){
        try {
            UserValidator.signup(user);

            User existUser = userRepository.findByUsername(user.getUsername())
                    .orElse(null);

            if(existUser!=null) throw new ValidateServiceException("El nombre de usuario ya existe.");

            return userRepository.save(user);

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requet) {
        try {
            User user = userRepository.findByUsername(requet.getUsername())
                    .orElseThrow(()->new ValidateServiceException("Usuario o password incorrectos"));

            if(!user.getPassword().equals(requet.getPassword())) throw new ValidateServiceException("Usuario o password incorrectos");

            //return new LoginResponseDTO(userConverter.fromEntity(user), "TOKEN");

            return LoginResponseDTO.builder()
                    .userDTO(userConverter.fromEntity(user))
                    .token("TOKEN")
                    .build();

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}
