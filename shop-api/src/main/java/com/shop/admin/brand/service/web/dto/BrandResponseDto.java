package com.shop.admin.brand.service.web.dto;

import com.shop.admin.category.service.web.dto.CategoryResponseDto;
import com.shop.common.entity.Brand;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BrandResponseDto {

    private Long id;

    private String name;

    private String logo;

    private List<CategoryResponseDto> categories;

    public BrandResponseDto(Brand entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.logo = entity.getLogo();
   /*     this.categories = */

    }
}
