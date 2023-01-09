package cplayer.userauth.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Table name will be User
@Entity
@Table(name="user")
public class User {
	
	/*
	 * Defining the columns in the table
	 * Marking username of type string as Primary Key
	 */
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String username;
	private String password;
	
	/*
	 * Creating Getters and Setters
	*/
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public User() {
		
	}
	public User(String username, String password) {
		
		this.username = username;
		this.password = password;
	}
	/*
	 * overriding the toString Method
	*/
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
		
}
