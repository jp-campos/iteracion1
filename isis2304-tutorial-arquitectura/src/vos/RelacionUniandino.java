package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RelacionUniandino {


	
	/**
	 * Id
	 */
	@JsonProperty(value="id")
	private int id; 

	/**
	 * Nombre del usuario
	 */
	@JsonProperty(value="nombre")
	private String nombre; 

	/**
	 * Relacion con la universidad de los Andes.
	 */
	@JsonProperty(value="rol")
	private String rol;
	
	/**
	 * Carnet del uniandino relacioado. Puede ser nulo si se trata de un vecino
	 */
	@JsonProperty(value="carnet")
	private int carnet;

	
	
	
	public RelacionUniandino(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String nombre, @JsonProperty(value="rol")String rol,@JsonProperty(value="carnet") Integer carnet) {
		
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
		this.carnet = carnet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Integer getCarnet() {
		return carnet;
	}

	public void setCarnet(Integer carnet) {
		this.carnet = carnet;
	}
	

	
	
}
