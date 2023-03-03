package com.shop.admin.category.service.web.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.common.entity.Category;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class CategoryResponseDto {

	private Long id;
	
	private String name;

	private String alias;
	
	private String image;

	private String parentCategoryId;

	@JsonIgnore
	private Set<Category> subCategory;
	
//	private List<SubCategoryResponseDto> subCategory;
	
	public CategoryResponseDto(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.alias = entity.getAlias();
		this.image = entity.getImage();
		this.subCategory = entity.getCategories();
	}
	
//	public void setSubCategory(List<SubCategoryResponseDto> subCategory) {
//		this.subCategory = subCategory;
//	}
	
	
}
