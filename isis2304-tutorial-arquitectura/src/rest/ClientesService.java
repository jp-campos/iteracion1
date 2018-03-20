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
package rest;


import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTransactionManager;
import vos.Cliente;

/**
 * @author Santiago Cortes Fernandez 	- 	s.cortes@uniandes.edu.co
 * @author Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * Clase que expone servicios REST con ruta base: http://localhost:8080/TutorialParranderos/rest/bebedores/...
 */
@Path("clientes")
public class ClientesService {

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

	
	/*
	 * REGISTRAR UN CLIENTE
	 */
	
	/**
	 * Metodo Post Que registra un cliente. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/Iteracion1/rest/clientes <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene al cliente  <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postCliente(Cliente cliente) {
		
		try {
			AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
			
		
		
			tm.addCliente(cliente);
			
			return Response.status(200).entity(cliente).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	/**
	 * Metodo GET que trae a todos los clientes en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/Iteracion1/rest/clientes <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los clientes que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllClientes() {
		
		try {
			AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
			
			List<Cliente> clientes;
	
			clientes = tm.getAllClientes();
			return Response.status(200).entity(clientes).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	/**
	 * Metodo GET que trae al cliente en la Base de Datos con el ID dado por parametro <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/clientes/rest/bebedores/{id} <br/>
	 * @return	<b>Response Status 200</b> - JSON Bebedor que contiene al bebedor cuyo ID corresponda al parametro <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getBebedorById( @PathParam( "id" ) Long id )
	{
		try{
			AlohaTransactionManager tm = new AlohaTransactionManager( getPath( ) );
			
			Cliente bebedor = tm.getClienteById( id );
			return Response.status( 200 ).entity( bebedor ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * Metodo que trae a los bebedores de la Base de Datos que viven en la ciudad y tienen el presupuesto dados por par�metro <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores/filterBy?nombre=---&apellido=--- <br/>
	 * @param ciudad - <em>[QueryParam]</em> parametro que indica la ciudad de los bebedores
	 * @param presupuesto - <em>[QueryParam]</em> parametro que indica el presupuesto de los bebedores
	 * @return	<b>Response Status 200</b> - JSONs que contienen a los bebedores que tengan el nombre o el apellido correspondiente<br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	//TODO Requerimiento 2A: Identifique e implemente la anotacion correcta para la realizacion del metodo
	
	
	@GET
	@Path( "/filterBy" )
	@Consumes
	public Response getBebedoresByCiudadAndPresupuesto(@QueryParam("ciudad")String ciudad){
		
		try{
			AlohaTransactionManager tm = new AlohaTransactionManager( getPath( ) );
			
			List<Cliente>bebedores;
			


			bebedores = null;
			return Response.status( 200 ).entity( bebedores ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	/**
	 * Metodo que recibe un bebedor en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al bebedor. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @param bebedor JSON con la informacion del bebedor que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Path("clientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBebedor(Cliente cliente) {
		
		 
		try {
			AlohaTransactionManager tm = new AlohaTransactionManager(getPath());
			tm.addCliente(cliente);
			 return Response.status( 200 ).entity( cliente ).build( );
		} catch (Exception e) {
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
		
		
	}
	
	/**
	 * Metodo POST que recibe un bebedor en formato JSON y lo agrega a la Base de Datos unicamente 
	 * si el n�mero de bebedores que existen en su ciudad es menor la constante CANTIDAD_MAXIMA <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al bebedor. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @param cantidadMaxima representa la cantidad maxima de bebedores que pueden haber en la misma ciudad
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Path( "restriccionCantidad" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBebedorWithLimitations(Cliente bebedor) {
		AlohaTransactionManager tm = new AlohaTransactionManager( getPath( ) );
		//TODO Requerimiento 4A: Implemente el metodo a partir de los ejemplos anteriores y utilizando el Transaction Manager de Parranderos 
		return null;
	}
	
	

	/**
	 * Metodo que recibe un bebedor en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se actualiza la Base de datos con la informacion correspondiente al bebedor.<br/>
	 * @param bebedor JSON con la informacion del bebedor que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que se desea modificar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	//TODO Requerimiento 5A: Identifique e implemente la anotacion correcta para la realizacion del metodo

	//TODO Requerimiento 5B: Identifique e implemente las anotaciones que indican el tipo de contenido que produce Y consume el metodo 

	
	public Response updateBebedor(Cliente bebedor) {
		//TODO Requerimiento 5B: Implemente el metodo a partir de los ejemplos anteriores y utilizando el Transaction Manager de Parranderos 
		return null;
	}

	/**
	 * Metodo que recibe un bebedor en formato JSON y lo elimina de la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se elimina de la Base de datos al bebedor con la informacion correspondiente.<br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/bebedores <br/>
	 * @param bebedor JSON con la informacion del bebedor que se desea eliminar
	 * @return	<b>Response Status 200</b> - JSON que contiene al bebedor que se desea eliminar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	
	public Response deleteBebedor(Cliente bebedor) {
		//TODO Requerimiento 6C: Implemente el metodo a partir de los ejemplos anteriores y utilizando el Transaction Manager de Parranderos 
		return null;
	}

}
