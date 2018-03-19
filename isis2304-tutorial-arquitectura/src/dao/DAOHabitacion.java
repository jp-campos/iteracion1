package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Apartamento;
import vos.PersonaOperador;

public class DAOHabitacion {

	//----------------------------------------------------------------------------------------------------------------------------------
		// CONSTANTES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Constante para indicar el usuario Oracle del estudiante
		 */
		
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
		 * Metodo constructor de la clase DAOHabitacion <br/>
		*/
		public DAOHabitacion() {
			recursos = new ArrayList<Object>();
		}
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE COMUNICACION CON LA BASE DE DATOS
		//----------------------------------------------------------------------------------------------------------------------------------
	

		public void addHabitacion(PersonaOperador operador, Apartamento apto) throws SQLException, Exception {

			String sql = String.format("INSERT INTO %1$s.APARTAMENTO (AMOBLADO, OCUPADO, UBICACION, APARTAMENTOID, PERSONAOPERADORID) VALUES (%2$s,%3$s,'%4$s',%5$s, %6$s )", 
										USUARIO, 
										apto.isAmoblado() ? 1 : 0, 
										apto.isOcupado() ? 1:0, 
										apto.getUbicacion(), 
										apto.getId(), 
										operador.getId());
										
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
			sql = String.format("INSERT INTO %1$s.PERSONAOPERADOR (APARTAMENTOID) VALUES (%2$s) ", 
								USUARIO, 
								apto.getId());
			
			
			System.out.println(sql);
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
		
		
		public void deleteHabitacion(Apartamento apto) throws SQLException
		{
			
			String sql = String.format("UPDATE %1$s.PERSONAOPERADOR SET APARTAMENTOID = NULL WHERE APARTAMENTOID = %2$s ", 
						USUARIO, apto.getId());
			
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
			sql = String.format("DELETE FROM %1$s.APARTAMENTO WHERE APARTAMENTOID = %2$s ", 
					USUARIO, apto.getId());
			
			System.out.println(sql);
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}

}
