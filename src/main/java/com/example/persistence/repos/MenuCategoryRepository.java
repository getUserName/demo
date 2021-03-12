package com.example.persistence.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.persistence.entities.MenuCategory;

public interface MenuCategoryRepository extends CrudRepository<MenuCategory, Integer> {

}
