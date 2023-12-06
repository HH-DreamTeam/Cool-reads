package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Recommendation", description = "Information about reading recommendations")
public class ReadingRecommendationRestController {
	@Autowired
	private ReadingRecommendationRepository recommendationRepository;

    @Operation(
        summary = "List of reading recommendations",
        description = "Fetches all reading recommendations"
    )
    @GetMapping("/api/recommendations")
    public List<ReadingRecommendation> getRecommendations() {
        return recommendationRepository.findAllByOrderByCreatedAtDesc();
    }
}