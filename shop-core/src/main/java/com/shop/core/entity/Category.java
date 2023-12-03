package com.shop.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
@ToString
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String name;

	@Column(length = 64, nullable = false, unique = true)
	private String alias;
	
	@Column(length = 64, nullable = false)
	private String image;

	private boolean enabled;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private Set<Category> subCategories;

//	@OneToMany(mappedBy = "category")
//	private Set<BrandsCategories> brands;

	@Builder
	public Category(Long id, boolean enabled, String name, String alias, String image, Category parent, Set<Category> subCategories) {
		this.id = id;
		this.name = name;
		this.enabled = enabled;
		this.alias = alias;
		this.image = image;
		this.parent = parent;
		this.subCategories = subCategories;
	}
}
