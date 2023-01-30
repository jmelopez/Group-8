package com.java.group8.services;

import java.util.List;

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
	
	public PastOrder savePastOrder(PastOrder savePastOrder) {
		return pastOrderRepo.save(savePastOrder);
	}
	
	public void DeletePastOrder(Long id) {
		pastOrderRepo.deleteById(id);
	}
	
	
}
