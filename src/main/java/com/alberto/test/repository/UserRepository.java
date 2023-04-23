package com.alberto.test.repository;

import com.alberto.test.repository.contract.IUserRepository;
import com.alberto.test.repository.jpa.UserJpa;
import com.alberto.test.service.dto.UserDto;
import com.alberto.test.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private UserJpa userJpa;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUser(String userName) {
        return userJpa
                .findByName(userName)
                .map(userMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public UserDto getUser(long id) {
        return null;
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        return null;
    }
}
