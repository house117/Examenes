/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAlumno;

import guiProfesor.*;
import Examen.objects.Examen;
import controlescolar.objects.Alumno;
import controlescolar.objects.Fecha;
import exceptions.FechaInvalidaException;
import guiAdministrador.MainFrame;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TableExamenesAlumno extends JPanel{
    private JScrollPane scrollPane;
    private JTable examenesTable;
    private DefaultTableModel tblmExamenesAlumno;
    private final int NUMERODEPREGUNTAS = 3;
    public TableExamenesAlumno(ArrayList<Examen> listaExamenes){
        super();
        super.setLayout(null);
        String col[] = {"No.Control", "Nombre Examen","Materia","Profesor","Fecha límite"};
        tblmExamenesAlumno = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int i, int il){
                return false;
            }
        };
        drawTabla(listaExamenes);
        examenesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                try {
                    String noControl = (String) examenesTable.getValueAt(examenesTable.getSelectedRow(), 0);
                    Examen aPresentar = null;
                    for(int i=0; i<listaExamenes.size(); i++){
                        if(noControl.equals(listaExamenes.get(i).getNoControl())){
                            aPresentar = listaExamenes.get(i);
                        }
                    }
                    LocalDate currentDate = LocalDate.now();
                    Fecha hoy = new Fecha(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
                    
                    if(!(aPresentar.getFechaLimite().esMayorQue(hoy) >0)){
                        PresentadorDeExamen.PresentarExamen(aPresentar);
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya no puedes presentar ese examen prro xD");
                    }
                    
                } catch (FechaInvalidaException ex) {
                    Logger.getLogger(TableExamenesAlumno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
     public void addExamen(Examen aMostrar){
        //cadenas innecesarias, solo de apoyo
        String noControl;
        String nombre;
        String materia;
        String profesor;
        String fechaLimite;
       //Prints en consola de apoyo
                noControl = aMostrar.getNoControl();
                nombre = aMostrar.getNombre();
                materia = aMostrar.getMateriaDelExamen().getMateria();
                profesor = String.format("%s %s %s", aMostrar.getProfesorCreador().getNombre(),
                        aMostrar.getProfesorCreador().getaPaterno(),
                        aMostrar.getProfesorCreador().getaMaterno());
                fechaLimite = aMostrar.getFechaLimite().toString();
                Object[] data = {noControl, nombre, materia, profesor, fechaLimite};
                tblmExamenesAlumno.addRow(data);

    }
    public void drawTabla(ArrayList<Examen> aMostrar){
        //Método de dibujar la tabla, es llamado por el constructor para desplejar
        //la tabla completa
        super.setLayout(new BorderLayout());
        //Hace que el Layout sea Border para
        System.out.println("ME EJECUTE");
        //metodo que obtiene un arrayList de alumnos y lo agrega a una tabla y
        //agrega esta al JPanel
        for(int i=0; i<tblmExamenesAlumno.getRowCount(); i++){
            tblmExamenesAlumno.removeRow(i);
        }
        String noControl;
        String nombre;
        String materia;
        String profesor;
        String fechaLimite;
        for(int i=0; i<aMostrar.size(); i++){    
            System.out.println("ENTRE AL FOR veces: "+i);
            System.out.println("Control = "+aMostrar.get(i).getNoControl());
            System.out.println("Nombre = "+aMostrar.get(i).getNombre());
            System.out.println("Materia= "+aMostrar.get(i).getMateriaDelExamen().getMateria());
            System.out.println("Profesor= "+aMostrar.get(i).getProfesorCreador());
                noControl = aMostrar.get(i).getNoControl();
                nombre = aMostrar.get(i).getNombre();
                materia = aMostrar.get(i).getMateriaDelExamen().getMateria();
                fechaLimite = aMostrar.get(i).getFechaLimite().toString();
                profesor = String.format("%s %s %s", aMostrar.get(i).getProfesorCreador().getNombre(),
                        aMostrar.get(i).getProfesorCreador().getaPaterno(),
                        aMostrar.get(i).getProfesorCreador().getaMaterno());
                Object[] data = {noControl, nombre, materia, profesor,fechaLimite};
                tblmExamenesAlumno.addRow(data);
                //Agrega la fila a table Model estudiante
                
        }
        
        //creamos la tabla y le agregamos el modelo
        examenesTable = new JTable(tblmExamenesAlumno);
        examenesTable.setRowSelectionAllowed(true);
        //el setRow no es necesario pero meh
        
       
        setScrollPane(new JScrollPane(examenesTable));
        //scrollPane.setPreferredSize(new Dimension(200, 400));
        examenesTable.setFillsViewportHeight(true);
        
        super.add(getScrollPane(), BorderLayout.CENTER);
        
    }

    /**
     * @return the scrollPane
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * @param scrollPane the scrollPane to set
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * @return the examenesTable
     */
    public JTable getExamenesTable() {
        return examenesTable;
    }

    /**
     * @param examenesTable the examenesTable to set
     */
    public void setExamenesTable(JTable examenesTable) {
        this.examenesTable = examenesTable;
    }

    /**
     * @return the tblmExamenesAlumno
     */
    public DefaultTableModel getTblmExamenesProfesor() {
        return tblmExamenesAlumno;
    }

    /**
     * @param tblmExamenesProfesor the tblmExamenesAlumno to set
     */
    public void setTblmExamenesProfesor(DefaultTableModel tblmExamenesProfesor) {
        this.tblmExamenesAlumno = tblmExamenesProfesor;
    }
}
