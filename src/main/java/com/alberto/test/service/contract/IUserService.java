package com.alberto.test.service.contract;

import com.alberto.test.service.dto.UserDto;

import java.util.Collection;

public interface IUserService {

    UserDto getUser(String userName);

    UserDto saveUser(UserDto userDto);

    Collection<UserDto> getAllUsers();

}
