package com.example.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
    @JoinColumn(name="menu_item_id", nullable=false)
	private MenuItem menuItem;
	@ManyToOne
    @JoinColumn(name="order_id", nullable=false)
	@JsonIgnore
	private Order order;
	private Integer quantity;
}