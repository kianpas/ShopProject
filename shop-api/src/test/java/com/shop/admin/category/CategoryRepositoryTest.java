package com.shop.admin.category;

import com.shop.common.entity.Category;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Test
    public void testCreateFirstCat(){
        Category comcat = Category.builder()
                .id(2L)
                .alias("Electronics")
                .enabled(false)
                .image("test.png")
                .name("Electronics")
                .build();

        repo.save(comcat);

        assertThat(repo.save(comcat).getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllCategory(){
        List<Category> listCategory = repo.findAll();

        listCategory.forEach(category -> System.out.println(category.getId()));
    }

}
