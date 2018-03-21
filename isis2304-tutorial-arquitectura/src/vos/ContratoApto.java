package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un contrato de apartamento
 * @author jp.campos
 *
 */
public class ContratoApto extends Reserva{

	
	@JsonProperty(value="incluyeServPublicos")
	private boolean incluyeServPublicos;
	
	@JsonProperty(value="precioServicio")
	private int precioServicio; 
	
	@JsonProperty(value="incluyeAdmin")
	private boolean incluyeAdmin;
	

	@JsonProperty(value="apartamento")
	private Apartamento apartamento; 
	
	



	public ContratoApto(@JsonProperty(value="id")int id,@JsonProperty(value = "fechaIncial")String fechaInicial,@JsonProperty(value = "fechaFinal") String fechaFinal, @JsonProperty(value = "precio")int precio, 
			@JsonProperty(value = "incluyeServPublicos")boolean incluyeServPublicos, @JsonProperty(value="incluyeAdmin")boolean incluyeAdmin, @JsonProperty(value = "precioServicio")int precioServicio ) {
		
		super(id,fechaInicial, fechaFinal, precio);
		
		this.incluyeAdmin = incluyeAdmin; 
		this.incluyeServPublicos = incluyeServPublicos; 
		this.precioServicio = precioServicio; 
		apartamento = null; 
		
	}

	
	public Apartamento getApartameto() {
		return apartamento;
	}


	public void setApartameto(Apartamento apartameto) {
		this.apartamento = apartameto;
	}

	public boolean isIncluyeServPublicos() {
		return incluyeServPublicos;
	}


	public void setIncluyeServPublicos(boolean incluyeServPublicos) {
		this.incluyeServPublicos = incluyeServPublicos;
	}


	public int getPrecioServicio() {
		return precioServicio;
	}


	public void setPrecioServicio(int precioServicio) {
		this.precioServicio = precioServicio;
	}


	public boolean isIncluyeAdmin() {
		return incluyeAdmin;
	}


	public void setIncluyeAdmin(boolean incluyeAdmin) {
		this.incluyeAdmin = incluyeAdmin;
	}

	
	
	
	
}
