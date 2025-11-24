package com.ritualcoffee.crm.dto;

import jakarta.validation.constraints.*;

public class LoginRequest {

	@NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;
    private String password;
    
    // getters y setters
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


    
}
