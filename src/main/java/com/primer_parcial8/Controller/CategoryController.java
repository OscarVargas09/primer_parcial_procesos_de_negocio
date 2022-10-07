package com.primer_parcial8.Controller;


import com.primer_parcial8.Models.Category;
import com.primer_parcial8.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}


