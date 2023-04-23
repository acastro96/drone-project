package com.alberto.test.repository.entity;


import com.alberto.test.repository.contract.IUserRepository;
import com.alberto.test.repository.jpa.UserJpa;
import com.alberto.test.service.dto.UserDto;
import com.alberto.test.util.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String password;

}
