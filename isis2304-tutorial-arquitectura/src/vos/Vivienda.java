package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Vivienda {

	
	@JsonProperty(value="menaje")
	private boolean menaje; 
	
	@JsonProperty(value="numeroHabitaciones")
	private int numeroHabitaciones; 
	
	@JsonProperty(value="ocupado")
	private boolean ocupado; 
	
	@JsonProperty(value="ubicacion")
	private String ubicacion;

	public Vivienda(@JsonProperty(value="menaje")boolean menaje,@JsonProperty(value="numeroHabitaciones") int numeroHabitaciones, @JsonProperty(value="ocupado")boolean ocupado, @JsonProperty(value="ubicacion")String ubicacion) {

		this.menaje = menaje;
		this.numeroHabitaciones = numeroHabitaciones;
		this.ocupado = ocupado;
		this.ubicacion = ubicacion;
	}

	public boolean isMenaje() {
		return menaje;
	}

	public void setMenaje(boolean menaje) {
		this.menaje = menaje;
	}

	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	} 
	

	
	
	
	
	
	
}
