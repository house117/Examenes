/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen.objects;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author House
 */
public class Pregunta {
    private String enunciadio;
    private ImageIcon imagen;
    private ArrayList<String> opciones;
    private ArrayList<String> respuestas;
    private EstadoPregunta estado;
    public Pregunta(String enuncadio, ArrayList<String>opciones, ArrayList<String> respuestas){
        this.enunciadio = enuncadio;
        this.opciones = opciones;
        this.respuestas = respuestas;
    }
        public Pregunta(String enuncadio, ImageIcon imagen, ArrayList<String>opciones, ArrayList<String> respuestas){
        this.enunciadio = enuncadio;
        this.imagen = imagen;
        this.opciones = opciones;
        this.respuestas = respuestas;
        estado = EstadoPregunta.SINCONTESTAR;
    }
    public void reset(){
            setEstado(EstadoPregunta.SINCONTESTAR);
    }

    /**
     * @return the estado
     */
    public EstadoPregunta getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoPregunta estado) {
        this.estado = estado;
    }

    public String getEnunciadio() {
        return enunciadio;
    }

    public void setEnunciadio(String enunciadio) {
        this.enunciadio = enunciadio;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public ArrayList<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<String> opciones) {
        this.opciones = opciones;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }
}
