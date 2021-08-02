package com.example.demo.resource;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
})
public class KitchenRegistrationDto {

	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 50)
	private String name;
	
	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 50)
	private String email;
	
	@NotNull
	@Min(value = 10)
	private String password;
	
	@NotNull
	@Min(value = 10)
	private String confirmPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
