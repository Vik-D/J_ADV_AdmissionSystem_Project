package ua.lviv.lgs.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.lviv.lgs.project.domain.User;
import ua.lviv.lgs.project.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			HttpServletRequest req) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);
		req.setAttribute("list", "Welcome!");
		return "redirect:/home";
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Your username or password is invalid");
		}
		if (logout != null) {
			model.addAttribute("message", "You have been logged out successfully.");
		}
		return "login";
	}

	/*
	 * @GetMapping("/applicants") public String listAllApplicants(HttpServletRequest req) {
	 *  req.setAttribute("mode", "APPLICANTS_LIST"); 
	 *  req.setAttribute("list", "Applicants list");
	 *  return "home"; 
	 *  }
	 */

	@GetMapping("/users")
	public String listAllApplicants(HttpServletRequest req) {
		req.setAttribute("users_list", userService.getAllUsersBySurnameAlphabeticalOrder());
		req.setAttribute("mode", "USERS_LIST");
		req.setAttribute("list", "Registered users list");
		return "home";
	}
	
	@RequestMapping(value ="/home", method = RequestMethod.GET)
	public String welcome(Model model, HttpServletRequest req) {
		req.setAttribute("list", "Welcome!");
        return "home";
    }

}
