package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/add-category")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/add-category")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addCategory";
        }
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            model.addAttribute("errorMessage", "Category already exists.");
            return "addCategory";
        }
        categoryRepository.save(category);
        return "redirect:/";
    }

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categories", categories);
        return "categoryList";
    }
}
