package com.primer_parcial8.Services;

import com.primer_parcial8.Models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService{
    ResponseEntity<Category> createCategory(Category category);
    ResponseEntity<List<Category>> listCategories();
    ResponseEntity<Category> CategoryByID(Long id);
    ResponseEntity<Category> updateCategoryByID(Long id, Category category);
    ResponseEntity<Category> deleteCategoryByID(Long id);
   /* ResponseEntity<Category> CategoryByCOD(String codCat);
    ResponseEntity<Category> updateCategoryByCOD(String CodCat, Category category);
    ResponseEntity<Category> deleteCategoryByCOD(String CodCat);*/
}
