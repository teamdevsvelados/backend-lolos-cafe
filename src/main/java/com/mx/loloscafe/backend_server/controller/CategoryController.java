package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.CategoryNotFoundException;
import com.mx.loloscafe.backend_server.model.Category;
import com.mx.loloscafe.backend_server.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findByCategoryId(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(categoryService.findById(id));
        } catch (CategoryNotFoundException X){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
