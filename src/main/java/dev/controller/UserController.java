package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.svc.UserSvc;
import dev.entity.User;

@RestController
@RequestMapping("/multiTenantApp")
public class UserController {

	@Autowired
	UserSvc userSvc;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userSvc.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
