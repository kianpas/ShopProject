package com.shop.admin.category.service;

import com.shop.admin.category.CategoryRepository;
import com.shop.admin.category.service.web.dto.CategoryResponseDto;
import com.shop.admin.category.service.web.dto.CategorySaveDto;
import com.shop.admin.category.service.web.dto.SubCategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;

	//전체 카테고리 조회
	public List<CategoryResponseDto> findAllCategory() {
		List<CategoryResponseDto> categoryList = categoryRepository.findCategoryByDepth(0)
											.stream()
											.map(CategoryResponseDto::new)
											.collect(Collectors.toList());

		return categoryList;
	}
	
	//메인카테고리 조회 - 테스트
	public List<CategoryResponseDto> getMainCategory(int depth) {
		return categoryRepository.findMainCategory(depth)
				.stream()
				.map(CategoryResponseDto::new)
				.collect(Collectors.toList());
	}
	
	//하위 카테고리 조회
	public List<CategoryResponseDto> getSubCategory(int depth) {
		return categoryRepository.findCategoryByDepth(depth)
				.stream()
				.map(CategoryResponseDto::new)
				.collect(Collectors.toList());
	}


	//카테고리 저장
    public Long save(CategorySaveDto categorySaveDto) {
		return categoryRepository.save(categorySaveDto.toEntity()).getId();
    }
}
