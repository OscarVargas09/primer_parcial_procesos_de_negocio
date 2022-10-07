package com.primer_parcial8.Controller;


import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Models.Category;
import com.primer_parcial8.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/category")
    public ResponseEntity createCategory(@RequestBody Category category){
        try {
            categoryRepository.save(category);
            return new ResponseEntity(category, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/category/all")
    public ResponseEntity listCategory(){
        List<Category> category = categoryRepository.findAll();
        if(category.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @PutMapping("/category/edit/{code}")
    public ResponseEntity updateCategory(@PathVariable Long code,@RequestBody Category category){
        Optional<Category> categoryDb = categoryRepository.findById(code);
        if(categoryDb.isPresent()){
            try {
                categoryDb.get().setName(category.getName());
                categoryDb.get().setDescription(category.getDescription());
                return new ResponseEntity(categoryDb, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/categoty/{code}")
    public ResponseEntity deleteCategory(@PathVariable Long code){
        Optional<Category> categoryDb = categoryRepository.findById(code);
        if(categoryDb.isPresent()){
            categoryRepository.delete(categoryDb.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


