package com.java.group8.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.group8.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
