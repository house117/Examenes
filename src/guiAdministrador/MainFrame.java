/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAdministrador;

import com.sun.xml.internal.ws.util.xml.XmlUtil;
import controlescolar.objects.*;
import controller.*;
import exceptions.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import listeners.ControlListener;


/**
 *
 * @author House
 */
public class MainFrame extends JFrame{
    private ControlPanel pnlControl; //Control en la parte de arriba
    private TablePanel pnlTabla;     //Contenedor para la tabla parte CENTER
    private ControlEscolar control;  //Objeto Control escolar poseedor de la información
    //CONSTRUCTOR PARA CREAR VENTANA SIN DATOS
    //UNICAMENTE CON EL NOMBRE
    private TableRowSorter<TableModel> rowSorter;
            
    public MainFrame(String title){
        super(title);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(800, 600);
        super.setLayout(new BorderLayout());
        super.setJMenuBar(createMenu());
        //super.setJMenuBar(createMenu());
        super.setLocationRelativeTo(null);
        control = new ControlEscolar();
        
        pnlControl = new ControlPanel();
        pnlTabla = new TablePanel(control.getListaAlumnos());
        super.add(pnlControl, BorderLayout.NORTH);
        super.add(pnlTabla, BorderLayout.CENTER);
        super.setVisible(true);
    }
    //Segundo Constructor Que recibe archivo
    //Si ya existe un archivo se utiliza este constructor
    //Sólo iguala control con el control escolar obtenido del archivo
    public MainFrame(String title, ControlEscolar archivoPrincipal){
        super(title);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(800, 600);
        super.setLayout(new BorderLayout());
        super.setJMenuBar(createMenu());
        //super.setJMenuBar(createMenu());
        super.setLocationRelativeTo(null);
        control = archivoPrincipal;
        
        
        
        pnlControl = new ControlPanel();
        pnlControl.setListener(new ControlListener() {
            @Override
            public void btnBusquedaControlOnClick(JTextField controlP) {
                rowSorter = new TableRowSorter<>(pnlTabla.getStudentsTable().getModel());
                pnlTabla.getStudentsTable().setRowSorter(rowSorter);
                controlP.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent de) {
                        if(controlP.getText().trim().length() == 0){
                            rowSorter.setRowFilter(null);
                        }else{
                            rowSorter.setRowFilter(RowFilter.regexFilter(controlP.getText(), 0));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent de) {
                        if(controlP.getText().trim().length() == 0){
                            rowSorter.setRowFilter(null);
                        }else{
                            rowSorter.setRowFilter(RowFilter.regexFilter(controlP.getText(), 0));
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent de) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }

            @Override
            public void btnBusquedaNombreOnClick(JTextField nombre) {
                rowSorter = new TableRowSorter<>(pnlTabla.getStudentsTable().getModel());
                pnlTabla.getStudentsTable().setRowSorter(rowSorter);
                nombre.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent de) {
                        if(nombre.getText().trim().length() == 0){
                            rowSorter.setRowFilter(null);
                        }else{
                            rowSorter.setRowFilter(RowFilter.regexFilter(nombre.getText(), 1));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent de) {
                        if(nombre.getText().trim().length() == 0){
                            rowSorter.setRowFilter(null);
                        }else{
                            rowSorter.setRowFilter(RowFilter.regexFilter(nombre.getText(), 1));
                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent de) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
        });
        pnlTabla = new TablePanel(control.getListaAlumnos());
        super.add(pnlControl, BorderLayout.NORTH);
        super.add(pnlTabla, BorderLayout.CENTER);
        super.setVisible(true);
    }

    //JMENUBAR DE LA PARTE DE ARRIBA
    private JMenuBar createMenu(){
        JMenuBar menu = new JMenuBar();
        
        //MENU ARCHIVO
        JMenu mmArchivo = new JMenu("Archivo");
        
        //SUBMENUS DE ARCHIVO
        //SUBMENÚ ABRIR
        JMenuItem nnAbrir = new JMenuItem("Abrir...");
        nnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    //cargar el archivo 
                    try {
                        control = GestionadorArchivo.abrirArchivo(fc.getSelectedFile());
                        //pnlTabla.removeAll();
                        pnlTabla.drawTabla(control.getListaAlumnos());
                        
                        MainFrame.this.repaint();
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Archivo no encontrado", 
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
               if(fc.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){

                   System.out.println(fc.getSelectedFile());
                   File f = new File(fc.getSelectedFile().toString());
                   if(f.exists()){
                       int result = JOptionPane.showConfirmDialog(null,  //Un confirm dialog para sobrescribir el archivo
                               "Ese archivo ya existe, ¿desea sobrescribirlo?", 
                               "Archivo Existente",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                       if(result == JOptionPane.OK_OPTION){ //Si acepta sobrescribimos, si no pues no.
                           try {
                                GestionadorArchivo.guardarArchivito(control, fc.getSelectedFile().toString());
                               } catch (IOException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                             }
                       }
                   }else{
                      try {
                          GestionadorArchivo.guardarArchivito(control, fc.getSelectedFile().toString());
                      } catch (IOException ex) {
                          Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                      }
                       
                   }
               }
            }
        });
        //SUBMENÚ SALIR
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
        
        //MENU ALUMNOS
        //AGREGAR ALUMNO
        JMenu mmAlumnos = new JMenu("Alumnos");
        JMenuItem nnAgregarAlumno = new JMenuItem("Agregar Alumno...");
        nnAgregarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nombre;
                String aPaterno;
                String aMaterno;
                Fecha fechaNac;
                String carrera;
                String noControl = JOptionPane.showInputDialog("Ingresa el numero de control");
                ArrayList<Materia> listaMaterias;
                String contraseniaAlumno;
                /*Pendiente agregar también la lista de Materias, y con ellas calcular el promedio*/
                if(!control.existeNoControlAlumno(noControl)){
                    nombre = JOptionPane.showInputDialog("Ingresa el nombre del alumno");
                    aPaterno = JOptionPane.showInputDialog("Ingresa el apellido paterno del alumno");
                    aMaterno = JOptionPane.showInputDialog("Ingresa el apellido materno del alumno");
                    //Para introducir fecha más comodamente
                    
                    JTextField diaField = new JTextField(5);
                    JTextField mesField = new JTextField(5);
                    JTextField anioField = new JTextField(5);

                        
                    /*Para fecha utilizamos una opción múltiple, si bien podemos utilizarla para toda
                    la inserción de datos, por ahora sólo utilizamos con fecha porque me da flojera jajaja
                    */
                    JPanel myPanel = new JPanel(); //Creamos un panel, esto para poder agregar los 
                                                       //JTextFields, y los Labels para recibir la información
                    myPanel.add(new JLabel("Dia:"));
                    myPanel.add(diaField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Mes:"));
                    myPanel.add(mesField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Año:"));
                    myPanel.add(anioField);
                    int result = JOptionPane.showConfirmDialog(null, myPanel, "Ingresa la fecha de nacimiento",
                            JOptionPane.OK_CANCEL_OPTION); //Con esto tenemos un boton de ok/cancel, muy util
                    if(result == JOptionPane.OK_OPTION){ //Si la opcion es OK, procedemos
                        try {
                            fechaNac = new Fecha(Integer.parseInt(diaField.getText()),
                                    Integer.parseInt(mesField.getText()),
                                    Integer.parseInt(anioField.getText()));
                            carrera = JOptionPane.showInputDialog("Ingresa la carrera del alumno");
                            
                            try {
                                /*
                                Sólo hasta el final después de corroborar la información, procedemos a
                                agregar al nuevo alumno con los datos obtenidos
                                lo agregamos al control, y a la tabla, 
                                        */
                                JTextField cantMaterias = new JTextField(5);
                                JPanel pnlMaterias = new JPanel();
   
                                pnlMaterias.add(new JLabel("Cantidad de materias:"));
                                pnlMaterias.add(cantMaterias);
                                
                                int resultMaterias = JOptionPane.showConfirmDialog(null, pnlMaterias, "Ingresa la cantidad de materias",
                                    JOptionPane.OK_CANCEL_OPTION); //Con esto tenemos un boton de ok/cancel, muy util
                                if(resultMaterias == JOptionPane.OK_OPTION){
                                    listaMaterias = new ArrayList<>();
                                    for(int i=0; i<Integer.parseInt(cantMaterias.getText()); i++){
                                        
                                        JPanel seleccionMateria = new JPanel();
                                        seleccionMateria.setLayout(new BoxLayout(seleccionMateria, WIDTH));
                                        JTextField calificacionField = new JTextField(5);
                                        
            
                                        String[] listaNombresMaterias = new String[control.getListaMaterias().size()];
                                        for(int j=0; j<control.getListaMaterias().size(); j++){
                                            listaNombresMaterias[j] = control.getListaMaterias().get(j).getMateria();
                                        }
                                            
                                        
                                        JComboBox cboxMaterias = new JComboBox(listaNombresMaterias);
                                        seleccionMateria.add(cboxMaterias);
                                        seleccionMateria.add(new JLabel("Calificacion de esta materia:"));
                                        seleccionMateria.add(calificacionField);
                                        int materiaSeleccionada = JOptionPane.showConfirmDialog(null, seleccionMateria, "Selecciona la materia"+i,
                                            JOptionPane.OK_CANCEL_OPTION);
                                        if(materiaSeleccionada == JOptionPane.OK_OPTION){
                                            Materia seleccionada;
                                            for(int j=0; j<control.getListaMaterias().size(); j++){
                                                System.out.println(control.getListaMaterias().get(j).getMateria()+" = "+(String)cboxMaterias.getSelectedItem());
                                                if(control.getListaMaterias().get(j).getMateria().equals((String)cboxMaterias.getSelectedItem())
                                                        /*&& Integer.parseInt(calificacionField.getText())>=0 
                                                        && Integer.parseInt(calificacionField.getText())<=10*/){
                                                    System.out.println("logro entrar al if porque son iwales!!!");
                                                    seleccionada = new Materia(control.getListaMaterias().get(j).getNoControl(), 
                                                            control.getListaMaterias().get(j).getMateria(), 
                                                            control.getListaMaterias().get(j).getTipoCurso(),
                                                            Double.parseDouble(calificacionField.getText()));
                                                    listaMaterias.add(seleccionada);
                                                }
                                            }
    
                                        }
                                    }
                                    JTextField contraseniaField = new JTextField();
                                    JTextField confirmacionField = new JTextField();
                                    
                                    do{
                                        JPanel contrasenia = new JPanel();
                                        contrasenia.setLayout(new BoxLayout(contrasenia, WIDTH));
                                        contrasenia.add(new JLabel("Ingrese contraseña:" ));
                                        contrasenia.add(contraseniaField);
                                        myPanel.add(Box.createVerticalStrut(15)); // a spacer
                                        contrasenia.add(new JLabel("Confirme la contraseñaa:" ));
                                        contrasenia.add(confirmacionField);
                                        int contraseniaOk = JOptionPane.showConfirmDialog(null, contrasenia, "Ingresa la contraseña",
                                            JOptionPane.OK_CANCEL_OPTION);
                                        if(contraseniaOk == JOptionPane.OK_OPTION){
                                            if(!contraseniaField.getText().equals(confirmacionField.getText())){
                                                JOptionPane.showMessageDialog(contrasenia, "Las contraseñas no coinciden");
                                            }else{
                                                contraseniaAlumno = contraseniaField.getText();
                                                Alumno aIngresar = new Alumno(noControl, nombre, aPaterno,
                                                aMaterno, fechaNac, carrera, listaMaterias, contraseniaAlumno);
                                                control.addAlumno(aIngresar);
                                                pnlTabla.addAlumno(aIngresar);
                                                MainFrame.this.repaint();
                                            }
                                        }
                                    }while(!contraseniaField.getText().equals(confirmacionField.getText()));
                                    
                                    
                                    
                                    
                                }
                                
                                //Repintamos MainFrame, aunque queda pendiente probar si es necesario
                                //puesto que JTable se actualiza solita.
                            } catch (CalificacionInvalidaException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NumeroControlRepetidoException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (FechaInvalidaException ex) {
                            ex.printStackTrace();
                            
                        }
                    }
                     
                    
                }else{
                    JOptionPane.showMessageDialog(MainFrame.this, "Ya existe ese numero de control");
                }
            }
        });
        mmAlumnos.add(nnAgregarAlumno);
        
        JMenu mmProfesores = new JMenu("Profesores");
        JMenuItem nnAgregarProfesor = new JMenuItem("Agregar profesor...");
        nnAgregarProfesor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nombre;
                String aPaterno;
                String aMaterno;
                Fecha fechaNac;
                ArrayList<Materia> listaMaterias;
                String contraseniaProfe;
                String noControl = JOptionPane.showInputDialog("Ingresa el numero de control");
                /*Pendiente agregar también la lista de Materias, y con ellas calcular el promedio*/
                if(!control.existeNoControlProfesor(noControl)){
                    nombre = JOptionPane.showInputDialog("Ingresa el nombre del profesor");
                    aPaterno = JOptionPane.showInputDialog("Ingresa el apellido paterno del profesor");
                    aMaterno = JOptionPane.showInputDialog("Ingresa el apellido materno del profesor");
                    //Para introducir fecha más comodamente
                    
                    JTextField diaField = new JTextField(5);
                    JTextField mesField = new JTextField(5);
                    JTextField anioField = new JTextField(5);

                        
                    /*Para fecha utilizamos una opción múltiple, si bien podemos utilizarla para toda
                    la inserción de datos, por ahora sólo utilizamos con fecha porque me da flojera jajaja
                    */
                    JPanel myPanel = new JPanel(); //Creamos un panel, esto para poder agregar los 
                                                       //JTextFields, y los Labels para recibir la información
                    myPanel.add(new JLabel("Dia:"));
                    myPanel.add(diaField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Mes:"));
                    myPanel.add(mesField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Año:"));
                    myPanel.add(anioField);
                    int result = JOptionPane.showConfirmDialog(null, myPanel, "Ingresa la fecha de nacimiento",
                            JOptionPane.OK_CANCEL_OPTION); //Con esto tenemos un boton de ok/cancel, muy util
                    if(result == JOptionPane.OK_OPTION){ //Si la opcion es OK, procedemos
                        try {
                            fechaNac = new Fecha(Integer.parseInt(diaField.getText()),
                                    Integer.parseInt(mesField.getText()),
                                    Integer.parseInt(anioField.getText()));
                            try {
                                /*
                                Sólo hasta el final después de corroborar la información, procedemos a
                                agregar al nuevo alumno con los datos obtenidos
                                lo agregamos al control, y a la tabla, 
                                        */
                                JTextField cantMaterias = new JTextField(5);
                                JPanel pnlMaterias = new JPanel();
   
                                pnlMaterias.add(new JLabel("Cantidad de materias:"));
                                pnlMaterias.add(cantMaterias);
                                
                                int resultMaterias = JOptionPane.showConfirmDialog(null, pnlMaterias, "Ingresa la cantidad de materias",
                                    JOptionPane.OK_CANCEL_OPTION); //Con esto tenemos un boton de ok/cancel, muy util
                                if(resultMaterias == JOptionPane.OK_OPTION){
                                    
                                    listaMaterias = new ArrayList<>();
                                    for(int i=0; i<Integer.parseInt(cantMaterias.getText()); i++){
                                        
                                        JPanel seleccionMateria = new JPanel();
                                        seleccionMateria.setLayout(new BoxLayout(seleccionMateria, WIDTH));
                                        String[] listaNombresMaterias = new String[control.getListaMaterias().size()];
                                        for(int j=0; j<control.getListaMaterias().size(); j++){
                                            listaNombresMaterias[j] = control.getListaMaterias().get(j).getMateria();
                                        }
                                        JComboBox cboxMaterias = new JComboBox(listaNombresMaterias);
                                        seleccionMateria.add(cboxMaterias);
                                        int materiaSeleccionada = JOptionPane.showConfirmDialog(null, seleccionMateria, "Selecciona la materia"+i,
                                            JOptionPane.OK_CANCEL_OPTION);
                                        if(materiaSeleccionada == JOptionPane.OK_OPTION){
                                            Materia seleccionada;
                                            for(int j=0; j<control.getListaMaterias().size(); j++){
                                                System.out.println(control.getListaMaterias().get(j).getMateria()+" = "+(String)cboxMaterias.getSelectedItem());
                                                if(control.getListaMaterias().get(j).getMateria().equals((String)cboxMaterias.getSelectedItem())
                                                        /*&& Integer.parseInt(calificacionField.getText())>=0 
                                                        && Integer.parseInt(calificacionField.getText())<=10*/){
                                                    System.out.println("logro entrar al if porque son iwales!!!");
                                                    seleccionada = new Materia(control.getListaMaterias().get(j).getNoControl(), 
                                                            control.getListaMaterias().get(j).getMateria(), 
                                                            control.getListaMaterias().get(j).getTipoCurso());
                                                    listaMaterias.add(seleccionada);
                                                }
                                            }
    
                                        }
                                    }
                                    JTextField contraseniaField = new JTextField();
                                    JTextField confirmacionField = new JTextField();
                                    
                                    do{
                                        JPanel contrasenia = new JPanel();
                                        contrasenia.setLayout(new BoxLayout(contrasenia, WIDTH));
                                        contrasenia.add(new JLabel("Ingrese contraseña:" ));
                                        contrasenia.add(contraseniaField);
                                        myPanel.add(Box.createVerticalStrut(15)); // a spacer
                                        contrasenia.add(new JLabel("Confirme la contraseñaa:" ));
                                        contrasenia.add(confirmacionField);
                                        int contraseniaOk = JOptionPane.showConfirmDialog(null, contrasenia, "Ingresa la contraseña",
                                            JOptionPane.OK_CANCEL_OPTION);
                                        if(contraseniaOk == JOptionPane.OK_OPTION){
                                            if(!contraseniaField.getText().equals(confirmacionField.getText())){
                                                JOptionPane.showMessageDialog(contrasenia, "Las contraseñas no coinciden");
                                            }else{
                                                contraseniaProfe = contraseniaField.getText();
                                                Profesor aIngresar = new Profesor(noControl, nombre, aPaterno,
                                                aMaterno, fechaNac, listaMaterias, contraseniaProfe);
                                                control.addProfesor(aIngresar);
                                            }
                                        }
                                    }while(!contraseniaField.getText().equals(confirmacionField.getText()));
                                }
                                
                                
                            } catch (CalificacionInvalidaException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NumeroControlRepetidoException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (FechaInvalidaException ex) {
                            ex.printStackTrace();
                            
                        }
                    }
                     
                    
                }else{
                    JOptionPane.showMessageDialog(MainFrame.this, "Ya existe ese numero de control");
                }
            }
        });
        mmProfesores.add(nnAgregarProfesor);
        
        JMenu mmMaterias = new JMenu("Materias");
        JMenuItem nnAgregarMateria = new JMenuItem("Agregar materia...");
        nnAgregarMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                /*
                    private Integer noControl;
                    private String materia;
                    private TipoCurso tipoCurso;
                    private Double calificacion;
                
                */
                JPanel myPanel = new JPanel(); //Creamos un panel, esto para poder agregar los 
                                                       //JTextFields, y los Labels para recibir la información
                    myPanel.setLayout(new BoxLayout(myPanel, WIDTH));
                    JTextField noControlField = new JTextField(5);
                    JTextField nombreField = new JTextField(5);
                    JComboBox tipoCursoBox = new JComboBox(TipoCurso.values());
                    myPanel.add(new JLabel("NoControl:"));
                    myPanel.add(noControlField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Nombre:"));
                    myPanel.add(nombreField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("Tipo Curso:"));
                    myPanel.add(tipoCursoBox);
                    int result = JOptionPane.showConfirmDialog(null, myPanel, "Ingresa la fecha de nacimiento",
                            JOptionPane.OK_CANCEL_OPTION); //Con esto tenemos un boton de ok/cancel, muy util
                    if(result == JOptionPane.OK_OPTION){
                        if(!control.existeNoControlMateria(noControlField.getText())){
                            
                            Materia nuevaMateria = new Materia(noControlField.getText(), 
                                    nombreField.getText(), (TipoCurso)tipoCursoBox.getSelectedItem());
                            try {
                                control.addMateria(nuevaMateria);
                            } catch (NumeroControlRepetidoException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            JOptionPane.showMessageDialog(MainFrame.this, "Ya existe ese numero de control");
                        }
                            
                    }
                
            }
        });
        mmMaterias.add(nnAgregarMateria);
        
        
        menu.add(mmArchivo);
        menu.add(mmAlumnos);
        menu.add(mmProfesores);
        menu.add(mmMaterias);
        return menu;
    }
    
}
