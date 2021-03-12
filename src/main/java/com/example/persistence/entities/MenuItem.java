package com.example.persistence.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class MenuItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private BigDecimal price;
	private BigDecimal tax;
	@ManyToOne
    @JoinColumn(name="category_id", nullable=false)
	private MenuCategory menuCategory;
}
