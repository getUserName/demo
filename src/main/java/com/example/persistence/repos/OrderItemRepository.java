package com.example.persistence.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.persistence.entities.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}
