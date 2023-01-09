package cplayer.favourites.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="favourite")
public class Favourites {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String pid;               //Getting player id to be stored as private
	private String country;        //Getting player country to be stored as private
	private String name;           //Getting player name to be stored as private
	private String placeOfBirth;     //Getting info about major teams to be stored as private
	private String dateOfBirth;     //Getting player currentAge to be stored as private
	private String playerImg;       //Getting player imageURL to be stored as private
	private String role;		//Getting player role to be stored as private
	private boolean status;
	
	
	
	public Favourites() {
		super();
	}

	public Favourites(int id, String username, String pid, String country, String name, String placeOfBirth,
			String dateOfBirth, String playerImg, String role, boolean status) {
		super();
		this.id = id;
		this.username = username;
		this.pid = pid;
		this.country = country;
		this.name = name;
		this.placeOfBirth = placeOfBirth;
		this.dateOfBirth = dateOfBirth;
		this.playerImg = playerImg;
		this.role = role;
		this.status = status;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPlayerImg() {
		return playerImg;
	}
	public void setPlayerImg(String playerImg) {
		this.playerImg = playerImg;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Favourites [id=" + id + ", username=" + username + ", pid=" + pid + ", country=" + country + ", name="
				+ name + ", placeOfBirth=" + placeOfBirth + ", dateOfBirth=" + dateOfBirth + ", playerImg=" + playerImg
				+ ", role=" + role + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
			
}
