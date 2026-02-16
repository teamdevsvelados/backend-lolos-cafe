package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Repository interface for performing CRUD operations on the Categories entity
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
	// Derived query method to locate a category by its name
	Categories findByNameOf(String nameOf);
}
