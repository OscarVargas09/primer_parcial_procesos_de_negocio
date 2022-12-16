package com.primer_parcial8.Services;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleService {
    ResponseEntity<Article> createArticle(Article article);
    ResponseEntity<List<Article>> listArticles();
    ResponseEntity<Article> ArticleByCOD(String CodProd);
    ResponseEntity<Article> updateArticle(String CodProd, Article article);
    ResponseEntity<Article> deleteArticle(String CodProd);

}
