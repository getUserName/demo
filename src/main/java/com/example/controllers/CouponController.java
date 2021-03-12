package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.entities.Coupon;
import com.example.persistence.repos.CouponRepository;

@RestController
@CrossOrigin
public class CouponController {

	@Autowired
	private CouponRepository repo;

	@GetMapping("/validatecoupon")
	@ResponseBody
	public Coupon getCoupon(@RequestParam(value = "coupon") String code ){
		return repo.findById(code).orElse(null);
	}
}
