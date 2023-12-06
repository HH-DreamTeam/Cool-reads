package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Category", description = "Information about categories and their reading recommendations")
public class CategoryRestController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ReadingRecommendationRepository readingRecommendationRepository;

    @Operation(
        summary = "List of categories",
        description = "Fetches all the categories"
    )
    @GetMapping("/api/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    @Operation(
        summary = "List of a recommendations by category id",
        description = "Lists all the reading recommendations of a category id"
    )
    @GetMapping("/api/categories/{categoryId}/recommendations")
    public ResponseEntity<?> getRecommendationsByCategoryId(@PathVariable Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        List<ReadingRecommendation> recommendations = readingRecommendationRepository
                .findByCategoryOrderByCreatedAtDesc(category);
        if (recommendations.isEmpty()) {
            return new ResponseEntity<>("No recommendations found for the category", HttpStatus.OK);
        }
        return new ResponseEntity<>(recommendations, HttpStatus.OK);
    }
}