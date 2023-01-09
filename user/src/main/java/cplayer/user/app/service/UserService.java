package cplayer.user.app.service;

import cplayer.user.app.model.User;

public interface UserService {
	public User getUser(String username);
	public boolean updateUser(User u, String username);
	public boolean addUser(User u);
	boolean deleteUser(String username);
}
