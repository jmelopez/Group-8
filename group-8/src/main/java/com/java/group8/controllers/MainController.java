package com.java.group8.controllers;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.java.group8.models.LoginUser;
import com.java.group8.models.User;
import com.java.group8.services.PizzaOrderService;
import com.java.group8.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PizzaOrderService pizzaServ;
	
	@GetMapping("/")
	public String login(Model model, HttpSession session) {
		//bind empty user and loginuser objects to jsp to capture input
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";		
	}
	
	@GetMapping("/register")
	public String newUser(Model model, HttpSession session) {
		model.addAttribute("newUser", new User());
		return "register.jsp";		
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		
		//TODO call a register method in service
		//TODO some extra validations and create new user
		
		User inputUser = userServ.register(newUser, result);
		if(result.hasErrors()) {
			//send in empty LoginUser before re-rendering page
			model.addAttribute("newLogin", new LoginUser());
			return "register.jsp";
		} else {
			//TODO later: store ID from DB in session (log them in)
			session.setAttribute("userId", inputUser.getId());
			return "redirect:/home";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User loggedUser = userServ.findUserByEmail(newLogin.getEmail());
		User user = userServ.login(newLogin, result, loggedUser);
		
		if(user == null) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		} else {
			session.setAttribute("userId", user.getId());
			return "redirect:/home";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/home")
	public String dashboard(HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		return "home.jsp";
	}
	
	@GetMapping("/account/{id}")
	public String account(@PathVariable Long id, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		return "account.jsp";
	}
	
	@PutMapping("/account/{id}/edit")
	public String editAccount(@PathVariable Long id, Model model, @Valid @ModelAttribute("user") User editUser, BindingResult result, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		
		if(result.hasErrors()) {
			return "account.jsp";
		}
		
		userServ.updateUser(editUser);
		return "redirect:/home";
	}
	
}
