package cplayer.user.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cplayer.user.app.model.User;
import cplayer.user.app.service.UserService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * http://localhost:8000/api/user/pro/token (Get)
	 * End point for getting user information from the DB
	 * If getting successfully returning status as Created (201)
	 * Otherwise returning status as Conflict
	 */
	@GetMapping("/pro/token")
	public ResponseEntity<?> getUser(@RequestParam("username") String username) {
		User user = userService.getUser(username);
		if (userService.getUser(username) != null) {
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else
			return new ResponseEntity<String>("no", HttpStatus.CONFLICT);

	}

	/*
	 * http://localhost:8000/api/user/pro/token (Put)
	 * End point for updating the information of a user
	 * If updating successfully returning status as OK
	 * Otherwise returning status as Conflict
	 */
	@PutMapping("/pro/token")
	public ResponseEntity<?> updateUser(@RequestBody User user, @RequestParam("username") String username) {
		try {
			if (userService.updateUser(user, username)) {
				return new ResponseEntity<String>("ok", HttpStatus.OK);
			} else
				return new ResponseEntity<String>("no", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>("no", HttpStatus.NOT_FOUND);
		}

	}

	/*
	 * http://localhost:8000/api/user/pro/token (Delete)
	 * End point for deleting all the user information from DB
	 * If deleted successfully returning status as OK
	 * Otherwise returning status as Conflict
	 */
	@DeleteMapping("/pro/token")
	public ResponseEntity<?> delUser(@RequestParam("username") String username) {
		if (userService.deleteUser(username)) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("no", HttpStatus.CONFLICT);
	}

	/*
	 * http://localhost:8000/api/user (Post)
	 * End point for saving the information of user in DB
	 * If added successfully returning status as Created
	 * Otherwise returning status as Conflict
	 */
	@PostMapping
	public ResponseEntity<?> adduser(@RequestBody User user) {
		if (userService.addUser(user)) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("no", HttpStatus.CONFLICT);
	}

}
