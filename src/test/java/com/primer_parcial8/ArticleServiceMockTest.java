package com.primer_parcial8;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Repository.ArticleRepository;
import com.primer_parcial8.Services.ArticleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.primer_parcial8.CategoryServiceMockTest.mockCategory;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ArticleServiceMockTest {
    public static Article mockArticle() {
        Article model = new Article();
        model.setId(1L);
        model.setCodProd("123");
        model.setName("Limpido");
        model.setStock(2);
        model.setCategory(mockCategory());
        model.setDescription("HCL");
        model.setPriceSale(2000);
        model.setPriceBuy(5000);
        model.setDateRegister(new Date(10,10,20));

        return model;
    }
    public static Article mockArticleMod() {
        Article model = new Article();
        model.setId(1L);
        model.setCodProd("123");
        model.setName("Fab");
        model.setStock(2);
        model.setCategory(mockCategory());
        model.setDescription("HCL");
        model.setPriceSale(3000);
        model.setPriceBuy(6000);
        model.setDateRegister(new Date(10,10,20));

        return model;
    }
    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("Test para obtener articulos por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        Article article = mockArticle();

        //When
        when(articleRepository.findByCodProd(anyString())).thenReturn(Optional.of(article));
        ResponseEntity<Article> respuesta = articleService.ArticleByCOD(article.getCodProd());

        //Then
        Assertions.assertNotNull(respuesta);

    }

    @DisplayName("Test para listar a los Articulos")
    @Test
    void getAllArticlesTest() {

        //Given
        Article article = mockArticle();

        //When

        ResponseEntity<List<Article>> lista = articleService.listArticles();

        //Then
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para crear Articulo")
    @Test
    void createArticleTest() {
        //Given
        Article article = mockArticle();
        given(articleRepository.findByCodProd(article.getCodProd())).willReturn(Optional.of(article));
        given(articleRepository.save(article)).willReturn(article);
        //When

        ResponseEntity<Article> articleSave = articleService.createArticle(article);

        //Then
        Assertions.assertNotNull(articleSave);
    }

    @DisplayName("Test para editar un Articulo")
    @Test
    void editArticleTest() {
        // Given
        Article article = mockArticle();
        Article articleMod = mockArticleMod();
        given(articleRepository.findByCodProd(article.getCodProd())).willReturn(Optional.of(article));
        given(articleRepository.save(articleMod)).willReturn(articleMod);

        //when

        ResponseEntity<Article> articleSave = articleService.updateArticle(article.getCodProd(), articleMod);

        //Then
        Assertions.assertNotNull(articleSave);
    }

    @DisplayName("Test para eliminar un Articulo")
    @Test
    void deleteArticleTest() {
        //Given
        Article article = mockArticle();


        given(articleRepository.findByCodProd(article.getCodProd())).willReturn(Optional.of(article));
        articleRepository.deleteById(article.getId());



        //when

        Optional<Article> deleteArticle = articleRepository.findById(article.getId());

        //Then

        assertThat(deleteArticle).isEmpty();


    }

    @Test
    @DisplayName("Test para una lista vacia")
    void listArticlesEmpty() {
        when(articleRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity mockArticleService = articleService.listArticles();

        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 404, mockArticleService.getStatusCodeValue());
    }
}
