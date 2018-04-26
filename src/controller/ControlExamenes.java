/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Examen.objects.Examen;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author House
 */
public class ControlExamenes implements Serializable{
    private ArrayList <Examen> listaExamenes = new ArrayList<>();
    public ControlExamenes(){
        listaExamenes = new ArrayList<Examen>();
    }

    /**
     * @return the listaExamenes
     */
    public Boolean existeNoControl(String noControl){
        for(int i=0; i<listaExamenes.size(); i++){
            if(noControl.equals(listaExamenes.get(i).getNoControl())){
                return true;
            }
        }
        return false;
    }
    public ArrayList <Examen> getListaExamenes() {
        return listaExamenes;
    }

    /**
     * @param listaExamenes the listaExamenes to set
     */
    public void setListaExamenes(ArrayList <Examen> listaExamenes) {
        this.listaExamenes = listaExamenes;
    }
}
