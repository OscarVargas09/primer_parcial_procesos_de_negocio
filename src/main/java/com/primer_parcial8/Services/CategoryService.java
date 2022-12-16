package com.primer_parcial8.Services;

import com.primer_parcial8.Models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService{
    ResponseEntity<Category> createCategory(Category category);
    ResponseEntity<List<Category>> listCategories();
    ResponseEntity<Category> CategoryByCOD(String codCat);
    ResponseEntity<Category> updateCategory(String CodCat, Category category);
    ResponseEntity<Category> deleteCategory(String CodCat);
}
