package com.alberto.test.service;

import com.alberto.test.repository.contract.IUserRepository;
import com.alberto.test.service.contract.IUserService;
import com.alberto.test.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDto getUser(String userName) {
        return userRepository.getUser(userName);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        return null;
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        return null;
    }
}
