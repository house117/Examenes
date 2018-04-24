/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controlescolar.objects.Alumno;
import controlescolar.objects.Fecha;
import controlescolar.objects.Materia;
import controlescolar.objects.Profesor;
import java.util.ArrayList;
import exceptions.*;
import java.io.Serializable;

/**
 *
 * @author House
 */
public class ControlEscolar implements Serializable{
    private ArrayList<Alumno> listaAlumnos;
    private ArrayList<Profesor> listaProfesores;
    private ArrayList<Materia> listaMaterias;
    public ControlEscolar(ArrayList<Alumno> listaAlumnos, ArrayList<Materia> listaMaterias, 
            ArrayList<Profesor> listaProfesores){
        this.listaAlumnos = listaAlumnos;
        this.listaProfesores = listaProfesores;
        this.listaMaterias = listaMaterias;
    }
    public ControlEscolar(){
        listaAlumnos = new ArrayList<>();
        listaProfesores = new ArrayList<>();
        listaMaterias = new ArrayList<>();
    }
    public void addAlumno(Alumno e) throws NumeroControlRepetidoException{
        for(int i=0; i<getListaAlumnos().size(); i++){
            System.out.println(e.getNoControl()+" | "+getListaAlumnos().get(i).getNoControl());
            if(e.getNoControl().equals(getListaAlumnos().get(i).getNoControl())){
                throw new NumeroControlRepetidoException("Numero de control repetido");
            }
        }
        getListaAlumnos().add(e);
    }
    public void addProfesor(Profesor e) throws NumeroControlRepetidoException{
        for(int i=0; i<getListaProfesores().size(); i++){
            System.out.println(e.getNoControl()+" | "+getListaProfesores().get(i).getNoControl());
            if(e.getNoControl().equals(getListaProfesores().get(i).getNoControl())){
                throw new NumeroControlRepetidoException("Numero de control repetido");
            }
        }
        getListaProfesores().add(e);
    }
    public void addMateria(Materia e) throws NumeroControlRepetidoException{
        for(int i=0; i<getListaMaterias().size(); i++){
            System.out.println(e.getNoControl()+" | "+getListaMaterias().get(i).getNoControl());
            if(e.getNoControl().equals(getListaMaterias().get(i).getNoControl())){
                throw new NumeroControlRepetidoException("Numero de control repetido");
            }
        }
        getListaMaterias().add(e);
    }

    
    public Boolean existeNoControlAlumno(String noControl){
        for(int i=0; i<getListaAlumnos().size(); i++){
            if(noControl.equals(getListaAlumnos().get(i).getNoControl())){
                return true;
            }
        }
        return false;
    }
    public Boolean existeNoControlProfesor(String noControl){
        for(int i=0; i<getListaProfesores().size(); i++){
            if(noControl.equals(getListaProfesores().get(i).getNoControl())){
                return true;
            }
        }
        return false;
    }
    public Boolean existeNoControlMateria(String noControl){
        for(int i=0; i<getListaMaterias().size(); i++){
            if(noControl.equals(getListaMaterias().get(i).getNoControl())){
                return true;
            }
        }
        return false;
    }
        
    public Alumno buscarNoControl (String noControl){
        for(int i=0; i<getListaAlumnos().size(); i++){
            if(noControl.equals(getListaAlumnos().get(i).getNoControl())){
                return getListaAlumnos().get(i);
            }
        }
        return null;
    }
    public ArrayList<Alumno> buscarPorNombre (String nombre){
        ArrayList<Alumno> encontrados = new ArrayList<>();
        for(int i=0; i<getListaAlumnos().size(); i++){
            if(nombre.equals(getListaAlumnos().get(i).getNombre())){
                encontrados.add(getListaAlumnos().get(i));
            }
        }
        return encontrados;
    }
    public void eliminarAlumno(String noControl){
        for(int i=0; i<getListaAlumnos().size(); i++){
            if(noControl.equals(getListaAlumnos().get(i).getNoControl())){
                getListaAlumnos().remove(i);
            }
        }
    } 
    public void mostrarAlumno(String noControl){
        System.out.println("noControl: "+buscarNoControl(noControl).getNoControl());
        System.out.println("Nombre: "+buscarNoControl(noControl).getNombre());
        System.out.println("Apellido Paterno: "+buscarNoControl(noControl).getaPaterno());
        System.out.println("Apellido Materno: "+buscarNoControl(noControl).getaMaterno());
        System.out.println("Fecha de nacimiento: "+buscarNoControl(noControl).getFechaNac().toString());
        System.out.println("Carrera: "+buscarNoControl(noControl).getCarrera());
        System.out.println("Promedio: "+buscarNoControl(noControl).getPromedio());
    }
    public void modificarNombre(String noControl, String nuevoNombre){
        buscarNoControl(noControl).setNombre(nuevoNombre);
    }
    public void modificarApat(String noControl, String nuevoApat){
        buscarNoControl(noControl).setaPaterno(nuevoApat);
    }
    public void modificarAMat(String noControl, String nuevoAmat){
        buscarNoControl(noControl).setaMaterno(nuevoAmat);
    }
    public void modificarFechaNac(String noControl, Fecha nuevaFechaNac){
        buscarNoControl(noControl).setFechaNac(nuevaFechaNac);
    }
    public void modificarCarrera(String noControl, String nuevaCarrera){
        buscarNoControl(noControl).setCarrera(nuevaCarrera);
    }

    /**
     * @return the listaAlumnos
     */
    public ArrayList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    /**
     * @param listaAlumnos the listaAlumnos to set
     */
    public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public ArrayList<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(ArrayList<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
    

}
