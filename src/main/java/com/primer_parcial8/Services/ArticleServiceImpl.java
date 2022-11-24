package com.primer_parcial8.Services;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ResponseEntity<Article> createArticle(Article article) {
        try {
            articleRepository.save(article);
            return new ResponseEntity(article, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Article>> listArticles() {
        List<Article> article = articleRepository.findAll();
        if (article.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity(article, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Article> ArticleByCOD(String CodProd) {
       Optional<Article> article = articleRepository.findByCodProd(CodProd);
        if (article.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity(article, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Article> updateArticle(String CodProd, Article article) {
        Optional<Article> articleBD = articleRepository.findByCodProd(CodProd);
        if (articleBD.isPresent()) {
            try {
                articleBD.get().setCodProd(article.getCodProd());
                articleBD.get().setName(article.getName());
                articleBD.get().setDescription(article.getDescription());
                articleBD.get().setDateRegister(article.getDateRegister());
                articleBD.get().setStock(article.getStock());
                articleBD.get().setPriceSale(article.getPriceSale());
                articleBD.get().setPriceBuy(article.getPriceBuy());
                articleRepository.save(articleBD.get());
                return new ResponseEntity(articleBD, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Article> deleteArticle(String CodProd) {
        Optional<Article> articleBD = articleRepository.findByCodProd(CodProd);
        if (articleBD.isPresent()) {
            articleRepository.delete(articleBD.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}