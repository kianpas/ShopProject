package com.shop.admin.category.service.web.dto;


import com.shop.common.entity.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponseDto {

	private Long id;
	
	private String name;

	private String alias;
	
	private String image;
	
	private Long parentId;
	
	private List<SubCategoryResponseDto> subCategory;
	
	public CategoryResponseDto(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.alias = entity.getAlias();
		this.image = entity.getImage();
		this.parentId = entity.getParent() != null ? entity.getParent().getId() : null;
			
	}
	
	public void setSubCategory(List<SubCategoryResponseDto> subCategory) {
		this.subCategory = subCategory;
	}
	
	
}
