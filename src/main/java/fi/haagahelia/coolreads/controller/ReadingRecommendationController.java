package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import fi.haagahelia.coolreads.model.ReadingRecommendation;


import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

@Controller
public class ReadingRecommendationController {
    @Autowired
    private ReadingRecommendationRepository recommendationRepository;

    @GetMapping("/add-recommendation")
    public String showAddRecommendationForm(Model model) {
        return "addRecommendation";
    }

    @GetMapping("/")
    public String showRecommendationList(Model model){
        List<ReadingRecommendation> recomendations = recommendationRepository.findAll();
        model.addAttribute("recomendations", recomendations);

        return "recommendationList";
    }

}
