package com.java.group8.controllers;



import java.util.List;
import java.util.ListIterator;

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
import com.java.group8.models.PastOrder;
import com.java.group8.models.User;
import com.java.group8.services.PastOrderService;
import com.java.group8.services.PizzaOrderService;
import com.java.group8.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PizzaOrderService pizzaServ;
	
	@Autowired
	private PastOrderService pastOrderServ; 
	
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
	
	@GetMapping("/home") // returns home.jsp, with buttons for ordering new, random and re-ordering favorite pizzas
	public String dashboard(HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid)); // boilerplate userID validation
		}
		
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size()); // Used to show Navbar items in order
		
		model.addAttribute("favorite", false); // "favorite" is used in home.jsp to check if a favorite has been set. It's False by default.
		
		List<PastOrder> pastOrders = pastOrderServ.findByUser(userServ.getById(uid)); // Grab all past orders, see if any of them are "favorite"
		
		ListIterator<PastOrder> it = pastOrders.listIterator(); // list iterator to iterate through list of past orders, looking for favorite order.
		
		while (it.hasNext() ) { // "it" iterator runs through list of past orders until it reaches the end of the list.
			if (it.next().getFavorite() == true) { // if any of the PastOrder objects have their favorite boolean set to true...
				model.addAttribute("favorite", true); // set the model attribute named "favorite", which shows up in the home.jsp, to true.
			}											// "favorite" is used in a c:if in home.jsp, and changes the re-order fav button to read "no fav" if no favorites are found.
		}
		return "home.jsp";
	}
	
	@GetMapping("/account/{id}") // returns account.jsp, shows users previously checked out orders, or, "past orders" and editable account details
	public String account(@PathVariable Long id, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		model.addAttribute("pastOrders", pastOrderServ.findByUser(userServ.getById(uid))); // used for displaying past orders through a c:forEach in account.jsp
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size()); // used for NavBar, shows orders in cart
		return "account.jsp";
	}
	
	@PutMapping("/account/{id}/edit") // edits user account details, request sent from account.jsp
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
		userServ.updateUser(editUser); // saves user account details
		return "redirect:/home";
	}
	
}
