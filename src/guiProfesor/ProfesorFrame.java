/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiProfesor;

import Examen.objects.Examen;
import Examen.objects.Pregunta;
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

public class ProfesorFrame extends JFrame{
    private ControlPanelAlumno pnlControl;
    private TableExamenesAlumno pnlTabla;
    private TableRowSorter<TableModel> rowSorter;
    private Profesor usuario;
    private ControlExamenes controlExamenes;
    public ProfesorFrame(String title, Profesor usuario, ControlEscolar controlEscolar, ControlExamenes controlExamenes){
        super(title);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(900, 600);
        super.setJMenuBar(createMenu());
        super.setLocationRelativeTo(null);
        //SETS THIS
        this.usuario = usuario;
        this.controlExamenes = controlExamenes;
        pnlControl = new ControlPanelAlumno();
        pnlTabla = new TableExamenesAlumno(this.controlExamenes.getListaExamenes());
        
        super.add(pnlControl, BorderLayout.NORTH);
        super.add(pnlTabla, BorderLayout.CENTER);
        
        super.setVisible(true);
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
                if (fc.showOpenDialog(ProfesorFrame.this) == JFileChooser.APPROVE_OPTION){
                    //cargar el archivo 
                    try {
                        controlExamenes = GestionadorArchivoControlExamenes.abrirArchivo(fc.getSelectedFile());
                        //pnlTabla.removeAll();
                        pnlTabla.drawTabla(controlExamenes.getListaExamenes());
                        ProfesorFrame.this.repaint();
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(ProfesorFrame.this, "Archivo no encontrado", 
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
               if(fc.showSaveDialog(ProfesorFrame.this) == JFileChooser.APPROVE_OPTION){

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
        JMenuItem nnAgregarExamen = new JMenuItem("Agregar Examen...");
        nnAgregarExamen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String noControl;
                String nombre;
                String materia;
                Materia materiaAAgregar;
                Integer cantPreguntas;
                Fecha fechaLimite;
                ArrayList<Pregunta> listaPreguntas = new ArrayList<>();
                String profesor = String.format("%s %s %s", usuario.getNombre(),
                        usuario.getaPaterno(),
                        usuario.getaMaterno());
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new BoxLayout(myPanel, WIDTH));
                JTextField noControlField = new JTextField(5);
                JTextField nombreField = new JTextField(5);
                JTextField cantidadPreguntasField = new JTextField(5);
                JTextField diaField = new JTextField(5);
                JTextField mesField = new JTextField(5);
                JTextField anioField = new JTextField(5);
                String[] listaMaterias = new String[usuario.getMateriasDelProfesor().size()];
                for(int i=0; i< usuario.getMateriasDelProfesor().size(); i++){
                    listaMaterias[i] = usuario.getMateriasDelProfesor().get(i).getMateria();
                }
                JComboBox cboxMaterias = new JComboBox(listaMaterias);
                myPanel.add(new JLabel("NoControl:"));
                myPanel.add(noControlField);
                myPanel.add(Box.createVerticalStrut(15)); //espaciador
                myPanel.add(new JLabel("Nombre:"));
                myPanel.add(nombreField);
                myPanel.add(Box.createVerticalStrut(15));
                myPanel.add(new JLabel("Ingresa la fecha límite para el examen:"));
                JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panelFecha.add(new JLabel("Dia:"));
                panelFecha.add(diaField);
                panelFecha.add(Box.createHorizontalStrut(15)); // a spacer
                panelFecha.add(new JLabel("Mes:"));
                panelFecha.add(mesField);
                panelFecha.add(Box.createHorizontalStrut(15)); // a spacer
                panelFecha.add(new JLabel("Año:"));
                panelFecha.add(anioField);
                myPanel.add(panelFecha);
                myPanel.add(Box.createVerticalStrut(15));
                myPanel.add(new JLabel("Selecciona la materia:"));
                myPanel.add(cboxMaterias);
                myPanel.add(Box.createVerticalStrut(15));
                myPanel.add(new JLabel("Ingresa la cantidad de preguntas para este examen"));
                myPanel.add(cantidadPreguntasField);

                
                int result = JOptionPane.showConfirmDialog(null, myPanel, "Ingresa datos para el examen",
                            JOptionPane.OK_CANCEL_OPTION); //Con esto tenemos un boton de ok/cancel, muy util
                if(result == JOptionPane.OK_OPTION){
                    try {
                        noControl = noControlField.getText();
                        nombre = nombreField.getText();
                        materia = (String)cboxMaterias.getSelectedItem();
                        fechaLimite = new Fecha(Integer.parseInt(diaField.getText()),
                                Integer.parseInt(mesField.getText()),
                                Integer.parseInt(anioField.getText()));
                        cantPreguntas = Integer.parseInt(cantidadPreguntasField.getText());
                        for(int i=0; i<cantPreguntas; i++){
                            ArrayList<String> opciones = new ArrayList<>();
                            ArrayList<String> respuestas = new ArrayList<>();
                            String enunciado;
                            System.out.println("entra al for de cantidad de preguntas"); //SYSOUT!!!
                            JTextField enunciadoField = new JTextField();
                            JTextField cantOpciones = new JTextField();
                            JTextField cantRespuestas = new JTextField();
                            JPanel setPregunta = new JPanel();
                            setPregunta.setLayout(new BoxLayout(setPregunta, WIDTH));
                            setPregunta.add(new JLabel("Enunciado de la pregunta:"));
                            setPregunta.add(enunciadoField);
                            setPregunta.add(Box.createVerticalStrut(10));
                            setPregunta.add(new JLabel("Cantidad de opciones:"));
                            setPregunta.add(cantOpciones);
                            setPregunta.add(Box.createVerticalStrut(10));
                            setPregunta.add(new JLabel("Cantidad de respuestas:"));
                            setPregunta.add(cantRespuestas);
                            setPregunta.add(Box.createVerticalStrut(10));
                            int DatosExamenOk = JOptionPane.showConfirmDialog(null, setPregunta, "Ingresa datos del examen ",
                                            JOptionPane.OK_CANCEL_OPTION);
                            if(DatosExamenOk == JOptionPane.OK_OPTION){
                                JPanel setOpcionesYrespuestas = new JPanel();
                                JPanel[] pnlOpcion = new JPanel[Integer.parseInt(cantOpciones.getText())];
                                JPanel[] pnlRespuesta = new JPanel[Integer.parseInt(cantRespuestas.getText())];
                                JTextField[] txtOpcion = new JTextField[Integer.parseInt(cantOpciones.getText())];
                                JTextField[] txtRespuesta = new JTextField[Integer.parseInt(cantRespuestas.getText())];
                                setOpcionesYrespuestas.setLayout(new BoxLayout(setOpcionesYrespuestas, WIDTH));
                                JPanel pnlTituloOpciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
                                pnlTituloOpciones.add(new JLabel("Rellena las opciones de respuesta:"));
                                setOpcionesYrespuestas.add(pnlTituloOpciones);
                                for(int k=0; k<Integer.parseInt(cantOpciones.getText()); k++){
                                    pnlOpcion[k] = new JPanel();
                                    pnlOpcion[k].setLayout(new FlowLayout(FlowLayout.LEFT));
                                    JLabel lblOpcion = new JLabel("Opcion "+k);
                                    txtOpcion[k] = new JTextField(30);
                                    pnlOpcion[k].add(lblOpcion);
                                    pnlOpcion[k].add(txtOpcion[k]);
                                    setOpcionesYrespuestas.add(pnlOpcion[k]);
                                    setOpcionesYrespuestas.add(Box.createVerticalStrut(10));
                                }
                                    setOpcionesYrespuestas.add(Box.createVerticalStrut(10));
                                JPanel pnlTituloRespuestas = new JPanel(new FlowLayout(FlowLayout.LEFT));
                                pnlTituloRespuestas.add(new JLabel("Rellena las respuestas correctas:"));
                                setOpcionesYrespuestas.add(pnlTituloRespuestas);
                                for(int k=0; k<Integer.parseInt(cantRespuestas.getText());k++){
                                    pnlRespuesta[k] = new JPanel();
                                    pnlRespuesta[k].setLayout(new FlowLayout(FlowLayout.LEFT));
                                    JLabel lblRespuesta = new JLabel("Respuesta "+k);
                                    txtRespuesta[k] = new JTextField(30);
                                    pnlRespuesta[k].add(lblRespuesta);
                                    pnlRespuesta[k].add(txtRespuesta[k]);
                                    setOpcionesYrespuestas.add(pnlRespuesta[k]);
                                    setOpcionesYrespuestas.add(Box.createVerticalStrut(10));
                                    
                                }
                                enunciado = enunciadoField.getText();
                                int DatosPreguntaOk = JOptionPane.showConfirmDialog(null, 
                                            setOpcionesYrespuestas, "Ingresa datos de la pregunta "+i,
                                            JOptionPane.OK_CANCEL_OPTION);
                                    if(DatosPreguntaOk == JOptionPane.OK_OPTION){
                                        for(int k=0; k<Integer.parseInt(cantOpciones.getText()); k++){
                                            opciones.add(txtOpcion[k].getText());
                                   
                                        }
                                        for(int k=0; k<Integer.parseInt(cantRespuestas.getText());k++){
                                            opciones.add(txtRespuesta[k].getText());
                                        }
                                        Pregunta pregunta = new Pregunta(enunciado, opciones, respuestas);
                                        listaPreguntas.add(pregunta);
                                    }
                                 
                            }//help
                            
                            
                        }
                        //Aqui termina ese for para cantidad de preguntas
                        for(int k=0; k<usuario.getMateriasDelProfesor().size(); k++){
                            if(materia.equals(usuario.getMateriasDelProfesor().get(k).getMateria())){
                                materiaAAgregar = usuario.getMateriasDelProfesor().get(k);
                                Examen examen = new Examen(nombre, noControl, usuario, materiaAAgregar, 
                                listaPreguntas, fechaLimite);
                                controlExamenes.getListaExamenes().add(examen);
                                pnlTabla.addExamen(examen);
                            }
                        }
                    } catch (FechaInvalidaException ex) {
                        Logger.getLogger(ProfesorFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        //ADDS A MENU EXAMEN
        mmExamen.add(nnAgregarExamen);
        
        menu.add(mmArchivo);
        menu.add(mmExamen);
        return menu;
    }
    
}
