package com.shop.admin.user.service.web.dto;

import com.shop.core.entity.Role;
import com.shop.core.entity.User;
import lombok.Getter;

import java.util.Set;

@Getter
public class UserResponseDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private Set<Role> roles;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }

}