package fi.haagahelia.coolreads.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryRecommendationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReadingRecommendationRepository recommendationRepository;

    @BeforeEach
    void setUp() {
 
        recommendationRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void getRecommendationsByCategoryIdReturnsEmptyListWhenCategoryDoesNotHaveRecommendations() throws Exception {
        Category category = new Category("Literature");
        categoryRepository.save(category);

        mockMvc.perform(get("/api/categories/" + category.getId() + "/recommendations"))
               .andExpect(status().isOk())
               .andExpect(content().string("No recommendations found for the category"));
    }


    @Test
    public void getRecommendationsByCategoryIdReturnsListOfRecommendationsWhenCategoryHasRecommendations() throws Exception {
        
        Category category = new Category("Fantasy");
        categoryRepository.save(category);
        
        ReadingRecommendation firstRecommendation = new ReadingRecommendation("Spring Boot", "https://spring.io/projects/spring-boot", "Create stand-alone Spring applications");
        firstRecommendation.setCategory(category);
        ReadingRecommendation secondRecommendation = new ReadingRecommendation("Javapoint", "http://www.javapoint.com", "Free Online Tutorials");
        secondRecommendation.setCategory(category);
        recommendationRepository.saveAll(List.of(firstRecommendation, secondRecommendation));

       
        mockMvc.perform(get("/api/categories/" + category.getId() + "/recommendations"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].title").value("Javapoint"))
               .andExpect(jsonPath("$[1].title").value("Spring Boot"));
    }

    @Test
    public void getRecommendationsByCategoryIdReturnsNotFoundWhenCategoryDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/categories/1/recommendations"))
               .andExpect(status().isNotFound());
    }
}
