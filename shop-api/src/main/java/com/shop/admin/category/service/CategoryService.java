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
	public List<CategoryResponseDto> findAllCat() {
		
		List<CategoryResponseDto> categoryList = categoryRepository.findAllCategory()
											.stream()
											.map(CategoryResponseDto::new)
											.collect(Collectors.toList());
		
		List<SubCategoryResponseDto> tempList;
		
		for(CategoryResponseDto cat : categoryList) {
			tempList = getSubCategory(cat.getId());
			cat.setSubCategory(tempList);
		}
		
		return categoryList;
	}
	
	//하위 카테고리 조회
	public List<SubCategoryResponseDto> getSubCategory(Long id) {
		return categoryRepository.getSubCategory(id)
				.stream()
				.map(SubCategoryResponseDto::new)
				.collect(Collectors.toList());
	}

	//사용 X
//	public List<CategoryResponseDto> findAllCatTest() {
//
//		List<CategoryResponseDto> catList = categoryRepository.findAllCategory()
//		List<CategoryResponseDto> catList = categoryRepository.findAll()
//											.stream()
//											.map(CategoryResponseDto::new)
//											.collect(Collectors.toList());
//		return catList;
//	}

	//카테고리 저장
    public Long save(CategorySaveDto categorySaveDto) {
		return categoryRepository.save(categorySaveDto.toEntity()).getId();
    }
}
