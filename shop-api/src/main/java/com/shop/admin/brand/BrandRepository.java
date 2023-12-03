package com.shop.admin.brand;

import com.shop.core.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
//    @Query("select c from Category c left join fetch c.parent where c.parent is null")
    @Query("select b from Brand b")
    public List<Brand> findJoinBrand();


}
