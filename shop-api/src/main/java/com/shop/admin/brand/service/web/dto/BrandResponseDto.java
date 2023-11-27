package com.shop.admin.brand.service.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.admin.category.service.web.dto.CategoryResponseDto;
import com.shop.common.entity.Brand;
import com.shop.common.entity.BrandsCategories;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BrandResponseDto {

    private Long id;

    private String name;

    private String logo;

    private List<BrandsCategories> categories;

    public void addBrandCategories(BrandsCategories brandsCategories){
        categories.add(brandsCategories);
    }

    public BrandResponseDto(Brand entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.logo = entity.getLogo();
//        this.categories = entity.getBrandsCategories()
//                .stream()
//                .collect(Collectors.toList());

//        this.brandId =
    }
}
