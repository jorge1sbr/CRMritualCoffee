package com.ritualcoffee.crm.dto;

import jakarta.validation.constraints.*;

public class RegistroRequest {

	@NotBlank(message = "El nombre es obligatorio")
    private String nombre;
	@NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;
	@NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;
	@NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
    private String password;
	@NotBlank(message = "La dirección es obligatoria")
    private String direccion;
	 @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;
    
 // getters y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

    
    
}
