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

import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReadingRecommendationRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReadingRecommendationRepository recommendationRepository;

    @BeforeEach
    void setUp() {
        recommendationRepository.deleteAll();
    }

    @Test
    public void getRecommendationsReturnsEmptyListWhenNoRecommendationsExist() throws Exception {
        mockMvc.perform(get("/api/recommendations"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getRecommendationsReturnsListOfRecommendationsWhenRecommendationsExist() throws Exception {
        ReadingRecommendation firstRecommendation = new ReadingRecommendation("Spring Boot", "https://spring.io/projects/spring-boot", "Create stand-alone Spring applications");
        ReadingRecommendation secondRecommendation = new ReadingRecommendation("Javapoint", "http://www.javapoint.com", "Free Online Tutorials");
        recommendationRepository.saveAll(List.of(firstRecommendation, secondRecommendation));

        mockMvc.perform(get("/api/recommendations"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].title").value("Javapoint"))
               .andExpect(jsonPath("$[0].link").value("http://www.javapoint.com"))
               .andExpect(jsonPath("$[0].description").value("Free Online Tutorials"))
               .andExpect(jsonPath("$[1].title").value("Spring Boot"))
               .andExpect(jsonPath("$[1].link").value("https://spring.io/projects/spring-boot"))
               .andExpect(jsonPath("$[1].description").value("Create stand-alone Spring applications"));
               
    }
}
