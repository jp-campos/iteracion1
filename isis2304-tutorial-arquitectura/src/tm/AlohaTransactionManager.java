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
package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dao.DAOCliente;
import dao.DAOHabitacion;
import dao.DAOHotel;
import dao.DAOOperador;
import vos.Cliente;
import vos.Comunidad;
import vos.Habitacion;
import vos.Hotel;
import vos.PersonaOperador;

/**
 * @author Santiago Cortes Fernandez 	- 	s.cortes@uniandes.edu.co
 * @author Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * 
 * Clase que representa al Manejador de Transacciones de la Aplicacion (Fachada en patron singleton de la aplicacion)
 * Responsabilidades de la clase: 
 * 		Intermediario entre los servicios REST de la aplicacion y la comunicacion con la Base de Datos
 * 		Modelar y manejar autonomamente las transacciones y las reglas de negocio.
 */
public class AlohaTransactionManager {

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private static String CONNECTION_DATA_PATH;
	


	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * Atributo que representa la conexion a la base de datos
	 */
	private Connection conn;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE CONEXION E INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * <b>Metodo Contructor de la Clase ParranderosTransactionManager</b> <br/>
	 * <b>Postcondicion: </b>	Se crea un objeto  ParranderosTransactionManager,
	 * 						 	Se inicializa el path absoluto del archivo de conexion,
	 * 							Se inicializna los atributos para la conexion con la Base de Datos
	 * @param contextPathP Path absoluto que se encuentra en el servidor del contexto del deploy actual
	 * @throws IOException Se genera una excepcion al tener dificultades con la inicializacion de la conexion<br/>
	 * @throws ClassNotFoundException 
	 */
	public AlohaTransactionManager(String contextPathP) {
		
		try {
			CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initializeConnectionData();
		} 
		catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.<br/>
	 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
	 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura<br/>
	 * @throws ClassNotFoundException 
	 */
	private void initializeConnectionData() throws IOException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream(new File(AlohaTransactionManager.CONNECTION_DATA_PATH));
		Properties properties = new Properties();
		
		properties.load(fileInputStream);
		fileInputStream.close();
		
		this.url = properties.getProperty("url");
		this.user = properties.getProperty("usuario");
		this.password = properties.getProperty("clave");
		this.driver = properties.getProperty("driver");
		
		//Class.forName(driver);
	}

