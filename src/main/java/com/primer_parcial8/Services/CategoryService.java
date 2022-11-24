package com.primer_parcial8.Services;

import com.primer_parcial8.Models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService{
    ResponseEntity<Category> createCategory(Category category);
    ResponseEntity<List<Category>> listCategories();
    ResponseEntity<Category> CategoryByID(Long Id);
    ResponseEntity<Category> updateCategory(Long id, Category category);
    ResponseEntity<Category> deleteCategory(Long id);
}
