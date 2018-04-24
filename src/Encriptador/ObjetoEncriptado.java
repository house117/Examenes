/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encriptador;

import java.io.Serializable;

/**
 *
 * @author House
 */
public class ObjetoEncriptado implements Serializable{
    private byte[] arregloBytes;
    public ObjetoEncriptado(byte[] arregloBytes){
        this.arregloBytes = arregloBytes;
    }

    /**
     * @return the arregloBytes
     */
    public byte[] getArregloBytes() {
        return arregloBytes;
    }
}
