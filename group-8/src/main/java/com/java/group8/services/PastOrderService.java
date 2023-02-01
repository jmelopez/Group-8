package com.java.group8.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.group8.models.PastOrder;
import com.java.group8.models.User;
import com.java.group8.repository.PastOrderRepo;

@Service
public class PastOrderService {

	@Autowired
	PastOrderRepo pastOrderRepo;
	
	public List<PastOrder> findByUser(User customer) {
		return pastOrderRepo.findByCustomer(customer);
	}
	
	public Optional<PastOrder> findById(Long id) {
		return pastOrderRepo.findById(id);
	}
	
	public PastOrder savePastOrder(PastOrder savePastOrder) {
		return pastOrderRepo.save(savePastOrder);
	}
	
	public void DeletePastOrder(Long id) {
		pastOrderRepo.deleteById(id);
	}
	
	
}
