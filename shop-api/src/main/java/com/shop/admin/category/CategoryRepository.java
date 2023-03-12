package com.shop.admin.category;

import com.shop.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("select c from Category c left join fetch c.parent where c.parent is null")
	public List<Category> findJoinCategory();

}
