package com.java.group8.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.group8.models.PizzaOrder;
import com.java.group8.services.PizzaOrderService;
import com.java.group8.services.UserService;

@Controller
public class PizzaOrderController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PizzaOrderService pizzaServ;
	
	
	@GetMapping("/craftapizza")
	public String craftPizza(@ModelAttribute("newPizzaOrder") PizzaOrder newPizzaOrder, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		return "craftapizza.jsp";
	}
	
	@PostMapping("/craftapizza/new")
	public String craftPizzaNew(@Valid @ModelAttribute("newPizzaOrder") PizzaOrder newPizzaOrder, BindingResult result, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "home.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		
		if (result.hasErrors()) {
			return "craftapizza.jsp";
		}
		
		newPizzaOrder.setCustomer(userServ.getById(uid)); // Gets only PizzaOrder's by logged in user.
		pizzaServ.createPizzaOrder(newPizzaOrder);
		
		return "redirect:/order";
	}
	
	@GetMapping("/order")
	public String order(@ModelAttribute("order") PizzaOrder order, HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		// Needs to be a Service. I built it out quickly just to see it display.
		Integer currentOrder = pizzaServ.findByUser(userServ.getById(uid)).size()-1; // Gets most recent order
		
		if (currentOrder >=0 ) {
			String crustType = pizzaServ.findByUser(userServ.getById(uid)).get(currentOrder).getCrust(); // Grabs details of the order
			String methodType = pizzaServ.findByUser(userServ.getById(uid)).get(currentOrder).getDeliveryMethod();
			String sizeType = pizzaServ.findByUser(userServ.getById(uid)).get(currentOrder).getSize();
			Integer qty = pizzaServ.findByUser(userServ.getById(uid)).get(currentOrder).getQuantity();
			order.setCrust(crustType); // Sets details for a view model
			order.setQuantity(qty);
			order.setDeliveryMethod(methodType);
			order.setSize(sizeType);
			return "order.jsp"; 
		} else { // If the User has never ordered before:
			return "redirect:/craftapizza";
		}
	}
	
	
	@GetMapping("/craftapizza/random")
	public String craftRandomPizza(@ModelAttribute("newRandomPizza") PizzaOrder newRandomPizza, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		
		String randomCrust = pizzaServ.createRandomPizzaOrder().getCrust();
		String randomSize = pizzaServ.createRandomPizzaOrder().getSize();
		
		newRandomPizza.setCrust(randomCrust);
		newRandomPizza.setSize(randomSize);
		
		return "craftapizza_random.jsp";
	}
	
}
