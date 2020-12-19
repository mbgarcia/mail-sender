package com.example.demo.service;

import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailMessage {

	@Singular
	private Set<String> recipients;

	@NonNull
	private String subject;

	@NonNull
	private String template;
	
	@Singular("property")
	private Map<String, Object> properties;
}
