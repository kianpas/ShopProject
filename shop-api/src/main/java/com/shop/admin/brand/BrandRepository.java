package com.shop.admin.brand;

import com.shop.common.entity.Brand;
import com.shop.common.entity.BrandsCategories;
import com.shop.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
//    @Query("select c from Category c left join fetch c.parent where c.parent is null")
    @Query("select b from Brand b")
    public List<Brand> findJoinBrand();


}
