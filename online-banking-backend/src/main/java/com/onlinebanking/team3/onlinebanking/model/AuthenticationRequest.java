package com.onlinebanking.team3.onlinebanking.model;

public class AuthenticationRequest{
    private String phoneNumber;
    private String loginPassword;
    
    public AuthenticationRequest() {
    	
    }
    
	public AuthenticationRequest(String phoneNumber, String loginPassword) {
		super();
		this.phoneNumber = phoneNumber;
		this.loginPassword = loginPassword;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return loginPassword;
	}
	public void setPassword(String password) {
		this.loginPassword = password;
	}

    // Getters and setters
    
}
