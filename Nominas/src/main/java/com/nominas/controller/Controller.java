package com.nominas.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * Servlet implementation class ProductoController
 */

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nominas.dao.NominaDAO;
import com.nominas.excepcion.DatosNoCorrectosException;
import com.nominas.model.Empleado;	


@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = { "/empresa" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


	/**
	 * Maneja las solicitudes HTTP GET del servlet.
	 *
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String opcion = request.getParameter("opcion");

		if (opcion.equals("listar")) {

			NominaDAO nominaDAO = new NominaDAO();
			List<Empleado> lista = new ArrayList<>();
			try {
				//Actualizar salario en DB
				nominaDAO.calculaSalarioEmpleados();
				System.out.println("Sueldo recalculado y actualizado");
				
				lista = nominaDAO.obtenerEmpleados();

				for (Empleado empleado : lista) {
					empleado.imprime();

				}
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				session.setAttribute("Error", "Error: No se ha podido listar los empleados.");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);
			} catch (DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted ha presionado la opcion listar");
			
			
			request.setAttribute("lista", lista);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
			
			requestDispatcher.forward(request, response);

		}else if (opcion.equals("meditar")) {
		    System.out.println("Has decidido editar un empleado en concreto");
		    
		    // Rescatar dni
		    String dni = request.getParameter("dni");
		    System.out.println("Este es el dni en meditar?" +dni);
		    
		    // Obtener el empleado en un objeto 
		    NominaDAO nominaDAO = new NominaDAO();
		    
		    try {
		        Empleado empleado = nominaDAO.obtenerEmpleado(dni);
		        empleado.imprime();
		        
		        // Establecer el empleado en el request
		        request.setAttribute("empleado", empleado);
		        
		        // Redirigir a la vista de edición
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
		        requestDispatcher.forward(request, response);
		        
		    } catch (SQLException | DatosNoCorrectosException ex) {
		        session.setAttribute("Error", "Error: No se ha podido seleccionar el empleado a editar.");
		        
		        ex.printStackTrace();
		        
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
		        requestDispatcher.forward(request, response);
		    }
		}

		

	}
	
	/**
	 * Maneja las solicitudes HTTP POST del servlet.
	 * 
	 * Se encarga de buscar, y editar empleados de la DB
	 *
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		
		HttpSession session = request.getSession();

		if (opcion.equals("buscar")) {
			
			List<Empleado> empleados = new ArrayList<>();
			
			try {
				NominaDAO nominaDAO = new NominaDAO();
				
				//Actualizar salario en DB
				nominaDAO.calculaSalarioEmpleados();
				System.out.println("Sueldo recalculado y actualizado");
				
				empleados = nominaDAO.obtenerSalarioPorDni(request.getParameter("dni"));
				
				if (empleados.isEmpty()) {
					session.setAttribute("Error","No se ha encontrado el empleado");
				}else {
					for (Empleado empleado : empleados) {
						empleado.imprime();
					}
				}
			} catch (Exception e) {
				session.setAttribute("Error", "Error: No se ha podido encontrar al empleado");
				e.printStackTrace();
			}
			
			System.out.println("Usted ha presionado la opcion buscar");
			
			request.setAttribute("lista", empleados);
			request.setAttribute("accion", "buscarSalario");
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
			requestDispatcher.forward(request, response);
			
		} else if (opcion.equals("busqueda")) {
			
			String sort = request.getParameter("sort");
			System.out.println("Ordenar por: "+sort);
			
			System.out.println(request.getParameter("nombre"));
			
			List<Empleado> empleados = new ArrayList<>();

			try {
				NominaDAO nominaDAO = new NominaDAO();
				
				//Actualizar salario en DB
				nominaDAO.calculaSalarioEmpleados();
			    System.out.println("Sueldo recalculado y actualizado");
				
				empleados = nominaDAO.obtenerEmpleadosPorX(request.getParameter("nombre"),sort);
				
				if (empleados.isEmpty()) {
					session.setAttribute("Error","No se ha encontrado el empleado");
				}else {
					for (Empleado empleado : empleados) {
						empleado.imprime();

					}
				}
			} catch (Exception e) {
				
				session.setAttribute("Error", "Error: No se ha podido encontrar al empleado");
				e.printStackTrace();
			}
			
			System.out.println("Usted ha presionado la opcion busqueda");
			
			request.setAttribute("lista", empleados);
			request.setAttribute("accion", "buscarEditar");
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
			requestDispatcher.forward(request, response);
			
		}else if (opcion.equals("editar")) {
			
			NominaDAO nominaDAO = new NominaDAO();
			Empleado e = null;
			
			String dni = (request.getParameter("dni"));
			System.out.println("Has elegido editar el empleado con DNI: "+dni);
			
			try {
				
				//Obtenemos empleado
				nominaDAO.obtenerEmpleado(dni);
				
				//Actualizar empleado con nuevos datos
				e = new Empleado(
						 request.getParameter("nombre"),
						 dni, 
						 request.getParameter("sexo").charAt(0),  
						 Integer.parseInt(request.getParameter("categoria")),  
						 Integer.parseInt(request.getParameter("anyo")), 
						 0);
					
				
				//Comprobaciones
				if (Character.toUpperCase(request.getParameter("sexo").charAt(0)) != 'M'
				        && Character.toUpperCase(request.getParameter("sexo").charAt(0)) != 'H') {
				    
				    session.setAttribute("Error", "Error: El sexo debe ser M: Mujer o H: Hombre.");

				    response.sendRedirect(request.getContextPath() + "/empresa?opcion=meditar&dni="+dni);
				}
				else {
					nominaDAO.editar(e);
					
					//Actualizar salario en DB
					nominaDAO.calculaSalarioEmpleados();
					
					//Listar con mensaje
					session.setAttribute("Error", "Se ha guardado y actualizado el empleado.");
					response.sendRedirect(request.getContextPath() + "/empresa?opcion=listar");
				 }
				
				
			} catch (SQLException | DatosNoCorrectosException ex) {
				session.setAttribute("Error", "Error: No se ha editar el empleado.");
				ex.printStackTrace();
				
				 // Prepara los parámetros
			    request.setAttribute("opcion", "busqueda");
			    
			    RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			    requestDispatcher.forward(request, response);
			    
			}
			
		}
		
	}

}