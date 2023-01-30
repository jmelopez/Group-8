package com.java.group8.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.group8.models.PastOrder;
import com.java.group8.models.PizzaOrder;
import com.java.group8.services.PastOrderService;
import com.java.group8.services.PizzaOrderService;
import com.java.group8.services.UserService;

@Controller
public class PizzaOrderController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PizzaOrderService pizzaServ;
	
	@Autowired
	private PastOrderService pastOrderServ;
	
	
	@GetMapping("/craftapizza")
	public String craftPizza(@ModelAttribute("newPizzaOrder") PizzaOrder newPizzaOrder, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size());
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
	public String order(@ModelAttribute("checkoutOrder") PizzaOrder checkoutOrder, HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		// Needs to be a Service. I built it out quickly just to see it display.
		Integer currentOrder = pizzaServ.findByUser(userServ.getById(uid)).size()-1; // Gets most recent order
		
		model.addAttribute("currentOrders", pizzaServ.findByUser(userServ.getById(uid)));
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size());
		
		if (currentOrder >=0 ) {
			return "order.jsp"; 
		} else { // If the User has never ordered before:
			return "redirect:/craftapizza";
		}
	}

	@PostMapping("/checkout")
	public String checkout(Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}

		for (int i=0; i < pizzaServ.findByUser(userServ.getById(uid)).size(); i++) {
			PizzaOrder pizzaOrder = pizzaServ.findByUser(userServ.getById(uid)).get(i);
			PastOrder pastOrder = new PastOrder();
			pastOrder.setCrust(pizzaOrder.getCrust());
			pastOrder.setSize(pizzaOrder.getSize());
			pastOrder.setDeliveryMethod(pizzaOrder.getDeliveryMethod());
			pastOrder.setQuantity(pizzaOrder.getQuantity());
			pastOrder.setFavorite(false);
			pastOrder.setCustomer(userServ.getById(uid));
			pastOrderServ.savePastOrder(pastOrder);
			pizzaServ.DeletePizzaOrder(pizzaServ.findByUser(userServ.getById(uid)).get(i).getId());
		}

		return "redirect:/home";
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
		
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size());
		return "craftapizza_random.jsp";
	}
	
	@GetMapping("/deleteorder/{id}")
	@DeleteMapping("/deleteorder/{id}")
	public String deleteOrder(@PathVariable Long id, HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}

		pizzaServ.DeletePizzaOrder(id);
		
		return "redirect:/home";
	}
	
	@GetMapping("/deletePastOrder/{id}")
	@DeleteMapping("/deletePastOrder/{id}")
	public String deletePastOrder(@PathVariable Long id, HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		pastOrderServ.DeletePastOrder(id);
		
		return "redirect:/home";
	}
	
}
