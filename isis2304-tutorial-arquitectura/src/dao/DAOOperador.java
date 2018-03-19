package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;
import vos.PersonaOperador;

public class DAOOperador {
	
	//----------------------------------------------------------------------------------------------------------------------------------
		// CONSTANTES
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Constante para indicar el usuario Oracle del estudiante
		 */
		public final static String USUARIO = "jp.campos@uniandes.edu.co";
		
		
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
		 * Metodo constructor de la clase DAOOperador <br/>
		*/
		public DAOOperador() {
			recursos = new ArrayList<Object>();
		}
		
	
		
		/**
		 * Metodo que agregar la informacion de un nuevo operador en la Base de Datos a partir del parametro ingresado<br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
		 * @param PersonaOperador operador que desea agregar a la Base de Datos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void addOperador(PersonaOperador operador) throws SQLException, Exception {
			
			String sql = String.format("INSERT INTO %1$s.PERSONAOPERADOR (ID) VALUES (%2$s)", 
										USUARIO, 
										operador.getId());
										
			System.out.println(sql);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
			sql = String.format("INSERT INTO %1$s.RELACIONUNIANDINO (RELACIONUNIANDINOID, CARNET, NOMBRE, ROL) VALUES (%2$s, %3$s, '%4$s', '%5$s)", 
								USUARIO, 
								operador.getId(), operador.getCarnet(), operador.getNombre(), operador.getRol());
			
		
			System.out.println(sql);
			
			prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
			
		}
		

		
		
		

		
		
		
}
