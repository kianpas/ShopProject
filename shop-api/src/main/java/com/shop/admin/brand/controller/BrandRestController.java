package com.shop.admin.brand.controller;

import com.shop.admin.brand.service.BrandService;
import com.shop.admin.brand.service.web.dto.BrandResponseDto;

import com.shop.common.entity.Brand;
import com.shop.common.entity.BrandsCategories;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BrandRestController {

    private final BrandService brandService;

    //전체 카테고리 조회
    @GetMapping("/api/brand/brandList")
    public List<BrandResponseDto> listAllCategory() {
        return brandService.findJoinBrands();
    }

}
