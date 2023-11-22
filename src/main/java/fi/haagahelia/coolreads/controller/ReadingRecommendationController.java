package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import jakarta.validation.Valid;

@Controller
public class ReadingRecommendationController {

    @Autowired
    private ReadingRecommendationRepository recommendationRepository;

    @GetMapping("/add-recommendation")
    public String showAddRecommendationForm(Model model) {
        model.addAttribute("recommendation", new ReadingRecommendation());
        return "addRecommendation";
    }

    @PostMapping("/add-recommendation")
    public String addRecommendation(@Valid @ModelAttribute ReadingRecommendation recommendation, BindingResult result) {
        if (result.hasErrors()) {
            return "addRecommendation";
        }
        recommendationRepository.save(recommendation);
        return "redirect:/";
    }

    @GetMapping("/")
    public String listRecommendations(Model model) {
        List<ReadingRecommendation> recommendations = recommendationRepository.findAll();
        model.addAttribute("recommendations", recommendations);
        return "recommendationList";
    }

    @GetMapping("/recommendations/edit/{id}")
    public String showEditRecommendationForm(@PathVariable Long id, Model model) {
        ReadingRecommendation recommendation = recommendationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recommendation id: " + id));

        model.addAttribute("recommendation", recommendation);
        return "editRecommendation";
    }

    @PostMapping("/recommendations/edit/{id}")
    public String editRecommendation(@PathVariable Long id,
            @Valid @ModelAttribute ReadingRecommendation editedRecommendation,
            BindingResult result) {
        if (result.hasErrors()) {
            return "editRecommendation";
        }
        ReadingRecommendation existingRecommendation = recommendationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recommendation id: " + id));

        existingRecommendation.setTitle(editedRecommendation.getTitle());
        existingRecommendation.setLink(editedRecommendation.getLink());
        existingRecommendation.setDescription(editedRecommendation.getDescription());

        recommendationRepository.save(existingRecommendation);

        return "redirect:/";
    }

    @PostMapping("/recommendations/{id}/delete")
    public String deleteRecommendation(@PathVariable Long id) {
        recommendationRepository.deleteById(id);
        return "redirect:/";
    }

}
