/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlescolar.objects;

/**
 *
 * @author House
 */
import exceptions.FechaInvalidaException;
import java.io.Serializable;

//Tarea ver pelicula Mente Indomable
//the good wild hunting
//cada comportamiento como ua entidad separada
//sobre carga de metodos diferente lista de parametros 
//TAREA HACER LA CLASE ALUMNO BIEN BIEN BIEN
//ALUMNO DE LA SALLE, NOMBRE, FECHA NAC, CALCULAR LA EDAD CON LA FECHA ACTUAL 
//CARRERA, AÑO QUE ENTRASTE, CORREO ELECTRONICO, TWITTER FACEBOOK
public class Fecha implements Serializable{
	private Integer mes;  //Variable de instancia de numero de días.
	private Integer dia;  //Variable atributo que indica el mes
	private Integer anio; //Variable atributo que indica el año
	public Fecha(Integer dia, Integer mes, Integer anio) throws FechaInvalidaException{
		
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;  	
		/*if((dia < 0 ) || (dia > diasDelMes())) {
			throw new FechaInvalidaException("Nel prro");
		}*/
		validar();
	}
	public void setDia(int dia) { //metodo para establecer dia
		this.dia = dia;
	}
	public Integer getDia() {   //metodo para obtener dia
		return dia;
	}
	public void setMes(int mes) {  //metodo para establecer mes
		this.mes = mes;

	}
	public Integer getMes() {       //metodo para obtener mes
		return mes;
	}
	public void setAnio(int anio) { //metodo para establecer año
		this.anio = anio;
	}
	public Integer getAnio() {        //metodo para obtener año
		return anio;
	}
	public String toString() {
		String cadena = String.format("%d/%d/%d ", dia, mes, anio);
		return cadena;
	}
	/*Método esAnioBisiesto
	 * Verifica si el año en cuestión es bisiesto
	 */
	public boolean esAnioBisiesto() { //metodo boolean para verificar si el año es bisiesto
		 return (anio % 4 == 0) && (anio % 100 != 0 || anio % 400 == 0);
	}
	
	private void validar() throws FechaInvalidaException {
		if(dia < 1) {
			throw new FechaInvalidaException("El dia debe ser mayor a 0");
		}else if (dia > diasDelMes()) {
			throw new FechaInvalidaException(String.format("El mes %d no tiene %d dias", mes, dia));
		}
	}
        //RECORDAR AGREGAR EL VALIDADOR DE FECHA PARA LA LECTURA DE DATOS!!!!
        /*private void validar() throws FechaInvalidaException {
		if(dia < 1) {
			throw new FechaInvalidaException("El dia debe ser mayor a 0");
		}else if (dia > diasDelMes()) {
			throw new FechaInvalidaException(String.format("El mes %d no tiene %d dias", mes, dia));
		}
	}*/
	/*Método diasDelMes
	 * Obtiene la cantidad de días en el mes en cuestión
	 */
	private Integer diasDelMes() throws FechaInvalidaException {
		switch(mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if(esAnioBisiesto()) {
				return 29;
			}
			if(!esAnioBisiesto()) {
				return 28;
			}
		default: throw new FechaInvalidaException(String.format("El mes %d no es valido", mes));
		}
	}
	/*Método diasMesAnterior
	 * Obtiene la cantidad de días del 
	 * mes anterior al actual en la
	 * variable de instacia mes */
	private Integer diasMesAnterior() {
		switch(mes) {
		case 5:
		case 7:
		case 10:
		case 12:
			return 30;
		case 9:
		case 4:
		case 6:
		case 11:
		case 1:
		case 2:
		case 8:
			return 31;
		case 3:
			if(esAnioBisiesto()) {
				return 29;
			}
			if(!esAnioBisiesto()) {
				return 28;
			}
		default: return 0;
		}
	}
	/*Método que aumenta un dia
	 * a la fecha actual*/
	public void aumentarDia() throws FechaInvalidaException{ 
		dia++;
		if(dia > diasDelMes()) {
			dia = 1;
			mes++;
			if(mes > 12) {
				mes = 1;
				anio++;
			}
		}
	}
	/*Método que aumenta N dias 
	 * a la fecha actual
	 */
	public void aumentarDia(Integer cantDias) throws FechaInvalidaException{
		for(int i=0; i<cantDias; i++) {
			aumentarDia();
		}
	}
	/*Método que resta un dia
	 * a la fecha actual
	 */
	public void restarDia() {
		dia--;
		if(dia < 1) {
			dia = diasMesAnterior();
			mes--;
			if(mes < 1) {
				mes = 12;
				anio--;
			}
		}
	}
	/*Método que resta N dias 
	 * a la fecha actual
	 */
	public void restarDia(Integer cantDias) {
		for(int i=0; i<cantDias; i++) {
			restarDia();
		}
	}
	/*Método esMayorQue, verifica si una fecha dada
	 * es mayor a la fecha en cuestión, según el resultado
	 * que devuelve:
	 * Devuelve positivo: la fecha en cuestión es mayor
	 * Devuelve 0: las fechas son iguales
	 * Devuelve negativo: la fecha en cuestión es menor
	 */
	public Integer esMayorQue(Fecha fechita) {
		Integer resultado = this.anio-fechita.getAnio();
		if(resultado == 0) {
			resultado = this.mes-fechita.getMes();
					if(resultado == 0) {
						resultado = this.dia-fechita.getDia();
					}
		}
		return resultado;
	}
	private Integer esMayorQueParcial(Fecha fechita) {
		Integer resultado = this.mes-fechita.getMes();
		if(resultado == 0) {
			resultado = this.dia-fechita.getDia();
		}
		return resultado;
	}
	public Integer aniosEntreFechas(Fecha fechita) throws FechaInvalidaException{
		Integer anios = this.anio-fechita.getAnio();
		if(fechita.esMayorQue(this) > 0) {
			throw new FechaInvalidaException("La fecha dada debe ser menor o igual");
		}else {
			Integer resultado = this.anio - fechita.getAnio();
			if(this.esMayorQueParcial(fechita) < 0) {
				resultado --;
			}
			return resultado;
		}
	}
}
