package com.shop.admin.category.service.web.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.common.entity.Category;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CategoryResponseDto {

	private Long id;
	
	private String name;

	private String alias;
	
	private String image;

	private Long parentId;

	private List<CategoryResponseDto> subCategory;
	
	public CategoryResponseDto(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.alias = entity.getAlias();
		this.image = entity.getImage();
		this.parentId = entity.getParent() != null ? entity.getParent().getId() : null;
		this.subCategory = entity.getSubCategories()
							.stream()
							.map(CategoryResponseDto::new)
							.collect(Collectors.toList());
	}

}
