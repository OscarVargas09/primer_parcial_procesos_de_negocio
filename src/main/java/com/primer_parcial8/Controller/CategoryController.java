package com.primer_parcial8.Controller;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Models.Category;
import com.primer_parcial8.Utils.JWTUtil;
import com.primer_parcial8.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/category")
    public ResponseEntity createCategory(@Valid @RequestBody Category category){
        return categoryService.createCategory(category);
    }
    @GetMapping(value = "categories")
    public ResponseEntity listCategories(@RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoryService.listCategories();
        }
    }
    @GetMapping(value = "category/{codigo}")
    public ResponseEntity getCategory(@PathVariable String codCat, @RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoryService.CategoryByCOD(codCat);
        }
    }
    @PutMapping(value = "/updateCategory/{codigo}")
    public ResponseEntity updateCategory(@PathVariable String codCat, @RequestBody Category category, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoryService.updateCategory(codCat, category);
        }
    }
    @DeleteMapping(value = "/deleteCategory/{codigo}")
    public ResponseEntity deleteCategory(@PathVariable String codCat, @RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoryService.deleteCategory(codCat);
        }
    }
}