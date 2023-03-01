package com.shop.admin.user.service.web.dto;

import com.shop.common.entity.Role;
import com.shop.common.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class UserUpdateDto {

	private Long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private Set<Role> roles;

	@Builder
	public UserUpdateDto(Long id, String email, String password, String firstName, String lastName, boolean enabled, Set<Role> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.roles = roles;
	}

	public User toEntity() {
		return User.builder().email(email).password(password).firstName(firstName)
				.lastName(lastName).roles(roles).build();
	}
}
