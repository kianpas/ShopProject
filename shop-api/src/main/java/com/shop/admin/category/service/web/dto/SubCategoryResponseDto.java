package com.shop.admin.category.service.web.dto;


import com.shop.common.entity.Category;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SubCategoryResponseDto {

	private Long id;
	
	private String name;

	private String alias;
	
	private String image;

	private List<SubCategoryResponseDto> subCategory;
	
	public SubCategoryResponseDto(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.alias = entity.getAlias();
		this.image = entity.getImage();

	}
	
	
}
