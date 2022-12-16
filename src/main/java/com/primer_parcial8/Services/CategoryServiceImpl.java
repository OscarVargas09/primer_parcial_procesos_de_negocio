package com.primer_parcial8.Services;

import com.primer_parcial8.Models.Category;
import com.primer_parcial8.Repository.CategoryRepository;
import com.primer_parcial8.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<Category> createCategory(Category category){
        try{
            categoryRepository.save(category);
            return new ResponseEntity(category, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return  ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Category>> listCategories(){
        List<Category> category = categoryRepository.findAll();
        if(category.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(category,HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<Category> CategoryByCOD(String codCat){
        Optional<Category> category = categoryRepository.findByCodCat(codCat);
        if (category.isPresent()) {
            return new ResponseEntity(category, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Category> updateCategory(String codCat, Category category){
        Optional<Category> categoryBD = categoryRepository.findByCodCat(codCat);
        if(categoryBD.isPresent()){
            try {
                categoryBD.get().setName(category.getName());
                categoryBD.get().setDescription(category.getDescription());
                categoryRepository.save(categoryBD.get());
                return new ResponseEntity(categoryBD,HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Category> deleteCategory(String codCat){
        Optional<Category> categoryBD = categoryRepository.findByCodCat(codCat);
        if (categoryBD.isPresent()) {
            categoryRepository.delete(categoryBD.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
