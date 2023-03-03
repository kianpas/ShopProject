package com.shop.admin.brand;


import com.shop.common.entity.Brand;
import com.shop.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

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
        Set<Category> appleCatSet = new HashSet<>();
        appleCatSet.add(smartphone);
        Brand apple = Brand.builder()
                .name("Apple")
                .logo("brand-logo.png")
                .categories(appleCatSet)
                .build();

        Brand savedBrand = brandRepository.save(apple);

        assertThat(savedBrand.getId()).isGreaterThan(0);


    }


}
