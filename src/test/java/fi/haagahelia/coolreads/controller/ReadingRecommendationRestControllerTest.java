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
        ReadingRecommendation firstRecommendation = new ReadingRecommendation("Title1", "http://wikipedia.com", "First description");
        ReadingRecommendation secondRecommendation = new ReadingRecommendation("Title2", "http://javapoint.com", "Second Description");
        recommendationRepository.saveAll(List.of(firstRecommendation, secondRecommendation));

        mockMvc.perform(get("/api/recommendations"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].title").value("Title1"))
               .andExpect(jsonPath("$[0].link").value("http://wikipedia.com"))
               .andExpect(jsonPath("$[0].description").value("First description"))
               .andExpect(jsonPath("$[1].title").value("Title2"))
               .andExpect(jsonPath("$[1].link").value("http://javapoint.com"))
               .andExpect(jsonPath("$[1].description").value("Second Description"));
    }
}
