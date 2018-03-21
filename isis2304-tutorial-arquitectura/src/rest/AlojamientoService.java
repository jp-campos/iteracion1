package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import tm.AlohaTransactionManager;
import vos.Cliente;
import vos.Habitacion;
import vos.Hostal;
import vos.Hotel;

@Path("alojamientos")
public class AlojamientoService {
	
		//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
		 */
		@Context
		private ServletContext context;

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE INICIALIZACION
		//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
		 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
		 */
		private String getPath() {
			return context.getRealPath("WEB-INF/ConnectionData");
		}


		private String doErrorMessage(Exception e){
			return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS REST
		//----------------------------------------------------------------------------------------------------------------------------------
	
	
	
		/**
		 * Metodo Post Que registra un cliente. <br/>
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>URL: </b> http://localhost:8080/Iteracion1/rest/clientes <br/>
		 * @return	<b>Response Status 200</b> - JSON que contiene al cliente  <br/>
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */			
		@POST
		@Path("/hotel")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public Response postHotel(Hotel hotel) {
			
			try {
				AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
				
				
				tm.addHotel(hotel);
				
				List<Habitacion> habitaciones = hotel.getHabitaciones(); 
				
				for (Habitacion habitacion : habitaciones) {
					
					tm.addHabitacion(habitacion, hotel.getId());
				}
				
				
				return Response.status(200).entity(hotel).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		@POST
		@Path("/hostal")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public Response postHotel(Hostal hostal) {
			
			try {
				AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
				
				
				tm.addHotel(hostal);
				
				return Response.status(200).entity(hostal).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		
		
		@DELETE
		@Path("/hotel")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public Response deleteHotel(Hotel hotel) {
			
			try {
				AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
				tm.deleteHotel(hotel);
				 return Response.status( 200 ).entity( hotel ).build( );
			} catch (Exception e) {
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
		
		@DELETE
		@Path("/hostal")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public Response deleteHostal(Hostal hotel) {
			
			try {
				AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
				tm.deleteHotel(hotel);
				 return Response.status( 200 ).entity( hotel ).build( );
			} catch (Exception e) {
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
		
		@DELETE
		@Path("/habitacion")
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		public Response deleteHabitacion(Habitacion habitacion) {
			
			try {
				AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
				tm.deleteHabitacion(habitacion);
				 return Response.status( 200 ).entity( habitacion ).build( );
			} catch (Exception e) {
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
		

}
