package fi.haagahelia.coolreads.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    public void getCategoriesReturnsEmptyListWhenNoCategoriesExist() throws Exception {
        mockMvc.perform(get("/api/categories"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getCategoriesReturnsListOfCategoriesWhenCategoriesExist() throws Exception {
        Category firstCategory = new Category("Fiction");
        Category secondCategory = new Category("Non-Fiction");
        categoryRepository.saveAll(List.of(firstCategory, secondCategory));

        mockMvc.perform(get("/api/categories"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].name").value("Fiction"))
               .andExpect(jsonPath("$[1].name").value("Non-Fiction"));
    }
}
