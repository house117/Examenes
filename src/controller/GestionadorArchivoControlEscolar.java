/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Encriptador.EncriptadorDeArchivos;
import Encriptador.GestionadorArchivoEncriptado;
import Encriptador.ObjetoEncriptado;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author House
 */
public class GestionadorArchivoControlEscolar {
    public static void guardarArchivito(ControlEscolar b, String Name) throws FileNotFoundException, IOException{
        File file = new File(Name);
        FileOutputStream output = new FileOutputStream(file);
        ObjectOutputStream writer = new ObjectOutputStream(output);
        writer.writeObject(b);
        ObjetoEncriptado aGuardar = EncriptadorDeArchivos.encriptaArchivo(file);
        writer.close();
        output.close();
        File cryptedFile = new File(Name);
        FileOutputStream cryptedOutput = new FileOutputStream(cryptedFile);
        ObjectOutputStream cryptedWriter = new ObjectOutputStream(cryptedOutput);
        cryptedWriter.writeObject(aGuardar);
        cryptedWriter.close();
        cryptedOutput.close();
    }
        public static ControlEscolar abrirArchivo(File selected) throws FileNotFoundException, IOException, ClassNotFoundException{
        //File file = new File("miArchivo.txt");
        File file = selected;
        byte[] arregloDesencriptado = EncriptadorDeArchivos.desencriptarArchivo(file);
        File Temporal = GestionadorArchivoEncriptado.crearArchivito(arregloDesencriptado, "morirexd.morire");
        FileInputStream input = new FileInputStream(Temporal);
        ObjectInputStream reader = new ObjectInputStream(input);
        
        ControlEscolar resultado = (ControlEscolar)reader.readObject();
        Temporal.delete();
        reader.close();
        input.close();
        return resultado;
    }
    
}
