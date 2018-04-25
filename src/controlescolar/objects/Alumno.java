/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlescolar.objects;

import exceptions.CalificacionInvalidaException;
import exceptions.FechaInvalidaException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author House
 */
public class Alumno implements Serializable{
    private String noControl; 
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private Fecha fechaNac;
    private String Carrera;
    private Double promedio;
    private String password;
    private ArrayList<Materia> materiasDelAlumno;
    private ArrayList<Profesor> profesoresDelAlumno;
    public Alumno(String noControl, String nombre, String aPaterno,
            String aMaterno, Fecha fechaNac, String Carrera, ArrayList<Materia> materiasDelAlumno,
            ArrayList<Profesor> profesoresDelAlumno,
            String password) //Quitar el promedio y reciba la lista materias
            throws FechaInvalidaException, CalificacionInvalidaException {
        this.noControl = noControl;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.fechaNac = new Fecha(fechaNac.getDia(), fechaNac.getMes(), fechaNac.getAnio());
        this.Carrera = Carrera;
        this.materiasDelAlumno = materiasDelAlumno;
        this.profesoresDelAlumno = profesoresDelAlumno; 
        this.password = password;
        promedio = calcularPromedio();
    }
    public Double calcularPromedio(){
        Double suma = 0.0;
        for(int i=0; i<materiasDelAlumno.size(); i++){
            suma += materiasDelAlumno.get(i).getCalificacion();
        }
        suma /= materiasDelAlumno.size();
        return suma;
    }
    public Alumno(String noControl, String nombre, String aPaterno,
            String aMaterno, Fecha fechaNac, String Carrera, Double promedio,
            ArrayList<Materia> materias) throws FechaInvalidaException, CalificacionInvalidaException {
        this.noControl = noControl;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.fechaNac = new Fecha(fechaNac.getDia(), fechaNac.getMes(), fechaNac.getAnio());
        this.Carrera = Carrera;
        if(promedio < 0 || promedio >10){
            throw new CalificacionInvalidaException("Promedio no esta entre 1 y 10");
        }else{
            this.promedio = promedio;
        }
        this.materiasDelAlumno = materias;
    }

    public Alumno() {
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public Fecha getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Fecha fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Materia> getMateriasDelAlumno() {
        return materiasDelAlumno;
    }

    public void setMateriasDelAlumno(ArrayList<Materia> materiasDelAlumno) {
        this.materiasDelAlumno = materiasDelAlumno;
    }

    /**
     * @return the profesoresDelAlumno
     */
    public ArrayList<Profesor> getProfesoresDelAlumno() {
        return profesoresDelAlumno;
    }

    /**
     * @param profesoresDelAlumno the profesoresDelAlumno to set
     */
    public void setProfesoresDelAlumno(ArrayList<Profesor> profesoresDelAlumno) {
        this.profesoresDelAlumno = profesoresDelAlumno;
    }
    
    
}
