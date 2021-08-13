package dev.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.entity.User;
import dev.repo.UserRepo;

@Service
public class UserSvc {

	@Autowired
	UserRepo userRepo;

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
