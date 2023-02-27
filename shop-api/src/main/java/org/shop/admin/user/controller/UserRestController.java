package org.shop.admin.user.controller;

import lombok.RequiredArgsConstructor;
import org.shop.admin.user.service.UserService;
import org.shop.admin.user.service.web.dto.UserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;
    
    //전체 유저 조회
    @GetMapping("/api/users/userList")
    public List<UserResponseDto> listAll() {
        return userService.listAll();
    }
    
    //한명 조회
    @GetMapping("/api/user/{id}")
    public UserResponseDto selectOneUser(@PathVariable(name = "id") Long id) {
        return userService.refById(id);
    }

}
