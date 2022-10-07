package com.primer_parcial8.Controller;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(value = "/article/{code}")
    public ResponseEntity getArticle(@PathVariable Long code){
        Optional<Article> article = articleRepository.findById(code);
        if (article.isPresent()){
            return new ResponseEntity(article, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/article")
    public ResponseEntity createArticle(@RequestBody Article article){
        try {
            articleRepository.save(article);
            return new ResponseEntity(article, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/article/edit/{code}")
    public ResponseEntity updateArticle(@PathVariable Long code,@RequestBody Article article){
        Optional<Article> articleDb = articleRepository.findById(code);
        if(articleDb.isPresent()){
            try {
                articleDb.get().setName(article.getName());
                articleDb.get().setDescription(article.getDescription());
                articleDb.get().setDateRegister(article.getDateRegister());
                articleDb.get().setStock(article.getStock());
                articleDb.get().setPriceSale(article.getPriceSale());
                articleDb.get().setPriceBuy(article.getPriceBuy());
                articleRepository.save(articleDb.get());
                return new ResponseEntity(articleDb, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/article/all")
    public ResponseEntity listArticles(){
        List<Article> articles = articleRepository.findAll();
        if(articles.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articles, HttpStatus.OK);
    }

    @DeleteMapping("/article/{code}")
    public ResponseEntity deleteArticle(@PathVariable Long code){
        Optional<Article> articleDb = articleRepository.findById(code);
        if(articleDb.isPresent()){
            articleRepository.delete(articleDb.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
