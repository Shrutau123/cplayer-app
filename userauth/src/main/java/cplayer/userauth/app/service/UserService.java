package cplayer.userauth.app.service;

import cplayer.userauth.app.model.User;

// This is an interface which is implemented by userServiceImpl class
public interface UserService {
	
	public boolean deleteUser(String u);
	public boolean addUser(User u);
	public boolean validate(String username,String password);
	public boolean updateUser(String username, String oldpass, String newpass);
}
