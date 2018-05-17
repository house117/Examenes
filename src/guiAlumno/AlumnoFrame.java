/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAlumno;

import guiProfesor.*;
import Examen.objects.Examen;
import Examen.objects.Pregunta;
import controlescolar.objects.Alumno;
import controlescolar.objects.Fecha;
import controlescolar.objects.Materia;
import controlescolar.objects.Profesor;
import controller.ControlEscolar;
import controller.ControlExamenes;
import controller.GestionadorArchivoControlEscolar;
import controller.GestionadorArchivoControlExamenes;
import exceptions.FechaInvalidaException;
import guiAdministrador.MainFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AlumnoFrame extends JFrame{
    private ControlPanelAlumno pnlControl;
    private TableExamenesAlumno pnlTabla;
    private TableRowSorter<TableModel> rowSorter;
    private Alumno usuario;
    private ControlExamenes controlExamenes;
    private ArrayList<Examen> examenesDelAlumno;
    public AlumnoFrame(String title, Alumno usuario, ControlEscolar controlEscolar, ControlExamenes controlExamenes){
        super(title);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(900, 600);
        super.setJMenuBar(createMenu());
        super.setLocationRelativeTo(null);
        //SETS THIS
        this.usuario = usuario;
        this.controlExamenes = controlExamenes;
        pnlControl = new ControlPanelAlumno();
        examenesDelAlumno = getExamenesAlumno();
        pnlTabla = new TableExamenesAlumno(this.examenesDelAlumno);
        
        super.add(pnlControl, BorderLayout.NORTH);
        super.add(pnlTabla, BorderLayout.CENTER);
        
        super.setVisible(true);
    }
    
    private ArrayList<Examen> getExamenesAlumno(){
        ArrayList<Examen> examenesDelAlumno = new ArrayList<>();
        for(int i=0; i<controlExamenes.getListaExamenes().size(); i++){
            for(int j=0; j<usuario.getMateriasDelAlumno().size(); j++){
                if(usuario.getMateriasDelAlumno().get(j).getNoControl().equals(controlExamenes.getListaExamenes().get(i).getMateriaDelExamen().getNoControl())
                        && usuario.getProfesoresDelAlumno().get(j).getNoControl().equals(controlExamenes.getListaExamenes().get(i).getProfesorCreador().getNoControl())){
                    examenesDelAlumno.add(controlExamenes.getListaExamenes().get(i));
                }
            }
        }
        return examenesDelAlumno;
    }
    
    private JMenuBar createMenu(){
        JMenuBar menu = new JMenuBar();
        
        //MENU ARCHIVO
        JMenu mmArchivo = new JMenu("Archivo");
        //SUBMENUS DE ARCHIVO
        //SUBMENU ABRIR
        JMenuItem nnAbrir = new JMenuItem("Abrir...");
        nnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                            JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(AlumnoFrame.this) == JFileChooser.APPROVE_OPTION){
                    //cargar el archivo 
                    try {
                        controlExamenes = GestionadorArchivoControlExamenes.abrirArchivo(fc.getSelectedFile());
                        //pnlTabla.removeAll();
                        pnlTabla.drawTabla(controlExamenes.getListaExamenes());
                        AlumnoFrame.this.repaint();
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(AlumnoFrame.this, "Archivo no encontrado", 
                                "Alerta", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }
        });
        //SUBMENÚ GUARDAR
        JMenuItem nnGuardar = new JMenuItem("Guardar...");
        nnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                               JFileChooser fc = new JFileChooser(); 
               if(fc.showSaveDialog(AlumnoFrame.this) == JFileChooser.APPROVE_OPTION){

                   System.out.println(fc.getSelectedFile());
                   File f = new File(fc.getSelectedFile().toString());
                   if(f.exists()){
                       int result = JOptionPane.showConfirmDialog(null,  //Un confirm dialog para sobrescribir el archivo
                               "Ese archivo ya existe, ¿desea sobrescribirlo?", 
                               "Archivo Existente",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                       if(result == JOptionPane.OK_OPTION){ //Si acepta sobrescribimos, si no pues no.
                           try {
                                GestionadorArchivoControlExamenes.guardarArchivito(controlExamenes, fc.getSelectedFile().toString());
                               } catch (IOException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                             }
                       }
                   }else{
                      try {
                          GestionadorArchivoControlExamenes.guardarArchivito(controlExamenes, fc.getSelectedFile().toString());
                      } catch (IOException ex) {
                          Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                      }
                       
                   }
               }
            }
        });
        JMenuItem nnSalir = new JMenuItem("Salir");
        nnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        mmArchivo.add(nnAbrir);
        mmArchivo.add(nnGuardar);
        mmArchivo.addSeparator();
        mmArchivo.add(nnSalir);
        
        //MENU EXAMENES
        JMenu mmExamen = new JMenu("Examenes");
        //SUBMENU AGREGAR EXAMEN
        
        
        menu.add(mmArchivo);
        menu.add(mmExamen);
        return menu;
    }
    
}
