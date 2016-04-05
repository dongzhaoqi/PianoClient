package com.piano.bean;

import java.io.Serializable;

public class VerifiedUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String school;
	private String bitthdate;
	private String gender;
	private String lastLoginTime;
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	private boolean selected;
	
	public VerifiedUser(String userName, boolean selected) {
		super();
		this.userName = userName;
		this.selected = selected;
	}
	
	public VerifiedUser(String userName, String school, String bitthdate,
			String gender, String lastLoginTime,boolean selected) {
		super();
		this.userName = userName;
		this.school = school;
		this.bitthdate = bitthdate;
		this.gender = gender;
		this.lastLoginTime = lastLoginTime;
		this.selected = selected;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getBitthdate() {
		return bitthdate;
	}

	public void setBitthdate(String bitthdate) {
		this.bitthdate = bitthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
