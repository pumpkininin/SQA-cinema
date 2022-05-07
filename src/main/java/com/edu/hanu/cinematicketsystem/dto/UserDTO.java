package com.edu.hanu.cinematicketsystem.dto;

import com.edu.hanu.cinematicketsystem.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private Set<String> roleSet;
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
