package com.example.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.repos.MenuCategoryRepository;

@RestController
@CrossOrigin
public class MenuCategoryController {
	
	@Autowired
	private MenuCategoryRepository repo;

	@GetMapping("/menucategories")
	@ResponseBody
	public Set<String> getCategories() {
		Set<String> names = new HashSet<>();
		repo.findAll().forEach(category -> names.add(category.getName()));
		return names;
	}
}
