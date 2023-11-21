package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

}
