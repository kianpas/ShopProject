package org.shop.admin.user.service;

import com.shop.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.shop.admin.user.UserRepository;
import org.shop.admin.user.service.web.dto.UserResponseDto;
import org.shop.admin.user.service.web.dto.UserSaveDto;
import org.shop.admin.user.service.web.dto.UserUpdateDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    
    //전체 조회
    public List<UserResponseDto> listAll(){
        List<UserResponseDto> list;
        list = userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
        return list;
    }

    //한명 조회
    public UserResponseDto refById(Long id) {
        User user = userRepository.getReferenceById(id);
        return new UserResponseDto(user);
    }

    //유저 등록
    public Long save(UserSaveDto userSaveDto) {
        userSaveDto.toEntity().setPassword(encodePassword(userSaveDto.getPassword()));
        return userRepository.save(userSaveDto.toEntity()).getId();
    }

    //암호화
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);

    }

    public Long edit(UserUpdateDto userUpdateDto) {
        return userRepository.save(userUpdateDto.toEntity()).getId();
    }
}
