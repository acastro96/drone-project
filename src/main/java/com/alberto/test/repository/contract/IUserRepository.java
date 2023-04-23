package com.alberto.test.repository.contract;

import com.alberto.test.service.dto.UserDto;

import java.util.Collection;

public interface IUserRepository {

    UserDto getUser(String userName);

    UserDto getUser(long id);

    Collection<UserDto> getAllUsers();

}
