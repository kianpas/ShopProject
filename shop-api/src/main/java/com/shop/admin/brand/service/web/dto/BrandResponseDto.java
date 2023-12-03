package com.shop.admin.brand.service.web.dto;

import com.shop.core.entity.Brand;
import com.shop.core.entity.BrandsCategories;
import lombok.Getter;

import java.util.List;

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
