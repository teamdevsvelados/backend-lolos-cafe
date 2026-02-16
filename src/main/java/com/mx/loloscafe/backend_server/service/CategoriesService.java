package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.CategoriesNotFoundException;
import com.mx.loloscafe.backend_server.model.Categories;
import com.mx.loloscafe.backend_server.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
	private final CategoriesRepository categoriesRepository;

	@Autowired
	public CategoriesService(CategoriesRepository categoriesRepository) {
		this.categoriesRepository = categoriesRepository;
	}

	// Method to fetch all categories
	public List<Categories> getCategories() {
		return categoriesRepository.findAll();
	}

	// Method to create a new category
	public Categories createCategory(Categories newCategory) {
		if (newCategory.getAvailable() == null) {
			newCategory.setAvailable(true);
		}
		return categoriesRepository.save(newCategory);
	}

	// Method to fetch a category by ID
	public Categories findById(Integer id) {
		return categoriesRepository.findById(id)
				.orElseThrow(() -> new CategoriesNotFoundException(id));
	}

	// Method to delete a category by ID
	public void deleteCategoryById(Integer id) {
		if (categoriesRepository.existsById(id)) {
			categoriesRepository.deleteById(id);
		} else {
			throw new CategoriesNotFoundException(id);
		}
	}

	// Method to update a category by ID
	public Categories updateCategoryById(Categories category, Integer id) {
		return categoriesRepository.findById(id)
				.map(categoryData -> {
					if (category.getNameOf() != null) {
						categoryData.setNameOf(category.getNameOf());
					}
					if (category.getAvailable() != null) {
						categoryData.setAvailable(category.getAvailable());
					}
					return categoriesRepository.save(categoryData);
				})
				.orElseThrow(() -> new CategoriesNotFoundException(id));
	}
}
