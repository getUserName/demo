package com.example.persistence.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="orders")
@Data
@EqualsAndHashCode(exclude="items")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal totalPlusTax = BigDecimal.ZERO;
	private BigDecimal totalPlusTaxLessCoupon = BigDecimal.ZERO;
	@OneToMany(mappedBy="order")
	private Set<OrderItem> items;
	@OneToOne
    @JoinColumn(name = "coupon_code", referencedColumnName = "code")
	private Coupon coupon;
}
