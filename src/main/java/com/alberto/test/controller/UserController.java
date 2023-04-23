package com.alberto.test.controller;

import com.alberto.test.service.contract.IUserService;
import com.alberto.test.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping(path = "/getUser")
    public UserDto getUser(@RequestParam String username){
        return userService.getUser(username);
    }


}
