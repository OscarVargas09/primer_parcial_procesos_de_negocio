package com.primer_parcial8.Controller;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Services.ArticleService;
import com.primer_parcial8.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/article")
    public ResponseEntity createArticle(@Valid @RequestBody Article article){
        return articleService.createArticle(article);
    }
    @GetMapping(value = "/articles")
    public ResponseEntity listArticles(@RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return articleService.listArticles();
        }
    }
    @GetMapping(value = "/article/{codigo}")
    public ResponseEntity getArticle(@PathVariable String CodProd, @RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return articleService.ArticleByCOD(CodProd);
        }
    }
    @PutMapping(value = "/updateArticle/{codigo}")
    public ResponseEntity updateArticle(@PathVariable String CodProd, @RequestBody Article article, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return articleService.updateArticle(CodProd, article);
        }
    }
    @DeleteMapping(value = "/deleteArticle/{codigo}")
    public ResponseEntity deleteArticle(@PathVariable String CodProd, @RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return articleService.deleteArticle(CodProd);
        }
    }

}
