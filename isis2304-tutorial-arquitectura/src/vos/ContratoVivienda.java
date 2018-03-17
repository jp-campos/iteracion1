package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContratoVivienda extends Reserva {
	
	
	
	@JsonProperty(value="menaje")
	private boolean menaje;		
	
	
	@JsonProperty(value="numeroHabitaciones")
	private int numeroHabitaciones;


	public ContratoVivienda(@JsonProperty(value="id")int id,@JsonProperty(value = "fechaInicial")String fechaInicial, @JsonProperty(value = "fechaFinal")String fechaFinal,@JsonProperty(value = "precio")int precio,@JsonProperty(value="menaje")boolean menaje,@JsonProperty(value="numeroHabitaciones") int numeroHabitaciones) {
		super(id, fechaInicial, fechaFinal, precio);
		this.menaje = menaje;
		this.numeroHabitaciones = numeroHabitaciones;
	}		
	
	
}
