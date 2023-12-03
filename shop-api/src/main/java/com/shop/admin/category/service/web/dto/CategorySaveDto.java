package com.shop.admin.category.service.web.dto;

import com.shop.core.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategorySaveDto {

    private Long id;

    private String name;

    private String alias;

    private String image;

    private Long parentId;

    @Builder
    public CategorySaveDto(Long id, String name, String alias, String image, Long parentId) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.image = image;
        this.parentId = parentId;
    }

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .alias(alias)
                .image(image)
//                .parentId(parentId)
                .build();
    }
}
