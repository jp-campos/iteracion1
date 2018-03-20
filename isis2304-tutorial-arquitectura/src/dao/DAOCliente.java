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
package dao;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

/**
 * @author Santiago Cortes Fernandez 	- 	s.cortes@uniandes.edu.co
 * @author Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicacion
 */
public class DAOCliente {

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Constante para indicar el usuario Oracle del estudiante
	 */
	//TODO Requerimiento 1H: Modifique la constante, reemplazando al ususario PARRANDEROS por su ususario de Oracle
	public final static String USUARIO = "ISIS2304A791810";
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase DAOBebedor <br/>
	*/
	public DAOCliente() {
		recursos = new ArrayList<Object>();
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------

	
	/**
	 * Metodo que agregar la informacion de un nuevo bebedor en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param cliente cliente que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addCliente(Cliente cliente) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.CLIENTE (CLIENTEID) VALUES (%2$s)", 
									USUARIO, 
									cliente.getId());
									
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		sql = String.format("INSERT INTO %1$s.RELACIONUNIANDINO (RELACIONUNIANDINOID, CARNET, NOMBRE, ROL) VALUES (%2$s, %3$s, '%4$s', '%5$s, ", 
							USUARIO, 
							cliente.getId(), cliente.getCarnet(), cliente.getNombre(), cliente.getRol());
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	
	
	public void cancelarReserva(Cliente cliente, Reserva reserva)throws SQLException
	{
		
		String sql = String.format("UPDATE %1$s.CLIENTE SET RESERVAACTUAL = NULL WHERE CLIENTEID = %2$s ", USUARIO, cliente.getId());
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		sql = String.format("UPDATE %1$s.HABITACION SET HABITACION RESERVAID = NULL WHERE RESERVAID = %2$s ",USUARIO, reserva.getId() );
		System.out.println(sql);
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		sql = String.format("DELETE FROM %1$s.RESERVA WHERE RESERVAID = 2%$s ", USUARIO, reserva.getId());
		System.out.println(sql);
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		
	}
	
	/**
	 * Metodo que obtiene la informacion de todos los clientes en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Cliente> getClientes() throws SQLException, Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

//		String sql = String.format( "SELECT %1$s.CLIENTE.RELACIONUNIANDINOID, NOMBRE, ROL, CARNET FROM %1$s.CLIENTE, %1$s.RELACIONUNIANDINO " + 
//				"WHERE %1$s.CLIENTE.RELACIONUNIANDINOID = %1$s.RELACIONUNIANDINO.RELACIONUNIANDINOID", USUARIO);

		
		String sql = String.format( "SELECT * FROM %1$s.RELACIONUNIANDINO", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		System.out.println("depues sql");
		
		if(rs.next())
		{
			int id = rs.getInt("RELACIONUNIANDINOID");
			System.out.println("uniandino id " + id);
			
		}
		
		sql = String.format( "SELECT * FROM %1$s.RELACIONUNIANDINO", USUARIO);
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			System.out.println("entra al next");
			clientes.add(convertResultSetToCliente(rs));
		}
		return clientes;
	}
	

	/**
	 * Metodo que obtiene la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del bebedor
	 * @return la informacion del bebedor que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el bebedor conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Cliente findBebedorById(Long id) throws SQLException, Exception 
	{
		Cliente bebedor = null;

		String sql = String.format("SELECT * FROM %1$s.CLIENTE WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			bebedor = convertResultSetToCliente(rs);
		}

		return bebedor;
	}
	
	
	/**
	 * Metodo que actualiza la informacion del bebedor en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param bebedor Bebedor que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void updateCliente(Cliente bebedor) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.BEBEDORES SET ", USUARIO));
		sql.append(String.format("NOMBRE = '%1$s' AND CIUDAD = '%2$s' AND PRESUPUESTO = '%3$s' ", bebedor.getNombre()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	
	/**
	 * Metodo que obtiene la cantidad de bebedores de una ciudad especifica, dada por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param ciudad ciudad que se desea saber la cantidad de bebedores
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public double getCountBebedoresByCiudad(String ciudad) throws SQLException, Exception
	{		
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("SELECT COUNT(*) AS CANTIDAD_CIUDAD ", USUARIO));
		sql.append(String.format("FROM %s.BEBEDORES ", USUARIO));
		sql.append(String.format("WHERE CIUDAD = '%s' ", ciudad));
		sql.append("GROUP BY CIUDAD");
		
		System.out.println(sql.toString());
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);	
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next())
		{
			return rs.getInt("CANTIDAD_CIUDAD");	
		}
		return 0;
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection){
		this.conn = connection;
	}
	
	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	
	

	
	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla CLIENTES) en una instancia de la clase Bebedor.
	 * @param resultSet ResultSet con la informacion de un bebedor que se obtuvo de la base de datos.
	 * @return Clinete cuyos atributos corresponden a los valores asociados a un registro particular de la tabla Clientes.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Cliente convertResultSetToCliente(ResultSet resultSet) throws SQLException {

//		System.out.println("Antes de get int");
//		int id = resultSet.getInt("RELACIONUNIANDINOID");
//		System.out.println("Despues de get int");
//		String sql = String.format("SELECT * FROM %1$s.RELACIONUNIANDINO WHERE RELACIONUNIANDINOID = %2$d", USUARIO, id );
//
//		
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		ResultSet rs = prepStmt.executeQuery();
//		
		System.out.println("En convert result");
		
		String nombre=""; 
		String rol = ""; 
		int carnet = 0;
		int id = 0;
		
		id = resultSet.getInt("RELACIONUNIANDINOID");
		nombre = resultSet.getString("NOMBRE");
		rol = resultSet.getString("ROL"); 
		carnet = resultSet.getInt("CARNET");
		
		Cliente cliente = new Cliente(id, nombre, rol, carnet);

		return cliente;
	}

}