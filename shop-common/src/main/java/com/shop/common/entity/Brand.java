package com.shop.common.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "brands")
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @Column(nullable = false, length = 128)
    private String logo;

    @OneToMany(mappedBy = "category")
    private Set<BrandsCategories> categories;

    @Builder
    public Brand(String name, String logo, Set<BrandsCategories> categories) {
        this.name = name;
        this.logo = logo;
        this.categories = categories;
    }
}
