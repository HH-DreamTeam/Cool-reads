package fi.haagahelia.coolreads.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadingRecommendationController {

    @GetMapping("/add-recommendation")
    public String showAddRecommendationForm(Model model) {
        return "addRecommendation";
    }

}
