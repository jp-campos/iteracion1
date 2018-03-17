package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hotel {
	
	
	
	@JsonProperty(value="capacidad")
	private int capacidad; 
	
	@JsonProperty(value="disponibilidad")
	private int disponibilidad;
	
	@JsonProperty(value = "registroCamaraComercio")
	private String registroCamaraComercio;
	
	@JsonProperty(value = "registroSuperIntendencia")
	private String registroSuperIntendencia; 
	
	@JsonProperty(value = "ubicacion")
	private String ubicacion;
	
	
	
	
	public Hotel()
	{
		
	}
	
	
	public Hotel(@JsonProperty(value="capacidad")int capacidad, @JsonProperty(value = "disponibilidad")int disponibilidad, @JsonProperty(value="registroCamaraComercio")String registroCamaraComercio,
			@JsonProperty(value="registroSuperIntendencia")String registroSuperIntendencia, @JsonProperty(value = "ubicacion")String ubicacion)
	{
		this.capacidad = capacidad; 
		this.disponibilidad = disponibilidad; 
		this.registroCamaraComercio = registroCamaraComercio; 
		this.registroSuperIntendencia = registroSuperIntendencia; 
		this.ubicacion = ubicacion; 
		
		
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public int getDisponibilidad() {
		return disponibilidad;
	}


	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}


	public String getRegistroCamaraComercio() {
		return registroCamaraComercio;
	}


	public void setRegistroCamaraComercio(String registroCamaraComercio) {
		this.registroCamaraComercio = registroCamaraComercio;
	}


	public String getRegistroSuperIntendencia() {
		return registroSuperIntendencia;
	}


	public void setRegistroSuperIntendencia(String registroSuperIntendencia) {
		this.registroSuperIntendencia = registroSuperIntendencia;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
	
}
