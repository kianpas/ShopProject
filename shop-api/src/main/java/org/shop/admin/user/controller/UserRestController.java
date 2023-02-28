package org.shop.admin.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shop.admin.FileUploadUtil;
import org.shop.admin.user.service.UserService;
import org.shop.admin.user.service.web.dto.UserResponseDto;
import org.shop.admin.user.service.web.dto.UserSaveDto;
import org.shop.admin.user.service.web.dto.UserUpdateDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
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

    //신규 유저 등록
    @PostMapping(path = "/api/newUser")
    public Long newUserSave(@ModelAttribute UserSaveDto userSaveDto,
                            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Long savedUserId;
        if(file != null && !file.isEmpty() && file.getOriginalFilename() != null){
            log.debug("{}", file.getOriginalFilename());
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            userSaveDto.setPhoto(filename);
            savedUserId = userService.save(userSaveDto);
            String uploadDir = "user-photos/" + savedUserId;
            FileUploadUtil.saveFiles(uploadDir, filename, file);
        } else {
            savedUserId = userService.save(userSaveDto);
        }
        return savedUserId;
    }

    //유저 수정
    @PutMapping("/api/edit")
    public Long editUser(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.edit(userUpdateDto);
    }

    //Runtime Error 핸들링
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleUserRuntimeExceptions(RuntimeException e){
        log.debug("유저 컨트롤러에서 생긴 문제입니다. : {}", e.getMessage(), e);
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set("Content-Type", "application/json");
        resHeader.set("resCd", "405");
        return ResponseEntity.badRequest().headers(resHeader).body(e.getMessage());
    }


}
