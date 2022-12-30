package com.luv2code.springboot.BlogApp.Dto;

public class SignIn {
	
	
	
	private String usernameoremail;
	
	
	
	private String password;



	public String getUsernameoremail() {
		return usernameoremail;
	}



	public void setUsernameoremail(String usernameoremail) {
		this.usernameoremail = usernameoremail;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public SignIn(String usernameoremail, String password) {
		super();
		this.usernameoremail = usernameoremail;
		this.password = password;
	}



	@Override
	public String toString() {
		return "SignIn [usernameoremail=" + usernameoremail + ", password=" + password + "]";
	}
	
	
	public SignIn() {
		
	}
	
	

}
