package com.nominas.model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Nomina {

	
	private static final int SUELDO_BASE[] =
		{50000, 70000, 90000, 110000, 130000,
		150000, 170000, 190000, 210000, 230000};

	
	public int sueldo(Empleado e) {
		
		int sueldo_base = SUELDO_BASE[e.getCategoria() - 1];
		
		int sueldo = sueldo_base + 5000*e.anyos;
		
		return sueldo;
	}
	
	
	

	


	
}