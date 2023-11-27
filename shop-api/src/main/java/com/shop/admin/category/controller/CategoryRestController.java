package com.shop.admin.category.controller;

import com.shop.admin.category.service.CategoryService;
import com.shop.admin.category.service.web.dto.CategoryResponseDto;
import com.shop.admin.category.service.web.dto.CategorySaveDto;
import com.shop.admin.user.service.web.dto.UserSaveDto;
import com.shop.admin.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

	//전체 카테고리 조회
	@GetMapping("/api/category/categoriesList")
	public ResponseEntity<List<CategoryResponseDto>> listAllCategory() {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
		resHeader.set("resCd", "200");
		return ResponseEntity.ok().headers(resHeader).body(categoryService.findAllCategories());
	}
	
	//계층화 카테고리 조회
	@GetMapping("/api/category/joinCategories")
	public ResponseEntity<List<CategoryResponseDto>> findJoinCategory() {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
		resHeader.set("resCd", "200");
		return ResponseEntity.ok().headers(resHeader).body(categoryService.findJoinCategory());
	}

	//새 카테고리 추가
	@PostMapping("/api/category/newCategory")
	public ResponseEntity<Long> newCategorySave(@ModelAttribute CategorySaveDto categorySaveDto,
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
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
		resHeader.set("resCd", "200");

		return ResponseEntity.ok().headers(resHeader).body(savedCategoryId);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleUserRuntimeExceptions(RuntimeException e){
		log.debug("카테고리 컨트롤러에서 생긴 문제입니다. : {}", e.getMessage(), e);
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.set(HttpHeaders.CONTENT_TYPE, "application/json");
		resHeader.set("resCd", "405");
		return ResponseEntity.badRequest().headers(resHeader).body(e.getMessage());
	}

//
//	@GetMapping("/api/category/subcategoryList/{depth}")
//	public List<CategoryResponseDto> listSubCategory(@PathVariable int depth) {
//		return categoryService.getSubCategory(depth);
//	}

}
