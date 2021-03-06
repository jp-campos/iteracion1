package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTransactionManager;
import vos.Apartamento;
import vos.ContratoApto;
import vos.ContratoHabitacion;
import vos.Hotel;
import vos.Reserva;

@Path ("reservas")
public class ReservaService {

	
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
			 * Metodo Post Que hace una reserva a un . <br/>
			 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
			 * <b>URL: </b> http://localhost:8080/Iteracion1/rest/reservas/{{idcliente}}/apartamento/{{idapartamento}} <br/>
			 * @return	<b>Response Status 200</b> - JSON que contiene la reserva  <br/>
			 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
			 */			
			@POST
			@Path("{idcliente: \\d+}/apartamento/{id: \\d+}")
			@Consumes({ MediaType.APPLICATION_JSON })
			@Produces({ MediaType.APPLICATION_JSON })
			public Response postContratoApto(ContratoApto reserva,@PathParam("idcliente") int idcliente, @PathParam("idapartamento")int idapartamento) {
				
				try {
					System.out.println("entra al post disponibilidad " + reserva.getFechaFinal());
					AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
					
					
					tm.addReserva(reserva, idcliente, idapartamento);
					
					
					return Response.status(200).entity(reserva).build();
				} 
				catch (Exception e) {
					return Response.status(500).entity(doErrorMessage(e)).build();
				}
			}
			
			

			/**
			 * Metodo Post Que hace una reserva a una habitacion . <br/>
			 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
			 * <b>URL: </b> http://localhost:8080/Iteracion1/rest/alojamientos/{{id}}/hotel <br/>
			 * @return	<b>Response Status 200</b> - JSON que contiene al cliente  <br/>
			 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
			 */			
			@POST
			@Path("/habitacion/{id: \\d+}")
			@Consumes({ MediaType.APPLICATION_JSON })
			@Produces({ MediaType.APPLICATION_JSON })
			public Response postHotel(ContratoHabitacion reserva,@PathParam("id") int id) {
				
				try {
					System.out.println("entra al post disponibilidad " + reserva.getFechaFinal());
					AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
					
					
					//tm.addReserva(reserva, 0);
					
					
					return Response.status(200).entity(reserva).build();
				} 
				catch (Exception e) {
					return Response.status(500).entity(doErrorMessage(e)).build();
				}
			}
	
	
	
			
	
	
	
}
