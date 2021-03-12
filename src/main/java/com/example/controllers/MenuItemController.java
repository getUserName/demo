package com.example.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.entities.MenuItem;
import com.example.persistence.repos.MenuItemRepository;

@RestController
public class MenuItemController {
	
	@Autowired
	private MenuItemRepository repo;
	
	@GetMapping("/itemsbycategory")
	@ResponseBody
	public Set<MenuItem> getItemsByCategory(@RequestParam(value = "category") String name) {
		return repo.findByMenuCategoryName(name);
	}
	
	@GetMapping("/menuitems")
	@ResponseBody
	public Set<MenuItem> getMenuItems() {
		Set<MenuItem> items = new HashSet<>();
		repo.findAll().forEach(i -> items.add(i));
		return items;
	}
}
