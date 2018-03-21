package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Apartamento;
import vos.Cliente;
import vos.Hostal;
import vos.Hotel;
import vos.PersonaOperador;

public class DAOHotel {

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
		 * Metodo constructor de la clase DAOHotel <br/>
		*/
		public DAOHotel() {
			recursos = new ArrayList<Object>();
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE COMUNICACION CON LA BASE DE DATOS
		//----------------------------------------------------------------------------------------------------------------------------------
	
	

		public void addHotel(Hotel hotel) throws SQLException, Exception {

			
			if(hotel instanceof Hostal)
			{	
				Hostal hostal = (Hostal) hotel; 
				
			String sql = String.format("INSERT INTO %1$s.HOTELERIA (CAPACIDAD, DISPONIBILIDAD, REGISTROCAMARACOMERCIO, REGISTROSUPERINTENDENCIA, UBICACION, HOTELERIAID, NOMBRE, HORAAPERTURA, HORACIERRE ) "
					+ "VALUES (%2$s,%3$s,'%4$s','%5$s', '%6$s', %7$s , '%8$s', '%9$s', '%10$s', 'HOSTAL'  )", 
										USUARIO, 
										hostal.getCapacidad(), 
										hostal.getDisponibilidad(), 
										hostal.getRegistroCamaraComercio(), 
										hostal.getRegistroSuperIntendencia(), 
										hostal.getUbicacion(), 
										hostal.getId(),
										hostal.getNombre(), 
										hostal.getHoraApertura(),
										hostal.getHoraCierre());
										
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
			}else 
			{
				String sql = String.format("INSERT INTO %1$s.HOTELERIA (CAPACIDAD, DISPONIBILIDAD, REGISTROCAMARACOMERCIO, REGISTROSUPERINTENDENCIA, UBICACION, HOTELERIAID, NOMBRE, TIPOH) "
						+ "VALUES (%2$s,%3$s,'%4$s','%5$s', '%6$s', %7$s , '%8$s', 'HOTEL')", 
											USUARIO, 
											hotel.getCapacidad(), 
											hotel.getDisponibilidad(), 
											hotel.getRegistroCamaraComercio(), 
											hotel.getRegistroSuperIntendencia(), 
											hotel.getUbicacion(), 
											hotel.getId(), 
											hotel.getNombre());
											
				System.out.println(sql);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
				
										
				
			}
			
		}
		
		
		public void deleteHotel(Hotel hotel) throws SQLException
		{
			
			if(hotel instanceof Hostal)
			{		
				
				Hostal hostal = (Hostal)hotel; 
				
			String sql = String.format("DELETE FROM %1$s.HOSTAL WHERE HOSTALID = %2$s ", 
						USUARIO, hostal.getId());
			
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
			sql = String.format("DELETE FROM %1$s.HOTEL WHERE HOTELID = %2$s ", 
					USUARIO, hostal.getId());
			
			System.out.println(sql);
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			}else
			{
				
				String sql = String.format("DELETE FROM %1$s.HOTEL WHERE HOTELID = %2$s ", 
						USUARIO, hotel.getId());
				
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
				
				
			}
				
			
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
		 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla CLIENTES) en una instancia de la clase Cliente	 * @param resultSet ResultSet con la informacion de un bebedor que se obtuvo de la base de datos.
		 * @return Cliente cuyos atributos corresponden a los valores asociados a un registro particular de la tabla Clientes.
		 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
		 */
		public Hotel convertResultSetToHotel(ResultSet resultSet) throws SQLException {

		
			Hotel hotel = null;
			
			String nombre=""; 
			String rol = ""; 
			int carnet = 0;
			int id = 0;
			
			id = resultSet.getInt("COMUNIDADID");
			nombre = resultSet.getString("NOMBRE");
			rol = resultSet.getString("ROL"); 
			carnet = resultSet.getInt("CARNET");
			String tipo = resultSet.getString("TIPO");
			
			if(tipo.equals("CLIENTE"))
			{
			
				//cliente = new Hotel(habitaciones, id, capacidad, disponibilidad, registroCamaraComercio, registroSuperIntendencia, ubicacion);
					
			
			}
			
			
			return hotel;
		}

		
		
		
		
	
}
