package com.shop.admin.brand.service;

import com.shop.admin.brand.BrandRepository;
import com.shop.admin.brand.service.web.dto.BrandResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<BrandResponseDto> findJoinBrands(){
        return brandRepository.findJoinBrand()
                .stream()
                .map(BrandResponseDto::new)
                .collect(Collectors.toList());
    }
}
