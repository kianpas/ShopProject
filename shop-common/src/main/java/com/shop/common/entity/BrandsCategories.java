package com.shop.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "brands_categories")
@NoArgsConstructor
public class BrandsCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;


}
