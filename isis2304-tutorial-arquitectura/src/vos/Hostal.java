package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hostal extends Hotel{

	
	
	
	@JsonProperty (value="horaApertura")
	private String horaApertura;
	
	@JsonProperty(value = "horaCierre")
	private String horaCierre;
	
	
	public Hostal(@JsonProperty(value="id")int id,  @JsonProperty(value = "capacidad")int capacidad,@JsonProperty(value = "disponibilidad") int disponibilidad, @JsonProperty(value = "registroCamaraComercio")String registroCamaraComercio,
			@JsonProperty(value = "registroSuperIntendencia")String registroSuperIntendencia,
			@JsonProperty(value = "ubicacion")String ubicacion,@JsonProperty(value = "horaApertura")String horaApertura,@JsonProperty(value = "horaCierre")String horaCierre ) {
		
		super(id, capacidad, disponibilidad, registroCamaraComercio, registroSuperIntendencia, ubicacion); 

		this.horaApertura =horaApertura;
		this.horaCierre=horaCierre;
		
		
	}


	public String getHoraApertura() {
		return horaApertura;
	}


	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}


	public String getHoraCierre() {
		return horaCierre;
	}


	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}
	
	
	
	
	
	

}
