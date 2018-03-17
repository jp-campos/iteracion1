package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Habitacion {

	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="compartida")
	private boolean compartida;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="ocupada")
	private int ocupada;

	public Habitacion(@JsonProperty(value="id")int id,@JsonProperty(value="compartida")boolean compartida, @JsonProperty(value="descripcion")String descripcion, @JsonProperty(value="ocupada") int ocupada) {
		this.id = id; 
		this.compartida = compartida;
		this.descripcion = descripcion;
		this.ocupada = ocupada;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isCompartida() {
		return compartida;
	}

	public void setCompartida(boolean compartida) {
		this.compartida = compartida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getOcupada() {
		return ocupada;
	}

	public void setOcupada(int ocupada) {
		this.ocupada = ocupada;
	}
	
	
	
}
