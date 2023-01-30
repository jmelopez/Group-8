package com.java.group8.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.group8.models.PastOrder;
import com.java.group8.models.User;

@Repository
public interface PastOrderRepo extends CrudRepository<PastOrder, Long> {
	List<PastOrder> findByCustomer(User customer);
	
}
