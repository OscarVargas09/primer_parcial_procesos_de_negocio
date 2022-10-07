package com.primer_parcial8.Repository;

import com.primer_parcial8.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
