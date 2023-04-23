package com.alberto.test.util.mapper;

import com.alberto.test.repository.entity.User;
import com.alberto.test.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user){
        return UserDto.builder()
                .userName(user.getName())
                .password(user.getPassword()).build();
    }

    public User toEntity(UserDto userDto){
        return User.builder()
                .name(userDto.getUserName())
                .password(userDto.getPassword())
                .build();
    }

}
