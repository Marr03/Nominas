package com.nominas.model;

import com.nominas.excepcion.DatosNoCorrectosException;

public class Empleado extends Persona {
    public int categoria;
    public int anyos;
    public int sueldo;

    // Constructor
    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos, int sueldo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (categoria <= 0 || categoria > 10 || anyos < 0) {
            throw new DatosNoCorrectosException("Datos no correctos");
        }
        this.categoria = categoria;
        this.anyos = anyos;
        this.sueldo = sueldo;
    }
    

    

    public int getCategoria() {
        return categoria;
    }




	public void setCategoria(int categoria) {
        this.categoria = categoria;
    }


    public int getAnyos() {
        return anyos;
    }
    
    public int getSueldo() {
        return sueldo;
    }
    
    public void setSueldo(int sueldo) {
    	this.sueldo = sueldo;
    }
    
    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }


    public void incrAnyo() {
        anyos++;
    }

	@Override
	public void imprime() {
		System.out.println("Empleado [categoria=" + categoria + ", anyos=" + anyos + ", sueldo=" + sueldo + ", nombre=" + nombre
				+ ", dni=" + dni + ", sexo=" + sexo + "]");
	}



}