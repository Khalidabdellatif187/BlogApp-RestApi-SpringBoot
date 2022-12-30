package com.luv2code.springboot.BlogApp.Dto;

public class JwtAuthResponse {
	
	private String accessToken;
	
	private String tokenType = "Bearer";

	public JwtAuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public String toString() {
		return "JwtAuthResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + "]";
	}
	
	
	public JwtAuthResponse() {
		
	}

}
