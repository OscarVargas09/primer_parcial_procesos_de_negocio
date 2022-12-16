package com.primer_parcial8.Repository;

import com.primer_parcial8.Models.Article;
import com.primer_parcial8.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCodCat (String codCat);
}
