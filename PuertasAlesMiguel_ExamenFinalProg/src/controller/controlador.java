package controller;

import java.util.Comparator;
import java.util.regex.Pattern;

import models.*;

public class controlador {

	public static float pagoTotalDia = 0f;
	public static ListaCochesOrdenada<Coche> Parking = new ListaCochesOrdenada<Coche>(new Comparator<Coche>(){
		@Override		
		public int compare(Coche o1, Coche o2){
					return o2.getTiempoIni()-o1.getTiempoIni();
				}
			});
	
	public static Coche Buscar(String matricula){
		Coche aux = null;
		for(Coche i: Parking){
			if(i.getMatricula().equals(matricula)){
				aux = i;
				break;
			}
		}
		
		return aux;
	}
	
	public static boolean ValidarFecha(String fecha){
		return Pattern.matches("([12][0-9]|3[01]|0?[1-9])/(0?[1-9]|1[012])/((?:19|20)\\d\\d)", fecha);
	}
}
