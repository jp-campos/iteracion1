package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContratoHabitacion extends Reserva{
	
	@JsonProperty(value="accesoCocina")
	private boolean accesoCocina;

	
	@JsonProperty(value="baņoPrivado")
	private boolean baņoPrivado;
	
	@JsonProperty(value="comidas")
	private boolean comidas;
	
	@JsonProperty(value="duracion")
	private boolean duracion;
	
	@JsonProperty(value="habitacionIndividual")
	private boolean habitacionIndividual;
	
	@JsonProperty(value="precioServicios")
	private int precioServicios;
	
	@JsonProperty(value="habitacion")
	private Habitacion habitacion;
	
	

	
	public ContratoHabitacion(@JsonProperty(value="id")int id,@JsonProperty(value = "fechaInicial")String fechaInicial, @JsonProperty(value = "fechaFinal")String fechaFinal,@JsonProperty(value = "precio")int precio,	@JsonProperty(value="baņoPrivado")boolean accesoCocina,@JsonProperty(value="baņoPrivado") boolean baņoPrivado,
			@JsonProperty(value="comidas")boolean comidas, @JsonProperty(value="duracion")boolean duracion,
			@JsonProperty(value="habitacionIndividual")boolean habitacionIndividual, @JsonProperty(value="precioServicios")int precioServicios) {
		
		super(id, fechaInicial, fechaFinal, precio);
		
		this.accesoCocina = accesoCocina;
		this.baņoPrivado = baņoPrivado;
		this.comidas = comidas;
		this.duracion = duracion;
		this.habitacionIndividual = habitacionIndividual;
		this.precioServicios = precioServicios;
		
		habitacion = null;
	}

	
	public Habitacion getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}


	public boolean isAccesoCocina() {
		return accesoCocina;
	}

	public void setAccesoCocina(boolean accesoCocina) {
		this.accesoCocina = accesoCocina;
	}

	public boolean isBaņoPrivado() {
		return baņoPrivado;
	}

	public void setBaņoPrivado(boolean baņoPrivado) {
		this.baņoPrivado = baņoPrivado;
	}

	public boolean isComidas() {
		return comidas;
	}

	public void setComidas(boolean comidas) {
		this.comidas = comidas;
	}

	public boolean isDuracion() {
		return duracion;
	}

	public void setDuracion(boolean duracion) {
		this.duracion = duracion;
	}

	public boolean isHabitacionIndividual() {
		return habitacionIndividual;
	}

	public void setHabitacionIndividual(boolean habitacionIndividual) {
		this.habitacionIndividual = habitacionIndividual;
	}

	public int getPrecioServicios() {
		return precioServicios;
	}

	public void setPrecioServicios(int precioServicios) {
		this.precioServicios = precioServicios;
	}

	
	
	
}
