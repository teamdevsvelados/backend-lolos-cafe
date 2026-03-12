package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.CategoryNotFoundException;
import com.mx.loloscafe.backend_server.model.Category;
import com.mx.loloscafe.backend_server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	// Method to fetch all categories
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	// Method to create a new category
	public Category createCategory(Category newCategory) {
		if (newCategory.getAvailable() == null) {
			newCategory.setAvailable(true);
		}
		return categoryRepository.save(newCategory);
	}

	// Method to fetch a category by ID
	public Category findById(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(id));
	}

	// Method to delete a category by ID
	public void deleteCategoryById(Integer id) {
		if (categoryRepository.existsById(id)) {
			categoryRepository.deleteById(id);
		} else {
			throw new CategoryNotFoundException(id);
		}
	}

	// Method to update a category by ID
	public Category updateCategoryById(Category category, Integer id) {
		return categoryRepository.findById(id)
				.map(categoryData -> {
					if (category.getNameOf() != null) {
						categoryData.setNameOf(category.getNameOf());
					}
					if (category.getAvailable() != null) {
						categoryData.setAvailable(category.getAvailable());
					}
					return categoryRepository.save(categoryData);
				})
				.orElseThrow(() -> new CategoryNotFoundException(id));
	}

    public boolean existsCategoryById(Integer id) {
        if (id == null) return false;
        return categoryRepository.existsById(id);
    }

}
