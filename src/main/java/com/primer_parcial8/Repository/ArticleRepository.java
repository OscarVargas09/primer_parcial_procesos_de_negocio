package com.primer_parcial8.Repository;

import com.primer_parcial8.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByCodProd (String CodProd);
}
