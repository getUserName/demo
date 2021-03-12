package com.example.persistence.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.persistence.entities.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {
	
	@Query("SELECT i FROM MenuItem i WHERE LOWER(i.menuCategory.name) = LOWER(:name)")
	public Set<MenuItem> findByMenuCategoryName(@Param("name") String name);
}
