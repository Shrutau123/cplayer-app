package cplayer.userauth.app.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cplayer.userauth.app.model.User;
import cplayer.userauth.app.repository.UserRepository;

// This class implements an abstract interface
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	/* Method to Delete the user info
	 * Checking if the user exists and if it exists then deleting it and returning true
	 * Otherwise returning false
	*/
	@Override
	public boolean deleteUser(String username) {
		if(userRepository.findById(username) != null) {
			userRepository.deleteById(username);
			return true;
		}
		else return false;
		
	}
	
	/* Method to Add the user info of new user
	 * Using save() method of CRUD Repository interface
	 * Returning True if the user info is saved
	 * Otherwise returning false in case any exception occurs or if the user already exists
	*/
	@Override
	public boolean addUser(User user) {
		try {
			if(!userRepository.existsById(user.getUsername())) {
				userRepository.save(user);
				return true;
			}
			else return false;
		}catch(Exception e) {
			return false;
		}
	}
	
	/* Method to Check if the username and password matches
	 * Returning True if the credentials correct
	 * Otherwise return false
	*/
	@Override
	public boolean validate(String username, String password) {
		if(userRepository.validate(username, password) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	/* Method to update the password of user
	 * Checking if the old password provided by him is correct
	 * Updating and Returning True if the credentials correct
	 * Otherwise return false
	*/
	@Override
	public boolean updateUser(String username, String oldpass, String newpass) {
		if(userRepository.existsById(username)) {
			if(userRepository.validate(username, oldpass) != null) {
				try {
					userRepository.deleteById(username);
					User u = new User();
					u.setUsername(username);
					u.setPassword(newpass);
					userRepository.save(u);
					return true;
				}catch (Exception e) {
					return false;
				}
			}
			else return false;
		}
		else return false;
	}
	
	
	
}
