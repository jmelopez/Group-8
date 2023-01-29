package com.java.group8.services;

import java.util.List;

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

}
