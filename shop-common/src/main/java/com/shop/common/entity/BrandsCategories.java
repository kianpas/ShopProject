package com.shop.common.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "brands_categories")
public class BrandsCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
