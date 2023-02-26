package org.shop.admin.user.service;

import lombok.RequiredArgsConstructor;
import org.shop.admin.user.UserRepository;
import org.shop.admin.user.service.web.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    //전체 조회
    public List<UserResponseDto> listAll(){
        List<UserResponseDto> list = userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
        return list;
    }
}
