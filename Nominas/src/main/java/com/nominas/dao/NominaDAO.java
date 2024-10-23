package com.nominas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nominas.model.*;
import com.nominas.conexion.Conexion;
import com.nominas.excepcion.DatosNoCorrectosException;

public class NominaDAO {

	private Connection connection;
	private PreparedStatement statement;

	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

	/**
	 * Listar todos los empleados, devolviendolos en una lista
	 * @return List<Empleado>
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public List<Empleado> obtenerEmpleados() throws SQLException, DatosNoCorrectosException {

		ResultSet resultSet = null;
		List<Empleado> listaEmpleados = new ArrayList<>();

		String sql = null;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados";

			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Empleado e = new Empleado(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3).charAt(0), resultSet.getInt(4), resultSet.getInt(5), 0);
				
				listaEmpleados.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}
	
	
	/**
	 * Obtiene un empleado por su dni
	 * @param dni
	 * @return Empleado
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public Empleado obtenerEmpleado(String dni) throws SQLException, DatosNoCorrectosException {

		ResultSet resultSet = null;

		String sql = null;
		connection = obtenerConexion();
		Empleado e = null;

		try {

			sql = "SELECT * FROM empleados WHERE dni = ?";

			statement = connection.prepareStatement(sql);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {

				e = new Empleado(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3).charAt(0), resultSet.getInt(4), resultSet.getInt(5), 0);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}
	
	/**
	 * Devuelve una lista de empleados que coincidan con el dni introducido por el cliente
	 * @param dni
	 * @return List<Empleado>
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public List<Empleado> obtenerSalarioPorDni(String dni) throws SQLException, DatosNoCorrectosException {

	    ResultSet resultSet = null;
	    String sql = null;
	    connection = obtenerConexion();
	    
	    List<Empleado> empleados = new ArrayList<>();
	    
	    try {
	        sql = "SELECT nombre, E.dni, sexo, categoria, anyo, sueldo FROM empleados E JOIN sueldo S ON E.dni = S.dni  WHERE E.DNI LIKE ?";
	        
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, dni + "%");
	        resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	        	 Empleado empleado = new Empleado(
	 	                resultSet.getString(1), 
	 	                resultSet.getString(2), 
	 	                resultSet.getString(3).charAt(0), 
	 	                resultSet.getInt(4),
	 	                resultSet.getInt(5),
	 	                resultSet.getInt(6)
	 	            );
	            
	            empleados.add(empleado); 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return empleados;  
	}
	
	
	/**
	 * Devuelve una lista de empleados dependiendo del parametro que se haya indicado
	 * @param nombre
	 * @param sort
	 * @return List<Empleado>
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public List<Empleado> obtenerEmpleadosPorX(String nombre,String sort) throws SQLException, DatosNoCorrectosException {

	    ResultSet resultSet = null;
	    String sql = null;
	    connection = obtenerConexion();
	    
	    List<Empleado> empleados = new ArrayList<>();

	    try {
	        sql = "SELECT * FROM empleados WHERE "+sort+" LIKE ?";
	        
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, nombre + "%");
	        resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	        	Empleado empleado = new Empleado(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3).charAt(0), resultSet.getInt(4), resultSet.getInt(5), 0);
	            
	            
	            empleados.add(empleado); 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return empleados;  
	}
	
	
	/**
	 * Calcula el salario de los empleados, utilizando su categioria y anos,
	 * luego llama a actualizarSalario
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public void calculaSalarioEmpleados() throws SQLException, DatosNoCorrectosException {
	    List<Empleado> empleados = new ArrayList<>();

	    try {
	    	
	    	//Tenemos la lista de los empleados con los salarios sin actualizar
	    	empleados = obtenerEmpleados();
	    	
	    	//Calcular el salario correcto segun su categoria y años
	    	Nomina n = new Nomina();
	    	
	    	int sueldo;
	    	
	    	//Le asignamos al empleado su nuevo sueldo, (repetido o no)
	    	for (Empleado empleado : empleados) {
				sueldo = n.sueldo(empleado);
				empleado.setSueldo(sueldo);
				
				//Actualizar sueldo
				actualizaSalarioEmpleados(empleado);
			}
	    	

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}
	
	/**
	 * Actualiza el salario de todos los empleados en la DB
	 * @param e
	 * @return estadoOperacion
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public boolean actualizaSalarioEmpleados(Empleado e) throws SQLException, DatosNoCorrectosException {

	    String sql = null;
	    connection = obtenerConexion();
	    boolean estadoOperacion = false;

	    try {
	    	
	    	connection.setAutoCommit(false);
			sql = "UPDATE sueldo SET sueldo=? WHERE dni=?";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, e.getSueldo());
			statement.setString(2, e.getDni());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    
		return estadoOperacion;
	}
	
	
	
	/**
	 * Edita los empleados
	 * @param empleado
	 * @return estadoOperacion
	 * @throws SQLException
	 */
	public boolean editar(Empleado empleado) throws SQLException {
		String sql = null;
		boolean estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyo=? WHERE dni=?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getNombre());
			statement.setString(2, String.valueOf(empleado.getSexo()).toUpperCase());
			statement.setDouble(3, empleado.getCategoria());
			statement.setInt(4, empleado.getAnyos());
			statement.setString(5, empleado.getDni());
			
			empleado.imprime();
			
			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}
	
	
}