package com.primer_parcial8.Repository;

import com.primer_parcial8.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
