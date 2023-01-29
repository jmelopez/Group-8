package com.java.group8.services;



import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.java.group8.models.LoginUser;
import com.java.group8.models.User;
import com.java.group8.repository.UserRepo;



@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> inputUser = userRepo.findByEmail(newUser.getEmail());
		if(result.hasErrors()) {
			return null;
		} else {
			if(inputUser.isPresent()) {
				result.rejectValue("email", "duplicate", "An account is already registered with this email address. Please log in.");
				return null;
			} else if (!newUser.getPassword().equals(newUser.getConfirm())) {
				result.rejectValue("confirm", "matches", "The passwords entered do not match.");
				return null;
			} else {
				String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
				newUser.setPassword(hashed);
				return userRepo.save(newUser);
			}
		}
	}

	public User login(LoginUser newLoginObject, BindingResult result, User user) {
		if(result.hasErrors()) {
			return null;
		} else if(user == null) {
			result.rejectValue("email", "matches", "The email/password entered is invalid.");
			return null;
		} else {
			if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
				result.rejectValue("password", "matches", "You have entered an invalid username or password.");
				return null;
			} else {
				return user;
			}
		}
	}
	
	public User findUserByEmail(String email) {
		Optional<User> user = userRepo.findByEmail(email);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	public User getById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	public User updateUser(User updateUser) {
		return userRepo.save(updateUser);
	}
	
}
