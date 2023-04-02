package com.shop.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@Table(name = "brands")
@NoArgsConstructor
@ToString
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @Column(nullable = false, length = 128)
    private String logo;

    @OneToMany(mappedBy = "brand")
    private Set<BrandsCategories> brandsCategories;

    @Builder
    public Brand(Long id, String name, String logo, Set<BrandsCategories> brandsCategories) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.brandsCategories = brandsCategories;
    }
}
