package com.shop.admin.category;

import com.shop.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("select c from Category c")
	public List<Category> findAllCategory();

//	@Query("select distinct c from Category c left join fetch c.categories where depth = ?1")
//	public List<Category> findMainCategory(int depth);
//
//	//c.categories -> Entity 필드명
//	@Query("select distinct c from Category c " +
//			"left join fetch c.categories c2 " +
//			"left join c.categories c3 " +
//			"where c.id = c3.id order by c.id")
//	public List<Category> findCategoryByDepth(int depth);



}
