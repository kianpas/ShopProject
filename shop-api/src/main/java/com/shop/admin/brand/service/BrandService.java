package com.shop.admin.brand.service;

import com.shop.admin.brand.BrandRepository;
import com.shop.admin.brand.service.web.dto.BrandResponseDto;
import com.shop.common.entity.Brand;
import com.shop.common.entity.BrandsCategories;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;

    public List<BrandResponseDto> findJoinBrands(){
        List<BrandResponseDto> list = brandRepository.findJoinBrand()
                .stream()
                .map(BrandResponseDto::new)
                .collect(Collectors.toList());
        log.debug("{}", list.get(0).getName());
        return list;
    }

}
