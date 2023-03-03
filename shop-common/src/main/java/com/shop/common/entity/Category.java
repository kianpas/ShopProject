package com.shop.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
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

	@ManyToMany
	@JoinTable(
			name="categories_relation",
			joinColumns = @JoinColumn(name = "child_category_id"),
			inverseJoinColumns = @JoinColumn(name = "parent_category_id"))
	private Set<Category> categories;
	
//	@OneToOne
//	@JoinColumn(name = "parent_id")
//	private Category parent;
//
//	@OneToMany(mappedBy = "parent")
//	private Set<Category> children = new HashSet<>();

	@Builder
	public Category(Long id, String name, String alias, String image, Set<Category> categories) {
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.image = image;
		this.categories = categories;
	}
}
