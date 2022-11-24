package com.primer_parcial8;

import com.primer_parcial8.Models.Category;
import com.primer_parcial8.Repository.CategoryRepository;
import com.primer_parcial8.Services.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CategoryServiceMockTest {
        public static Category mockCategory() {
            Category modelo = new Category();
            modelo.setId(1L);
            modelo.setName("Ropa");
            modelo.setDescription("Productos de tela");

            return modelo;
        }
        public static Category mockCategoryMod() {
            Category modelo = new Category();
            modelo.setId(1L);
            modelo.setName("Ropa");
            modelo.setDescription("Productos de algodon");

            return modelo;
        }

        @InjectMocks
        private CategoryServiceImpl categoryService;
        @Mock
        private CategoryRepository categoryRepository;


        @DisplayName("Test para listar a las categorias")
        @Test
        void getAllCategoryTest() {
            //Given
            Category category = mockCategory();
            //When
            ResponseEntity<List<Category>> lista = categoryService.listCategories();
            //Then
            Assertions.assertNotNull(lista);
        }
        @DisplayName("Test para crear Categoria")
        @Test
        void createArticleTest() {
            //Given
            Category category = mockCategory();
            given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
            given(categoryRepository.save(category)).willReturn(category);
            //When

            ResponseEntity<Category> categorySave = categoryService.createCategory(category);

            //Then
            Assertions.assertNotNull(categorySave);
        }
        @DisplayName("Test para editar una Categoria")
        @Test
        void editCategoriaTest() {
            // Given
            Category category = mockCategory();
            Category categoryMod = mockCategoryMod();
            given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
            given(categoryRepository.save(categoryMod)).willReturn(categoryMod);

            //when

            ResponseEntity<Category> categorySave = categoryService.updateCategory(category.getId(), categoryMod);

            //Then
            Assertions.assertNotNull(categorySave);
        }
}
