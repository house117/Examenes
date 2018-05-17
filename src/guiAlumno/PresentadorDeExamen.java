/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAlumno;

import Examen.objects.Examen;

/**
 *
 * @author House
 */
public class PresentadorDeExamen {
    public static final int Max = 2;
    public static final int Min = 0;
    public static void PresentarExamen(Examen examen){
       
       Integer a = Min + (int)(Math.random() * ((Max - Min) + 1));
       Integer preguntasFaltantes = 0;
       do{
           
       }while(preguntasFaltantes<Max);
    }
    
}
