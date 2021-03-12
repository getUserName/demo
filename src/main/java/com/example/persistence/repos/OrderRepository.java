package com.example.persistence.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.persistence.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
