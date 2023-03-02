package com.shop.admin.user.controller;

import com.shop.admin.security.ShopmeUserDetails;
import com.shop.admin.user.service.UserService;
import com.shop.admin.user.service.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.shop.admin.util.FileUploadUtil;
import com.shop.admin.user.service.web.dto.UserResponseDto;
import com.shop.admin.user.service.web.dto.UserSaveDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<UserResponseDto>> listAll() throws RuntimeException {
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "200");
        return ResponseEntity.ok().headers(resHeader).body(userService.listAll());
    }
    
    //한명 조회
    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserResponseDto> selectOneUser(@PathVariable(name = "id") Long id) throws RuntimeException {
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "200");
        return ResponseEntity.ok().headers(resHeader).body(userService.refById(id));
    }

    //신규 유저 등록
    @PostMapping("/api/newUser")
    public ResponseEntity<Long> newUserSave(@ModelAttribute UserSaveDto userSaveDto,
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

        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "200");

        return ResponseEntity.ok().headers(resHeader).body(savedUserId);
    }

    //유저 수정
    @PutMapping("/api/edit")
    public ResponseEntity<Long> editUser(@RequestBody UserUpdateDto userUpdateDto) throws RuntimeException {
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "200");
        return ResponseEntity.ok().headers(resHeader).body(userService.edit(userUpdateDto));
    }

    //로그인한 유저 조회
    @GetMapping("/api/account")
    public ResponseEntity<UserResponseDto> viewDetails(@AuthenticationPrincipal ShopmeUserDetails loggedUser) throws RuntimeException {
        String email = loggedUser.getUsername();
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "200");
        return ResponseEntity.ok().headers(resHeader).body(userService.getByEmail(email));
    }

    //유저 삭제
    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable(name = "id") Long id) throws RuntimeException {
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "200");
        return ResponseEntity.ok().headers(resHeader).body(userService.delete(id));
    }

    @PutMapping("/api/users/{id}/enabled/{status}")
    public ResponseEntity<Long> editUser(@PathVariable(name = "id") Long id,
                                         @PathVariable(name = "status") boolean enabled) {
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "200");
        return ResponseEntity.ok().headers(resHeader).body(userService.updateUserEnabledStatus(id, enabled));
    }

    //Runtime Error 핸들링
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleUserRuntimeExceptions(RuntimeException e){
        log.debug("유저 컨트롤러에서 생긴 문제입니다. : {}", e.getMessage(), e);
        HttpHeaders resHeader = new HttpHeaders();
        resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
        resHeader.set("resCd", "405");
        return ResponseEntity.badRequest().headers(resHeader).body(e.getMessage());
    }


}
