package com.shop.admin.category;

import com.shop.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("select c from Category c left join fetch c.categories")
	public List<Category> findAllCategory();

//	@Query("select c from Category c left join fetch c.categories where depth ")
//	public List<Category> findMainCategory();
	
	//c.categories -> Entity 필드명
	@Query("select c from Category c left join fetch c.categories where depth = ?1")
	public List<Category> findSubCategory(int depth);

}
