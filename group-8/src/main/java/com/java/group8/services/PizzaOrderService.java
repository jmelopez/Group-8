package com.java.group8.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.group8.models.PizzaOrder;
import com.java.group8.models.User;
import com.java.group8.repository.PizzaOrderRepo;

@Service
public class PizzaOrderService {
	
	@Autowired
	PizzaOrderRepo pizzaOrderRepo;
	
	public List<PizzaOrder> findByUser(User customer) {
		return pizzaOrderRepo.findByCustomer(customer);
	}
	
	public PizzaOrder createPizzaOrder(PizzaOrder newPizzaOrder) {
		return pizzaOrderRepo.save(newPizzaOrder);
	}
	
	public PizzaOrder createRandomPizzaOrder() {
		
		Random random = new Random();
		final String[] sizes = {"Small","Medium","Large"};
		final String[] crusts = {"Thin", "Thick", "Stuffed"};
		int index = random.nextInt(sizes.length);
		String randomSize = sizes[index];
		index = random.nextInt(crusts.length);
		String randomCrust = crusts[index];
		
		PizzaOrder randomPizza = new PizzaOrder();
		
		randomPizza.setCrust(randomCrust);
		randomPizza.setSize(randomSize);
//		randomPizza.setDeliveryMethod("CarryOut");
//		randomPizza.setQuantity(1);
//		randomPizza.setFavorite(false);
		
		return randomPizza;
	}
	
	public void DeletePizzaOrder(Long id) {
		pizzaOrderRepo.deleteById(id);
	}

}
