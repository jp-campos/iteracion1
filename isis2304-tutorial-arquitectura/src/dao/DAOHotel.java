package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Apartamento;
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
				
			String sql = String.format("INSERT INTO %1$s.HOTEL (CAPACIDAD, DISPONIBILIDAD, REGISTROCAMARACOMERCIO, REGISTROSUPERINTENDENCIA, UBICACION, HOTELID) "
					+ "VALUES (%2$s,%3$s,'%4$s','%5$s', '%6$s', %7$s )", 
										USUARIO, 
										hostal.getCapacidad(), 
										hostal.getDisponibilidad(), 
										hostal.getRegistroCamaraComercio(), 
										hostal.getRegistroSuperIntendencia(), 
										hostal.getUbicacion(), 
										hostal.getId());
										
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
			sql = String.format("INSERT INTO %1$s.HOSTAL (HORAAPERTURA, HORACIERRE, HOSTALID) VALUES ('%2$s', '%3$s', %4$s) ", 
								USUARIO, 
								hostal.getHoraApertura(), 
								hostal.getHoraCierre(),
								hostal.getId());
			
			
			System.out.println(sql);
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			}else 
			{
				String sql = String.format("INSERT INTO %1$s.HOTEL (CAPACIDAD, DISPONIBILIDAD, REGISTROCAMARACOMERCIO, REGISTROSUPERINTENDENCIA, UBICACION, HOTELID) "
						+ "VALUES (%2$s,%3$s,'%4$s','%5$s', '%6$s', %7$s )", 
											USUARIO, 
											hotel.getCapacidad(), 
											hotel.getDisponibilidad(), 
											hotel.getRegistroCamaraComercio(), 
											hotel.getRegistroSuperIntendencia(), 
											hotel.getUbicacion(), 
											hotel.getId());
											
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

		
	
}
