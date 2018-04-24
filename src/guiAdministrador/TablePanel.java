/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAdministrador;

import controlescolar.objects.Alumno;
import java.awt.BorderLayout;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author House
 */
public class TablePanel extends JPanel{
    /*
        Esta clase es de lo más importante pues aquí se muestran los alumnos
        Recibe un arrayList de alumnos y lo despliega con su constructor
    */
    private JScrollPane scrollPane;
    private JTable studentsTable;
    private DefaultTableModel tblmEstudiante;
    //Despliega la lista completa que recibe
    public TablePanel(ArrayList<Alumno> listaAlumnos){
        super();
        super.setLayout(null);
        String col[] = {"No.Control","Nombre","Ap.Paterno", "Ap.Materno", "Fecha Nac.", 
                            "Carrera", "Promedio"};
        tblmEstudiante = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int i, int i1){
                return false;
            }
            /*
                Esta parte es muy importante pues sobrescribimos
                el metodo isCellEditable de DefaultTableModel
                para así evitar que las celdas de la tabla
                sean editables.
            */
        };
        drawTabla(listaAlumnos);
        
        studentsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(!lse.getValueIsAdjusting()){
                  JPanel mostrarAlumno = new JPanel();
                String noControl = studentsTable.getValueAt(studentsTable.getSelectedRow(), 0).toString();
                System.out.println("clicked: "+studentsTable.getValueAt(studentsTable.getSelectedRow(), 0).toString());
                for(int i=0; i<listaAlumnos.size(); i++){
                    //System.out.println("me ejecute xd");
                    if(noControl.equals(listaAlumnos.get(i).getNoControl())){
                       // System.out.println("FUI ECUAL XD");
                        
                        mostrarAlumno.setLayout(new BoxLayout(mostrarAlumno, WIDTH));
                        mostrarAlumno.add(new JLabel("No control: "+listaAlumnos.get(i).getNoControl()));
                        mostrarAlumno.add(Box.createHorizontalStrut(15)); 
                        mostrarAlumno.add(new JLabel("Nombre: "+listaAlumnos.get(i).getNombre()));
                        mostrarAlumno.add(Box.createHorizontalStrut(15)); 
                        mostrarAlumno.add(new JLabel("Apellido paterno: "+listaAlumnos.get(i).getaPaterno()));
                        mostrarAlumno.add(Box.createHorizontalStrut(15)); 
                        mostrarAlumno.add(new JLabel("Apellido materno: "+listaAlumnos.get(i).getaMaterno()));
                        mostrarAlumno.add(Box.createHorizontalStrut(15)); 
                        mostrarAlumno.add(new JLabel("Fecha de nacimiento: "+listaAlumnos.get(i).getFechaNac().toString()));
                        mostrarAlumno.add(Box.createHorizontalStrut(15)); 
                        mostrarAlumno.add(new JLabel("Carrera: "+listaAlumnos.get(i).getCarrera()));
                        mostrarAlumno.add(Box.createVerticalStrut(15)); 
                        
                         mostrarAlumno.add(new JLabel(String.format("Promedio general: %.2f", listaAlumnos.get(i).getPromedio())));
                        mostrarAlumno.add(Box.createVerticalStrut(15)); 
                        
                        for(int j=0; j<listaAlumnos.get(i).getMateriasDelAlumno().size(); j++){
                            mostrarAlumno.add(new JLabel(String.format("Materia %d: %s", j, 
                                    listaAlumnos.get(i).getMateriasDelAlumno().get(j).getMateria())));
                            mostrarAlumno.add(new JLabel(String.format("Calificación %s: %s",
                                    listaAlumnos.get(i).getMateriasDelAlumno().get(j).getMateria(), 
                                    listaAlumnos.get(i).getMateriasDelAlumno().get(j).getCalificacion().toString())));
                            mostrarAlumno.add(new JLabel(String.format("Tipo curso: %s",
                                    listaAlumnos.get(i).getMateriasDelAlumno().get(j).getTipoCurso())));
                            mostrarAlumno.add(Box.createVerticalStrut(15));
                        }
                        int result = JOptionPane.showConfirmDialog(null, mostrarAlumno, "Información del alumno",
                            JOptionPane.CLOSED_OPTION); 
                        //TablePanel.this.repaint();
                    }
                }
                  
                }
                
                        
            }
        });
    }
    //Método para agregar un alumno más a la lista.
    public void addAlumno(Alumno alumno){
        //cadenas innecesarias, solo de apoyo
        String noControl;
        String nombre;
        String aPaterno;
        String aMaterno;
        String fecha;
        String carrera;
        String promedio;
       //Prints en consola de apoyo
            System.out.println("Control = "+alumno.getNoControl());
            System.out.println("Nombre = "+alumno.getNombre());
            System.out.println("Apellido pat= "+ alumno.getaPaterno());
            System.out.println("Apellido mat= "+alumno.getaMaterno());
            System.out.println("fecha nac= "+alumno.getFechaNac().toString());
            System.out.println("carrera= "+alumno.getCarrera());
            System.out.println("promedio= "+alumno.getPromedio().toString());
                noControl = alumno.getNoControl();
                nombre = alumno.getNombre();
                aPaterno = alumno.getaPaterno();
                aMaterno = alumno.getaMaterno();
                fecha = alumno.getFechaNac().toString();
                carrera = alumno.getCarrera();
                promedio = String.format("%.2f", alumno.getPromedio());
                Object[] data = {noControl, nombre, aPaterno,
                    aMaterno, fecha, carrera, promedio};
                tblmEstudiante.addRow(data);
    }
    public void drawTabla(ArrayList<Alumno> aMostrar){
        //Método de dibujar la tabla, es llamado por el constructor para desplejar
        //la tabla completa
        super.setLayout(new BorderLayout());
        //Hace que el Layout sea Border para
        System.out.println("ME EJECUTE");
        //metodo que obtiene un arrayList de alumnos y lo agrega a una tabla y
        //agrega esta al JPanel
        
        String noControl;
        String nombre;
        String aPaterno;
        String aMaterno;
        String fecha;
        String carrera;
        String promedio;
        for(int i=0; i<aMostrar.size(); i++){    
            System.out.println("ENTRE AL FOR veces: "+i);
            System.out.println("Control = "+aMostrar.get(i).getNoControl());
            System.out.println("Nombre = "+aMostrar.get(i).getNombre());
            System.out.println("Apellido pat= "+ aMostrar.get(i).getaPaterno());
            System.out.println("Apellido mat= "+aMostrar.get(i).getaMaterno());
            System.out.println("fecha nac= "+aMostrar.get(i).getFechaNac().toString());
            System.out.println("carrera= "+aMostrar.get(i).getCarrera());
            System.out.println("promedio= "+aMostrar.get(i).getPromedio().toString());
                noControl = aMostrar.get(i).getNoControl();
                nombre = aMostrar.get(i).getNombre();
                aPaterno = aMostrar.get(i).getaPaterno();
                aMaterno = aMostrar.get(i).getaMaterno();
                fecha = aMostrar.get(i).getFechaNac().toString();
                carrera = aMostrar.get(i).getCarrera();
                promedio = String.format("%.2f", aMostrar.get(i).getPromedio());
                Object[] data = {noControl, nombre, aPaterno,
                    aMaterno, fecha, carrera, promedio};
                tblmEstudiante.addRow(data);
                //Agrega la fila a table Model estudiante
                
        }
        
        //creamos la tabla y le agregamos el modelo
        studentsTable = new JTable(tblmEstudiante);
        studentsTable.setRowSelectionAllowed(true);
        //el setRow no es necesario pero meh
        
       
        scrollPane = new JScrollPane(studentsTable);
        //scrollPane.setPreferredSize(new Dimension(200, 400));
        studentsTable.setFillsViewportHeight(true);
        
        super.add(scrollPane, BorderLayout.CENTER);
        
    }
    //Hacer el drawTabla para cuando busque un alumno
    /*
    A ese le agregaremos 2 tablas, una con los datos del alumno
    y una con los datos de sus respectivas materias
    */

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTable getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(JTable studentsTable) {
        this.studentsTable = studentsTable;
    }

    public DefaultTableModel getTblmEstudiante() {
        return tblmEstudiante;
    }

    public void setTblmEstudiante(DefaultTableModel tblmEstudiante) {
        this.tblmEstudiante = tblmEstudiante;
    }
}