	/**
	 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
	 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados<br/>
	 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
	 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("[ALOHANDES APP] Attempting Connection to: " + url + " - By User: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS TRANSACCIONALES
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AGREGAR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo que modela la transaccion que agrega un bebedor a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el bebedor que entra como parametro <br/>
	 * @param bebedor - el bebedor a agregar. bebedor != null
	 * @throws Exception - Cualquier error que se genere agregando el bebedor
	 */
	public void addCliente(Cliente cliente) throws Exception 
	{
		
		DAOCliente daoCliente = new DAOCliente( );
		try
		{
			this.conn = darConexion(); 
			daoCliente.setConn(conn);
			
			if(!cliente.getRol().equals(Comunidad.FAMILIAR)	&&!cliente.getRol().equals(Comunidad.ESTUDIANTE )&&!cliente.getRol().equals(Comunidad.PROFESOR))
			{
				throw new Exception("El cliente no cumple con los requisitos para inscribirse"); 
			}
			
			
			
			daoCliente.addCliente(cliente);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un operador a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el operador que entra como parametro <br/>
	 * @param bebedor - el operador a agregar. bebedor != null
	 * @throws Exception - Cualquier error que se genere agregando el bebedor
	 */
	public void addOperador(PersonaOperador operador) throws Exception 
	{
		
		DAOOperador daoOperador = new DAOOperador( );
		try
		{
			this.conn = darConexion(); 
			daoOperador.setConn(conn);
			
			if(!operador.getRol().equals(Comunidad.FAMILIAR)	&&!operador.getRol().equals(Comunidad.ESTUDIANTE )&&!operador.getRol().equals(Comunidad.PROFESOR)
					&& !operador.getRol().equals(Comunidad.HOTEL))
			{
				throw new Exception("El operador no cumple con los requisitos para inscribirse"); 
			}
			
			
			System.out.println("Pasa el exception de tm");
			daoOperador.addOperador(operador);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * Metodo que modela la transaccion que agrega un hotel/hostal a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el operador que entra como parametro <br/>
	 * @param hotel - el hotel a agregar. hotel != null
	 * @throws Exception - Cualquier error que se genere agregando el hotel
	 */
	public void addHotel(Hotel hotel) throws Exception 
	{
		
		DAOHotel daoHotel = new DAOHotel( );
		try
		{
			this.conn = darConexion(); 
			daoHotel.setConn(conn);
			
			
			daoHotel.addHotel(hotel);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHotel.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega una habitación de un respectivo operador a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el operador que entra como parametro <br/>
	 * @param bebedor - el operador a agregar. bebedor != null
	 * @throws Exception - Cualquier error que se genere agregando el bebedor
	 */
	public void addHabitacion(Habitacion habitacion, int id) throws Exception 
	{
		
		DAOHabitacion daoHabitacion = new DAOHabitacion( );
		try
		{
			this.conn = darConexion(); 
			daoHabitacion.setConn(conn);
			
			daoHabitacion.addHabitacion(habitacion, id);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHabitacion.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	public void addServicios()
	{
		
		
	}
	//-----------------------------------------------
	// METODOS GET
	//-----------------------------------------------
	/**
	 * Metodo que modela la transaccion que retorna todos los bebedores de la base de datos. <br/>
	 * @return List<Bebedor> - Lista de bebedores que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<Cliente> getAllClientes() throws Exception {
		DAOCliente daoBebedor = new DAOCliente();
		List<Cliente> clientes;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			
			
			clientes = daoBebedor.getClientes();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return clientes;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los bebedores de la base de datos. <br/>
	 * @return List<Bebedor> - Lista de bebedores que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<PersonaOperador> getAllOperadores() throws Exception {
		DAOOperador daoOperador = new DAOOperador();
		List<PersonaOperador> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			
			operadores = daoOperador.getAllOperadores();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	
	
	/**
	 * Metodo que modela la transaccion que busca el bebedor en la base de datos que tiene el ID dado por parametro. <br/>
	 * @param name -id del bebedor a buscar. id != null
	 * @return Bebedor - Bebedor que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Cliente getClienteById(Long id) throws Exception {
		DAOCliente daoCliente = new DAOCliente();
		Cliente cliente = null;
		try 
		{
			this.conn = darConexion();
			daoCliente.setConn(conn);
			cliente = daoCliente.findBebedorById(id);
			if(cliente == null)
			{
				throw new Exception("El bebedor con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return cliente;
	}
	

	
	
	/**
	 * Metodo que modela la transaccion que agrega un bebedor a la base de datos  <br/>
	 * unicamente si el número de bebedores que existen en su ciudad es menor la constante CANTIDAD_MAXIMA <br/>
	 * <b> post: </b> Si se cumple la condicion, se ha agregado el bebedor que entra como parametro  <br/>
	 * @param bebedor - el bebedor a agregar. bebedor != null
	 * @param cantidadMaxima -representa la cantidad maxima de bebedores que pueden haber en la misma ciudad
	 * @throws Exception - Cualquier error que se genere agregando el bebedor
	 */
	public void addBebedorWithLimitations(Cliente bebedor) throws Exception 
	{
		DAOCliente daoBebedor = new DAOCliente( );
		try
		{
			//TODO Requerimiento 4B: Obtenga la conexion a la Base de Datos (revise los metodos de la clase)
			daoBebedor.updateCliente(bebedor);
			//TODO Requerimiento 4C: Establezca la conexion del DaoBebedor a la Base de datos (revise los metodos de DAOBebedor)
		
			
			//TODO Requerimiento 4C: Verifique la regla de negocio descrita en la documentacion. En caso que no se cumpla, lance una excepcion explicando lo sucedido
			//						 (Solo se agrega el bebedor si la cantidad de bebedores, en la Base de Datos, de su misma ciudad es inferior al valor de la constante CANTIDAD_MAXIMA.
			
			
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
 
	}
	
	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al bebedor que entra por parametro.<br/>
	 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el bebedor que entra como parametro <br/>
	 * @param bebedor - Bebedor a actualizar. bebedor != null
	 * @throws Exception - Cualquier error que se genere actualizando al bebedor.
	 */
	public void updateBebedor(Cliente bebedor) throws Exception 
	{
		DAOCliente daoBebedor = new DAOCliente( );
		try
		{
			this.conn = darConexion();
			daoBebedor.setConn( conn );
			//TODO Requerimiento 5C: Utilizando los Metodos de DaoBebedor, verifique que exista el bebedor con el ID dado en el parametro. 
			//						 Si no existe un bebedor con el ID ingresado, lance una excepcion en donde se explique lo sucedido
			//						 De lo contrario, se actualiza la informacion del bebedor de la Base de Datos


		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	
	
	//-------------------------------------------------------------
	//METODOS DELETE
	//--------------------------------------------------------------
	
	
	
	

	
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos al bebedor que entra por parametro. <br/>
	 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado el bebedor que entra por parametro <br/>
	 * @param Hotel - hotel a eliminar. hotel != null
	 * @throws Exception - Cualquier error que se genere eliminando al bebedor.
	 */
	public void deleteHotel(Hotel hotel) throws Exception 
	{
		DAOHotel daoHotel = new DAOHotel( );
		try
		{
			this.conn = darConexion();
			daoHotel.setConn( conn );
			
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHotel.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	
	
	
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos al bebedor que entra por parametro. <br/>
	 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado el bebedor que entra por parametro <br/>
	 * @param Hotel - hotel a eliminar. hotel != null
	 * @throws Exception - Cualquier error que se genere eliminando al bebedor.
	 */
	public void deleteHabitacion(Habitacion habitacion) throws Exception 
	{
		DAOHotel daoHotel = new DAOHotel( );
		try
		{
			this.conn = darConexion();
			daoHotel.setConn( conn );
			
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHotel.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	
	
	
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos al bebedor que entra por parametro. <br/>
	 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado el bebedor que entra por parametro <br/>
	 * @param Cliente - bebedor a eliminar. bebedor != null
	 * @throws Exception - Cualquier error que se genere eliminando al bebedor.
	 */
	public void deleteCliente(Cliente bebedor) throws Exception 
	{
		DAOCliente daoBebedor = new DAOCliente( );
		try
		{
			this.conn = darConexion();
			daoBebedor.setConn( conn );
			


		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}

}
