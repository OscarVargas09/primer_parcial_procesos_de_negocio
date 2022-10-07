package com.primer_parcial8.Controller;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Repository.ArticleRepository;
import com.primer_parcial8.Repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/article/{id}")
    public ResponseEntity getArticle(@PathVariable Long id){
        Optional<Article> article = articleRepository.findById(id);
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

    @PutMapping("/article/edit/{id}")
    public ResponseEntity updateArticle(@PathVariable Long id,@RequestBody Article article){
        Optional<Article> articleDb = articleRepository.findById(id);
        if(articleDb.isPresent()){
            try {
                articleDb.get().setCod_prod(article.getCod_prod());
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

    @DeleteMapping("/article/{id}")
    public ResponseEntity deleteArticle(@PathVariable Long id){
        Optional<Article> articleDb = articleRepository.findById(id);
        if(articleDb.isPresent()){
            articleRepository.delete(articleDb.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
