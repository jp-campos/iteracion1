package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Apartamento {

	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="amoblado")
	private boolean amoblado;
	
	@JsonProperty(value="ocupado")
	private boolean ocupado;
	
	@JsonProperty(value="ubicacion")
	private boolean ubicacion;
	

	public Apartamento(@JsonProperty(value="amoblado")boolean amoblado, @JsonProperty(value="ocupado")boolean ocupado, @JsonProperty(value="ubicacion")boolean ubicacion) {
		super();
		this.amoblado = amoblado;
		this.ocupado = ocupado;
		this.ubicacion = ubicacion;
	}


	public boolean isAmoblado() {
		return amoblado;
	}


	public void setAmoblado(boolean amoblado) {
		this.amoblado = amoblado;
	}


	public boolean isOcupado() {
		return ocupado;
	}


	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}


	public boolean isUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(boolean ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
	
	
	
	
}
