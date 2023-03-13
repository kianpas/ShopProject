package com.shop.admin.brand;


import com.shop.common.entity.Brand;
import com.shop.common.entity.BrandsCategories;
import com.shop.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testCreateNewBrand() {
        Category smartphone = Category.builder().id(7L).build();
        Set<BrandsCategories> appleCatSet = new HashSet<>();
//        appleCatSet.add(smartphone);
//        Brand apple = Brand.builder()
//                .name("Apple")
//                .logo("brand-logo.png")
//                .categories(appleCatSet)
//                .build();
//
//        Brand savedBrand = brandRepository.save(apple);
//
//        assertThat(savedBrand.getId()).isGreaterThan(0);

    }

    @Test
    public void testOneBrand() {
       Brand brand = brandRepository.getReferenceById(1L);
        System.out.println(brand);

    }

    @Test
    public void testListBrand() {
        List<Brand> brandList = brandRepository.findJoinBrand();

        brandList.forEach(brand -> System.out.println(brand));

    }

    @Test
    public void testDeleteBrand() {

        Long brandId = brandRepository.getReferenceById(1L).getId();

        brandRepository.deleteById(brandId);

    }


}
