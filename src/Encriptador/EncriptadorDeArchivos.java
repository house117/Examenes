/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encriptador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import Encriptador.GestionadorArchivoEncriptado;

/**
 *
 * @author House
 */
public class EncriptadorDeArchivos {
    public static final byte SUMADOR = 77;
    public static ObjetoEncriptado encriptaArchivo(File archivo) throws IOException{
        byte[] arregloBytes = Files.readAllBytes(archivo.toPath());
        for(int i=0; i<arregloBytes.length; i++){
            //System.out.println("Byte value BEFORE encrypt: "+arregloBytes[i]);
            //¬í ur [B¬óøTà  xp N§¹

            if(arregloBytes[i]+SUMADOR > 127){
                arregloBytes[i] = (byte)((arregloBytes[i]+SUMADOR)-256);
            }else{
                arregloBytes[i] = (byte) (arregloBytes[i]+SUMADOR);
            }
            
            
            //System.out.println("Byte value AFTER encrypt: "+arregloBytes[i]);
            
        }
        ObjetoEncriptado encriptado = new ObjetoEncriptado(arregloBytes);
        //archivo.delete();
        return encriptado;
    }
    public static byte[] desencriptarArchivo(File archivo) throws IOException, FileNotFoundException, ClassNotFoundException{
                byte[] arregloBytes = GestionadorArchivoEncriptado.abrirArchivo(archivo).getArregloBytes();
                
        for(int i=0; i<arregloBytes.length; i++){
            //System.out.println("Byte value BEFORE decrypt: "+arregloBytes[i]);
            if(arregloBytes[i]-SUMADOR < -128){
                arregloBytes[i] = (byte)((arregloBytes[i]-SUMADOR)+256);
            }else{
                arregloBytes[i] = (byte) (arregloBytes[i]-SUMADOR);
            }
            //System.out.println("Byte value AFTER decrypt: "+arregloBytes[i]);
        }
        return arregloBytes;
    }
    
    
            
    
}
