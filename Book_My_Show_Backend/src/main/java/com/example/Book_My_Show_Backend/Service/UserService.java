package com.example.Book_My_Show_Backend.Service;

import com.example.Book_My_Show_Backend.Dtos.UserRequestDto;
import com.example.Book_My_Show_Backend.Models.UserEntity;
import com.example.Book_My_Show_Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto)
    {
        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();

        try {
            userRepository.save(userEntity);
        }
        catch (Exception e)
        {
            return "User could not be added";
        }
        return "User added successfully";
    }
}
