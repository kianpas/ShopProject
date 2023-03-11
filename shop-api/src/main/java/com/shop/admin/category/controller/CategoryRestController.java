package com.shop.admin.category.controller;

import com.shop.admin.category.service.CategoryService;
import com.shop.admin.category.service.web.dto.CategoryResponseDto;
import com.shop.admin.category.service.web.dto.CategorySaveDto;
import com.shop.admin.user.service.web.dto.UserSaveDto;
import com.shop.admin.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CategoryRestController {

	private final CategoryService categoryService;

	//전체 조회
//	@GetMapping("/api/category/categoryList")
//	public List<CategoryResponseDto> listAllCategory() {
//		return categoryService.findAllCategory();
//	}

	//새 카테고리 추가
	@PostMapping("/api/category/newCategory")
	public Long newCategorySave(@ModelAttribute CategorySaveDto categorySaveDto,
								@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		Long savedCategoryId;
		if(file != null && !file.isEmpty() && file.getOriginalFilename() != null) {
			log.debug("{}", file.getOriginalFilename());
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			categorySaveDto.setImage(filename);
			savedCategoryId = categoryService.save(categorySaveDto);
			String uploadDir = "category-photos/" + savedCategoryId;
			FileUploadUtil.saveFiles(uploadDir, filename, file);
		} else {
			savedCategoryId = categoryService.save(categorySaveDto);
		}

		return savedCategoryId;
	}

//	@GetMapping("/api/category/mainCategoryList/{depth}")
//	public List<CategoryResponseDto> listMainCategory(@PathVariable int depth) {
//		return categoryService.getMainCategory(depth);
//	}
//
//	@GetMapping("/api/category/subcategoryList/{depth}")
//	public List<CategoryResponseDto> listSubCategory(@PathVariable int depth) {
//		return categoryService.getSubCategory(depth);
//	}

}
