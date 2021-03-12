package com.example.persistence.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.persistence.entities.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, String>{

}
