package com.java.group8.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.group8.models.PizzaOrder;
import com.java.group8.models.User;

@Repository
public interface PizzaOrderRepo extends CrudRepository<PizzaOrder, Long> {
	List<PizzaOrder> findByCustomer(User customer);
}
