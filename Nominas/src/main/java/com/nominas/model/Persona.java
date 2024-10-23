package com.nominas.model;

public class Persona {

	public String nombre;
	public String dni;
	public char sexo;
	
	
	/**
	 * Constructor 1 Persona con todos los atributos
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Persona(String nombre, String dni, char sexo) {
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}
	
	/**
	 * Constructor 2 Persona
	 * @param nombre
	 * @param sexo
	 */
	public Persona(String nombre, char sexo) {
		this.nombre = nombre;
		this.sexo = sexo;
	}


	/**
	 * Imprime el dni y el nombre del empleado
	 */
	public void imprime() {
		System.out.println("DNI:"+ dni);
		System.out.println("Nombre:"+ nombre);
	}


	/**
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return nombre
	 */
	public String getNombre() {
	    return nombre;
	}

	/**
	 * @return dni
	 */
	public String getDni() {
	    return dni;
	}

	/**
	 * @return sexo
	 */
	public char getSexo() {
	    return sexo;
	}



	
	
	
	
	
	
	
}