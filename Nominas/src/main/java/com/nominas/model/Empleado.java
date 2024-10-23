package com.nominas.model;

import com.nominas.excepcion.DatosNoCorrectosException;

public class Empleado extends Persona {
    public int categoria;
    public int anyos;
    public int sueldo;

    /**
     * Contructor Empleado con atributos Persona. categoria y anyos por defecto
     * @param nombre
     * @param dni
     * @param sexo
     */
    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    /**
     * Constructor Empleado con todos los atributos, con comprobacion 
     * ----> categoria entre 0 y 10
     * ----> anyos igual o mayor que 0
     * @param nombre
     * @param dni
     * @param sexo
     * @param categoria
     * @param anyos
     * @param sueldo
     * @throws DatosNoCorrectosException
     */
    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos, int sueldo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (categoria <= 0 || categoria > 10 || anyos < 0) {
            throw new DatosNoCorrectosException("Datos no correctos");
        }
        this.categoria = categoria;
        this.anyos = anyos;
        this.sueldo = sueldo;
    }
    

    
    /**
     * @return
     */
    public int getCategoria() {
        return categoria;
    }



    /**
     * @param categoria
     */
	public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

	/**
	 * @return anyos
	 */
    public int getAnyos() {
        return anyos;
    }
    
    /**
     * @return sueldo
     */
    public int getSueldo() {
        return sueldo;
    }
    
    
    /**
     * @param sueldo
     */
    public void setSueldo(int sueldo) {
    	this.sueldo = sueldo;
    }
    
    /**
     * @param anyos
     */
    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }


    /**
     * Incrementa los anyos en uno
     */
    public void incrAnyo() {
        anyos++;
    }

    
    /**
     * Imprime el empleado y todos sus parametros
     */
	@Override
	public void imprime() {
		System.out.println("Empleado [categoria=" + categoria + ", anyos=" + anyos + ", sueldo=" + sueldo + ", nombre=" + nombre
				+ ", dni=" + dni + ", sexo=" + sexo + "]");
	}



}