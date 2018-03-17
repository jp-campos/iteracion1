package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva {

	
	@JsonProperty(value="fechaInicial")
	private String fechaInicial;
	

	@JsonProperty(value="fechaFinal")
	private String fechaFinal;
	
	@JsonProperty(value="precio")
	private int precio; 
	
	@JsonProperty(value="id")
	private int id;
	
	//Constructor
	public Reserva (@JsonProperty(value="id")int id, @JsonProperty(value = "fechaInicial")String fechaInicial, @JsonProperty(value = "fechaFinal")String fechaFinal, @JsonProperty(value = "precio")int precio)
	{
		this.id = id; 
		this.fechaInicial = fechaInicial; 
		this.fechaFinal = fechaFinal; 
		this.precio = precio; 
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}


	
	
	
	
	
}
