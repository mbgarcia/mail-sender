package com.example.demo.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class MailModel {
	
	@NonNull
	protected String username;
	
	@NonNull
	protected String company;
	
	@NonNull
	protected Integer year;
	
	@NonNull
	protected String productName;
	
	@NonNull
	protected String address;
	
	@NonNull
	protected String team;
	
	@NonNull
	protected String support;
	
	@NonNull
	protected String productUrl;
	
	private String message;
}