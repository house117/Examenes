/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiProfesor;

import Examen.objects.Examen;
import Examen.objects.Pregunta;
import controlescolar.objects.Fecha;
import controlescolar.objects.Profesor;
import controller.ControlEscolar;
import exceptions.FechaInvalidaException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ProfesorFrame extends JFrame{
    private ControlPanelProfesor pnlControl;
    private TableExamenesProfesor pnlTabla;
    private ArrayList<Examen> examenes; //quitar luego y cambiar por ControlExamenes
    private TableRowSorter<TableModel> rowSorter;
    private Profesor usuario;
    public ProfesorFrame(String title, Profesor usuario, ControlEscolar controlEscolar){
        super(title);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(900, 600);
        super.setJMenuBar(createMenu());
        super.setLocationRelativeTo(null);
        //SETS THIS
        this.usuario = usuario;
        this.examenes = new ArrayList<Examen>();
        pnlControl = new ControlPanelProfesor();
        pnlTabla = new TableExamenesProfesor(examenes);
        
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
        //SUBMENÚ GUARDAR
        JMenuItem nnGuardar = new JMenuItem("Guardar...");
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
                Integer cantPreguntas;
                Fecha fechaLimite;
                ArrayList<Pregunta> listaPreguntas;
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
                                setOpcionesYrespuestas.setLayout(new BoxLayout(setOpcionesYrespuestas, WIDTH));
                                JPanel pnlTituloOpciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
                                pnlTituloOpciones.add(new JLabel("Rellena las opciones de respuesta:"));
                                setOpcionesYrespuestas.add(pnlTituloOpciones);
                                for(int k=0; k<Integer.parseInt(cantOpciones.getText()); k++){
                                    JPanel pnlOpcion = new JPanel();
                                    pnlOpcion.setLayout(new FlowLayout(FlowLayout.LEFT));
                                    JLabel lblOpcion = new JLabel("Opcion "+k);
                                    JTextField txtOpcion = new JTextField(5);
                                    pnlOpcion.add(lblOpcion);
                                    pnlOpcion.add(txtOpcion);
                                    setOpcionesYrespuestas.add(pnlOpcion);
                                    setOpcionesYrespuestas.add(Box.createVerticalStrut(5));
                                }
                                    setOpcionesYrespuestas.add(Box.createVerticalStrut(10));
                                 JPanel pnlTituloRespuestas = new JPanel(new FlowLayout(FlowLayout.LEFT));
                                pnlTituloRespuestas.add(new JLabel("Rellena las opciones de respuesta:"));
                                setOpcionesYrespuestas.add(pnlTituloRespuestas);
                                for(int k=0; k<Integer.parseInt(cantRespuestas.getText());k++){
                                    JPanel pnlRespuesta = new JPanel();
                                    pnlRespuesta.setLayout(new FlowLayout(FlowLayout.LEFT));
                                    JLabel lblRespuesta = new JLabel("Respuesta "+k);
                                    JTextField txtRespuesta = new JTextField(5);
                                    pnlRespuesta.add(lblRespuesta);
                                    pnlRespuesta.add(txtRespuesta);
                                    setOpcionesYrespuestas.add(pnlRespuesta);
                                    setOpcionesYrespuestas.add(Box.createVerticalStrut(5));
                                    
                                }
                                int DatosPreguntaOk = JOptionPane.showConfirmDialog(null, 
                                            setOpcionesYrespuestas, "Ingresa datos de la pregunta "+i,
                                            JOptionPane.OK_CANCEL_OPTION);
                                    if(DatosPreguntaOk == JOptionPane.OK_OPTION){
                                        System.out.println("Funciono prro xD");
                                    }
                                 
                            }//help
                            
                            
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
