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
public class Profesor implements Serializable{
    private String noControl; 
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private Fecha fechaNac;
    private String password;
    private ArrayList<Materia> materiasDelProfesor;
    public Profesor(String noControl, String nombre, String aPaterno,
            String aMaterno, Fecha fechaNac, ArrayList<Materia> listaMaterias,
            String password) 
            throws FechaInvalidaException, CalificacionInvalidaException {
        this.noControl = noControl;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.fechaNac = new Fecha(fechaNac.getDia(), fechaNac.getMes(), fechaNac.getAnio());
        this.materiasDelProfesor = listaMaterias;
        this.password = password;
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

    public ArrayList<Materia> getMateriasDelAlumno() {
        return materiasDelAlumno;
    }

    public void setMateriasDelAlumno(ArrayList<Materia> materiasDelAlumno) {
        this.materiasDelAlumno = materiasDelAlumno;
    }
    private ArrayList<Materia> materiasDelAlumno;

    public ArrayList<Materia> getMateriasDelProfesor() {
        return materiasDelProfesor;
    }

    public void setMateriasDelProfesor(ArrayList<Materia> materiasDelProfesor) {
        this.materiasDelProfesor = materiasDelProfesor;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
