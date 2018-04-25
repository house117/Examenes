/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlescolar.objects;

import java.io.Serializable;

/**
 *
 * @author House
 */
public class Materia implements Serializable{
    private String noControl;
    private String materia;
    private TipoCurso tipoCurso;
    private Double calificacion;
    private String profesorPrivilegiado; //solo se usara con el objeto materia de alumno

    public Materia(String noControl, String materia, TipoCurso tipoCurso, Double calificacion) {
        this.noControl = noControl;
        this.materia = materia;
        this.tipoCurso = tipoCurso;
        this.calificacion = calificacion;
    }
        public Materia(String noControl, String materia, TipoCurso tipoCurso) {
        this.noControl = noControl;
        this.materia = materia;
        this.tipoCurso = tipoCurso;
        this.calificacion = calificacion;
    }
        public Materia(String noControl, String materia, TipoCurso tipoCurso, Double calificacion,
                String profesorPrivilegiado) {
        this.noControl = noControl;
        this.materia = materia;
        this.tipoCurso = tipoCurso;
        this.calificacion = calificacion;
        this.profesorPrivilegiado = profesorPrivilegiado;
    }

    public Materia() {
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getProfesorPrivilegiado() {
        return profesorPrivilegiado;
    }

    public void setProfesorPrivilegiado(String profesorPrivilegiado) {
        this.profesorPrivilegiado = profesorPrivilegiado;
    }
    
}
