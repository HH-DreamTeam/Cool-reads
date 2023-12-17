package fi.haagahelia.coolreads.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.dto.RegisterUserDto;
import fi.haagahelia.coolreads.model.User;
import fi.haagahelia.coolreads.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String renderRegisterForm(Model model) {
		model.addAttribute("user", new RegisterUserDto());

		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") RegisterUserDto user, BindingResult bindingResult,
			Model model) {
		Optional<User> existingUser = userRepository.findOneByUsername(user.getUsername());

		if (existingUser.isPresent()) {
			bindingResult.rejectValue("username", "UsernameTaken",
					"This username is already taken. Choose another one");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}

		String passwordHash = passwordEncoder.encode(user.getPassword());
		User newUser = new User(user.getUsername(), passwordHash, "USER");
		userRepository.save(newUser);

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}