package org.shop.admin.user.service.web.dto;

import com.shop.common.entity.Role;
import com.shop.common.entity.User;
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

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.password = entity.getPassword();
        this.enabled = entity.isEnabled();
        this.roles = entity.getRoles();
    }

}