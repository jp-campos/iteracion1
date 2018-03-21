package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un hotel
 * @author K555jp.campos
 *
 */
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
	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="habitaciones")
	private List<Habitacion> habitaciones; 

	
	public Hotel(@JsonProperty(value= "habitaciones")List<Habitacion> habitaciones,@JsonProperty(value="id")int id,@JsonProperty(value="capacidad")int capacidad, @JsonProperty(value = "disponibilidad")int disponibilidad, @JsonProperty(value="registroCamaraComercio")String registroCamaraComercio,
			@JsonProperty(value="registroSuperIntendencia")String registroSuperIntendencia, @JsonProperty(value = "ubicacion")String ubicacion)
	{
		this.habitaciones = habitaciones;
		this.capacidad = capacidad; 
		this.disponibilidad = disponibilidad; 
		this.registroCamaraComercio = registroCamaraComercio; 
		this.registroSuperIntendencia = registroSuperIntendencia; 
		this.ubicacion = ubicacion; 
		this.id =id;
		
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}



	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
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
