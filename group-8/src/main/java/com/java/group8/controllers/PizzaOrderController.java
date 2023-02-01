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
import org.springframework.web.bind.annotation.PutMapping;

import com.java.group8.models.PastOrder;
import com.java.group8.models.PizzaCalc;
import com.java.group8.models.PizzaOrder;
import com.java.group8.services.PastOrderService;
import com.java.group8.services.PizzaOrderService;
import com.java.group8.services.UserService;

@Controller
public class PizzaOrderController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PizzaOrderService pizzaServ; // orders that have never been through checkout are "PizzaOrder" and use this service
	
	@Autowired
	private PastOrderService pastOrderServ; // orders that HAVE been checked out are "PastOrder" and use this service
	
	
	@GetMapping("/craftapizza") // Manual PizzaOrder creation, displays craftapizza.jsp
	public String craftPizza(@ModelAttribute("newPizzaOrder") PizzaOrder newPizzaOrder, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size()); // used for NavBar, displays total orders in cart
		return "craftapizza.jsp";
	}
	
	@PostMapping("/craftapizza/new") // Saves a new PizzaOrder and sets "Customer" using User Id. "Customer" is used in the one to many the relationship mapping.
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
		
		newPizzaOrder.setCustomer(userServ.getById(uid)); // Sets Customer by UserID
		pizzaServ.createPizzaOrder(newPizzaOrder); // Saves a new Pizza Order to the Database
		
		return "redirect:/order";
	}
	
	@GetMapping("/order") // Checkout screen. Will return user to craftapizza.jsp instead if there are no items in the cart.
	public String order(@ModelAttribute("checkoutOrder") PizzaOrder checkoutOrder, HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size());// Used for Navbar, displays # items in cart
		
		model.addAttribute("currentOrders", pizzaServ.findByUser(userServ.getById(uid))); 
		Integer currentOrder = pizzaServ.findByUser(userServ.getById(uid)).size()-1; // Gets most recent order.
		
		Double totalPrice = 0.0;
		for (int i=0; i < pizzaServ.findByUser(userServ.getById(uid)).size(); i++) {
			PizzaOrder aPizza = pizzaServ.findByUser(userServ.getById(uid)).get(i);
			PizzaCalc pizzaCalc = new PizzaCalc();
			totalPrice += pizzaCalc.calculatePrice(aPizza.getDeliveryMethod(), aPizza.getSize(), aPizza.getCrust(),
					aPizza.getQuantity(), userServ.getById(uid).getState());
			
		}

		model.addAttribute("totalPrice", totalPrice);
		
		if (currentOrder >=0 ) {// Checks if there are any items in the cart
			return "order.jsp"; // Returns order.jsp if at least 1 order exists
		} else { // If the User has never ordered before:
			return "redirect:/craftapizza"; // Returns them to the craftapizza.jsp, where they make a manual order.
		}
	}

	@PostMapping("/checkout") // Removes ALL PizzaOrders, converts them to PastOrders
	public String checkout(Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}

		for (int i=0; i < pizzaServ.findByUser(userServ.getById(uid)).size(); i++) { // PizzaOrder to PastOrder converter
			if (pizzaServ.findByUser(userServ.getById(uid)).get(i).getFavorite() == false) { // If PizzaOrder has not been saved as a favorite.
				PizzaOrder pizzaOrder = pizzaServ.findByUser(userServ.getById(uid)).get(i);
				PastOrder pastOrder = new PastOrder(); // Sets up new PastOrder for this iteration
				pastOrder.setCrust(pizzaOrder.getCrust()); // Sets PizzaOrder Crust to PastOrder Crust
				pastOrder.setSize(pizzaOrder.getSize()); // Sets Size
				pastOrder.setDeliveryMethod(pizzaOrder.getDeliveryMethod()); // Sets DeliveryMethod 
				pastOrder.setQuantity(pizzaOrder.getQuantity()); // Sets Quantity
				pastOrder.setFavorite(false); // Sets favorite to false manually - this keeps it from saving favorite orders twice when checking out
				pastOrder.setCustomer(userServ.getById(uid)); // Sets customer from PizzaOrder customer
				//TOPPINGS:
				pastOrder.setHasPepperoni(pizzaOrder.getHasPepperoni());
				pastOrder.setHasSausage(pizzaOrder.getHasSausage());
				pastOrder.setHasMushrooms(pizzaOrder.getHasMushrooms());
				pastOrder.setHasExtraCheese(pizzaOrder.getHasExtraCheese());
				pastOrder.setHasOnions(pizzaOrder.getHasOnions());
				pastOrder.setHasAnchovies(pizzaOrder.getHasAnchovies());
				pastOrder.setHasEggplant(pizzaOrder.getHasEggplant());
				pastOrder.setHasBroccoli(pizzaOrder.getHasBroccoli());
				pastOrder.setHasPineApple(pizzaOrder.getHasPineApple());
				pastOrder.setHasArtichokes(pizzaOrder.getHasArtichokes());

				pastOrderServ.savePastOrder(pastOrder); // Finally, once it's done converting PizzaOrder to a PastOrder, it saves the PastOrder
			}
		}

		for (int i=0; i < pizzaServ.findByUser(userServ.getById(uid)).size(); i++) {
			pizzaServ.DeletePizzaOrder(pizzaServ.findByUser(userServ.getById(uid)).get(i).getId()); // Empties the checkout of PizzaOrders, deleting them all. They live on as PastOrders
		}
		
		if (pizzaServ.findByUser(userServ.getById(uid)).size() >0) { // Brute force, delete the last PizzaOrder in list (was giving me trouble for some reason)...
			pizzaServ.DeletePizzaOrder(pizzaServ.findByUser(userServ.getById(uid)).get(0).getId()); // Ugly but it works, clears cart on checkout
		}
		
		return "redirect:/home"; // Returns home, because returning to Order would redirect to home anyway if there's nothing to order.
	}
	
	@GetMapping("/craftapizza/random") // Returns craftapizza_random.jsp, which randomly creates a pizza (but not delivery or quantity)
	public String craftRandomPizza(@ModelAttribute("newRandomPizza") PizzaOrder newRandomPizza, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size()); // used for navbar, displays # items in cart
		
		newRandomPizza.setCrust(pizzaServ.createRandomPizzaOrder().getCrust()); // sets random crust
		newRandomPizza.setSize(pizzaServ.createRandomPizzaOrder().getSize()); // sets random size
		//TOPPINGS:
		newRandomPizza.setHasPepperoni(pizzaServ.createRandomPizzaOrder().getHasPepperoni());
		newRandomPizza.setHasSausage(pizzaServ.createRandomPizzaOrder().getHasSausage());
		newRandomPizza.setHasMushrooms(pizzaServ.createRandomPizzaOrder().getHasMushrooms());
		newRandomPizza.setHasExtraCheese(pizzaServ.createRandomPizzaOrder().getHasExtraCheese());
		newRandomPizza.setHasOnions(pizzaServ.createRandomPizzaOrder().getHasOnions());
		newRandomPizza.setHasAnchovies(pizzaServ.createRandomPizzaOrder().getHasAnchovies());
		newRandomPizza.setHasEggplant(pizzaServ.createRandomPizzaOrder().getHasEggplant());
		newRandomPizza.setHasArtichokes(pizzaServ.createRandomPizzaOrder().getHasArtichokes());
		newRandomPizza.setHasBroccoli(pizzaServ.createRandomPizzaOrder().getHasBroccoli());
		newRandomPizza.setHasPineApple(pizzaServ.createRandomPizzaOrder().getHasPineApple());
	
		newRandomPizza.setCustomer(userServ.getById(uid)); // Sets Customer based on logged in UserID
		
		return "craftapizza_random.jsp";
	}
	
	@GetMapping("/deleteorder/{id}")
	@DeleteMapping("/deleteorder/{id}") // deletes a Pizza Order when you click "remove from order" in order.jsp
	public String deleteOrder(@PathVariable Long id, HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}

		pizzaServ.DeletePizzaOrder(id); // slays the pizza
		
		return "redirect:/home";
	}
	
	@GetMapping("/deletePastOrder/{id}")
	@DeleteMapping("/deletePastOrder/{id}") // removes a "PastOrder" from account.jsp in the Past Orders list displayed there
	public String deletePastOrder(@PathVariable Long id, HttpSession session, Model model) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		
		pastOrderServ.DeletePastOrder(id); // removes past order with a vengence
		return "redirect:/account/{id}";
	}

	@PutMapping("/favorite/{id}") // Sets a PastOrder as a "Favorite". Only 1 PastOrder can be set this way.
	public String editNPC(@Valid @ModelAttribute("fav") PastOrder pastOrder, BindingResult result, Model model, @PathVariable Long id,
			HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		
		PastOrder thisPastOrder; // sets up empty PastOrder to take in what the previous PastOrder was + Favorite set to True
		for (int i = 0; i < pastOrderServ.findByUser(userServ.getById(uid)).size(); i++) { // Sets ALL past orders to favorite = false
			thisPastOrder = pastOrderServ.findByUser(userServ.getById(uid)).get(i); 
			thisPastOrder.setFavorite(false); // there can only be one!
		}
		
		pastOrder.setFavorite(true); // Once all other PastOrders favorite booleans = false, it sets the PastOrder with the ID assigned to the button as favorite
		pastOrder.setCrust(pastOrderServ.findById(pastOrder.getId()).get().getCrust()); // Converts PastOrder to cloned PastOrder, but it's a Favorite now
		pastOrder.setSize(pastOrderServ.findById(pastOrder.getId()).get().getSize()); // There's a better way to do this, but I couldn't figure it out!
		pastOrder.setDeliveryMethod(pastOrderServ.findById(pastOrder.getId()).get().getDeliveryMethod());
		pastOrder.setQuantity(pastOrderServ.findById(pastOrder.getId()).get().getQuantity());
		pastOrder.setCustomer((pastOrderServ.findById(pastOrder.getId()).get().getCustomer()));
		//Toppings:
		pastOrder.setHasPepperoni(pastOrderServ.findById(pastOrder.getId()).get().getHasPepperoni());
		pastOrder.setHasSausage(pastOrderServ.findById(pastOrder.getId()).get().getHasSausage());
		pastOrder.setHasMushrooms(pastOrderServ.findById(pastOrder.getId()).get().getHasMushrooms());
		pastOrder.setHasExtraCheese(pastOrderServ.findById(pastOrder.getId()).get().getHasExtraCheese());
		pastOrder.setHasOnions(pastOrderServ.findById(pastOrder.getId()).get().getHasOnions());
		pastOrder.setHasAnchovies(pastOrderServ.findById(pastOrder.getId()).get().getHasAnchovies());
		pastOrder.setHasEggplant(pastOrderServ.findById(pastOrder.getId()).get().getHasEggplant());
		pastOrder.setHasArtichokes(pastOrderServ.findById(pastOrder.getId()).get().getHasArtichokes());
		pastOrder.setHasBroccoli(pastOrderServ.findById(pastOrder.getId()).get().getHasBroccoli());
		pastOrder.setHasPineApple(pastOrderServ.findById(pastOrder.getId()).get().getHasPineApple());
		
		pastOrderServ.savePastOrder(pastOrder); // Once Crust, Size, DeliveryMethod, Quantity and Customer is shared, it saves the PastOrder again, but with Fav = true.
		
		return "redirect:/account/{id}";
	}
	
	@GetMapping("/craftapizza/favorite") // Returns craftapizza_favorite.jsp, only if a favorite exists - the button is locked unless the User selects a favorite Pizza.
	public String craftFavoritePizza(@ModelAttribute("favoritePizza") PizzaOrder favoritePizza, PastOrder thisPastOrder, Model model, HttpSession session) {
		Long uid = (Long) session.getAttribute("userId");
		if(uid == null) {
			return "error.jsp";
		} else  {
			model.addAttribute("user", userServ.getById(uid));
		}
		model.addAttribute("totalOrders", pizzaServ.findByUser(userServ.getById(uid)).size()); //shows number of orders in navbar
		
		for (int i = 0; i < pastOrderServ.findByUser(userServ.getById(uid)).size(); i++) { // run through the list of past orders
			thisPastOrder = pastOrderServ.findByUser(userServ.getById(uid)).get(i); // grab one
			if (thisPastOrder.getFavorite() == true) { // if the selected past order is grabbed -->
				favoritePizza.setDeliveryMethod(thisPastOrder.getDeliveryMethod()); // start converting PastOrder BACK to a PizzaOrder
				favoritePizza.setQuantity(thisPastOrder.getQuantity()); // It has to do this, because PizzaOrders are what the app operates on during creation
				favoritePizza.setCrust(thisPastOrder.getCrust());
				favoritePizza.setSize(thisPastOrder.getSize());
				favoritePizza.setFavorite(true); // Set the PizzaOrder to true - which is normally impossible.
				//Toppings:
				favoritePizza.setHasPepperoni(thisPastOrder.getHasPepperoni());
				favoritePizza.setHasSausage(thisPastOrder.getHasSausage());
				favoritePizza.setHasMushrooms(thisPastOrder.getHasMushrooms());
				favoritePizza.setHasExtraCheese(thisPastOrder.getHasExtraCheese());
				favoritePizza.setHasOnions(thisPastOrder.getHasOnions());
				favoritePizza.setHasAnchovies(thisPastOrder.getHasAnchovies());
				favoritePizza.setHasEggplant(thisPastOrder.getHasEggplant());
				favoritePizza.setHasArtichokes(thisPastOrder.getHasArtichokes());
				favoritePizza.setHasBroccoli(thisPastOrder.getHasBroccoli());
				favoritePizza.setHasPineApple(thisPastOrder.getHasPineApple());
				// Setting the PizzaOrder to true here allows it to be culled later, so it doesn't add the favorite order to the PastOrders all over again.
			}
		}

		favoritePizza.setCustomer(userServ.getById(uid)); // Sets Customer at the end of the logic, for later look ups

		return "craftapizza_favorite.jsp";
	}


}
