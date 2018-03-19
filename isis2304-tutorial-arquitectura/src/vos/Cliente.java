/**-------------------------------------------------------------------
 * ISIS2304 - Sistemas Transaccionales
 * Departamento de Ingenieria de Sistemas
 * Universidad de los Andes
 * Bogota, Colombia
 * 
 * Actividad: Tutorial Parranderos: Arquitectura
 * Autores:
 * 			Santiago Cortes Fernandez	-	s.cortes@uniandes.edu.co
 * 			Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.*;

/**
 * @author Santiago Cortes Fernandez 	- 	s.cortes@uniandes.edu.co
 * @author Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * Clase que representa a los Bebebores del modelo Parranderos
 */
public class Cliente extends RelacionUniandino{

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------


	@JsonProperty(value="reservasHistoricas")
	private List<Reserva> reservasHistoricas;

	@JsonProperty(value="reservaActual")
	private Reserva reservaActual;
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase Bebedor
	 * <b>post: </b> Crea el bebedor con los valores que entran por parametro
	 * @param id - Id del bebedor.
	 * @param nombre - Nombre del bebedor.
	 * @param presupuesto - Presupuesto del bebedor.
	 * @param ciudad - Ciudad del bebedor.
	 */
	//TODO Requerimiento 1E: Complete el metodo constructor (parametros y contenido) con los atributos agregados anteriormente
	public Cliente( @JsonProperty(value="id")int id, @JsonProperty(value="nombre") String nombre, @JsonProperty(value="rol")String rol,@JsonProperty(value="carnet")int carnet) {
		super(id,nombre,rol,carnet);
		setId(id);
		setNombre(nombre);
		setRol(rol);
		setCarnet(carnet);
		reservasHistoricas = new ArrayList<>(); 
		reservaActual = null; 
		
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	
	public List<Reserva> getReservas()
	{
		return reservasHistoricas;
	}
	
	public void setReservas(List <Reserva> reservas)
	{
		this.reservasHistoricas = reservas; 
	}

	public void agregarReservaHistorica(Reserva reserva)
	{
		 reservasHistoricas.add(reserva);
	}

	public List<Reserva> getReservasHistoricas() {
		return reservasHistoricas;
	}

	public void setReservasHistoricas(List<Reserva> reservasHistoricas) {
		this.reservasHistoricas = reservasHistoricas;
	}

	public Reserva getReservaActual() {
		return reservaActual;
	}

	public void setReservaActual(Reserva reservaActual) {
		this.reservaActual = reservaActual;
	}
	
	

	
}
