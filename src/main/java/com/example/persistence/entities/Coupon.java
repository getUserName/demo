package com.example.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Coupon {
	@Id
	private String code;
	private Boolean used;
}
