package cplayer.recommended.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recommended {
	
	@Id
	private String name;           //Getting player name to be stored as private

//	private int id;               	
	private String pid;               //Getting player id to be stored as private
	private String country;        //Getting player country to be stored as private
	private String placeOfBirth;     //Getting info about major teams to be stored as private
	private String dateOfBirth;     //Getting player currentAge to be stored as private
	private String playerImg;       //Getting player imageURL to be stored as private
	private String role;
	private int count;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Recommended [pid=" + pid + ", country=" + country + ", name=" + name + ", placeOfBirth=" + placeOfBirth
				+ ", dateOfBirth=" + dateOfBirth + ", playerImg=" + playerImg + ", role=" + role + ", count=" + count
				+ "]";
	}
	
	
}
