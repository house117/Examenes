/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen.objects;

import controlescolar.objects.Fecha;
import controlescolar.objects.Materia;
import controlescolar.objects.Profesor;
import java.util.ArrayList;

/**
 *
 * @author House
 */
public class Examen {
    private String nombre;
    private String noControl;
    private Profesor profesorCreador;
    private Materia materiaDelExamen;
    private ArrayList<Pregunta> listaPreguntas;
    private Fecha diaLimite;

    public Examen(String nombre, String noControl, Profesor profesorCreador, Materia materiaDelExamen, ArrayList<Pregunta> listaPreguntas, Fecha diaLimite) {
        this.nombre = nombre;
        this.noControl = noControl;
        this.profesorCreador = profesorCreador;
        this.materiaDelExamen = materiaDelExamen;
        this.listaPreguntas = listaPreguntas;
        this.diaLimite = diaLimite;
    }
    
    public Examen(ArrayList<Pregunta> listaPreguntas){
        this.listaPreguntas = listaPreguntas;
    }
    //Resetea todas las preguntas a sin contestar, a ver si se ocupa xdxd
    public void resetAll(){
        for(int i=0; i<listaPreguntas.size(); i++){
            listaPreguntas.get(i).reset();
        }
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the noControl
     */
    public String getNoControl() {
        return noControl;
    }

    /**
     * @param noControl the noControl to set
     */
    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    /**
     * @return the profesorCreador
     */
    public Profesor getProfesorCreador() {
        return profesorCreador;
    }

    /**
     * @param profesorCreador the profesorCreador to set
     */
    public void setProfesorCreador(Profesor profesorCreador) {
        this.profesorCreador = profesorCreador;
    }

    /**
     * @return the materiaDelExamen
     */
    public Materia getMateriaDelExamen() {
        return materiaDelExamen;
    }

    /**
     * @param materiaDelExamen the materiaDelExamen to set
     */
    public void setMateriaDelExamen(Materia materiaDelExamen) {
        this.materiaDelExamen = materiaDelExamen;
    }

    /**
     * @return the listaPreguntas
     */
    public ArrayList<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    /**
     * @param listaPreguntas the listaPreguntas to set
     */
    public void setListaPreguntas(ArrayList<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    /**
     * @return the diaLimite
     */
    public Fecha getDiaLimite() {
        return diaLimite;
    }

    /**
     * @param diaLimite the diaLimite to set
     */
    public void setDiaLimite(Fecha diaLimite) {
        this.diaLimite = diaLimite;
    }
}
