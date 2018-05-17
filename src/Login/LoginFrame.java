/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import controller.*;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Examen.objects.TipoUsuario;
import controlescolar.objects.Alumno;
import controlescolar.objects.Profesor;
import guiAdministrador.MainFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import controller.*;
import guiAlumno.AlumnoFrame;
import guiProfesor.ProfesorFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/**
 *
 * @author House
 */
public class LoginFrame extends JFrame{
    JPanel panelsito;
    JPanel bordeEast;
    JPanel bordeWest;
    JPanel bordeNorth;
    JPanel bordeSouth;
    JPanel pnlLogIn;
    JPanel pnlUsuario;
    JPanel pnlPassword;
    JLabel lblUser;
    JTextField txtUser;
    JLabel lblpassword;
    JPasswordField txtPassword;
    JLabel lblTipoUsuario;
    JComboBox cboxTipoUsuario;
    JButton btnLogIn;
    JLabel titulo;
    ActionListener listenerLogIn;
    public LoginFrame(String title, ControlEscolar controlEscolar){
        super(title);
        super.setLayout(new BorderLayout());
        
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(450,300);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        
        //PANEL QUE TENDRA EL BOX LAYOUT Y LOS BORDES
        panelsito = new JPanel();
        panelsito.setLayout(new BoxLayout(panelsito, BoxLayout.PAGE_AXIS));
       
        //PANEL DENTRO DE PANELSITO QUE TENDRA BOTON ACEPTAR Y JCOMBOBOX
        pnlLogIn = new JPanel();
        pnlLogIn.setLayout(new FlowLayout(FlowLayout.LEFT));
        //PANEL USUARIO DENTRO DE PANELSITO
        pnlUsuario = new JPanel();
        pnlUsuario.setLayout(new FlowLayout(FlowLayout.LEFT));
        //PANEL CONTRASEÑA DENTRO DE PANELSITO
        pnlPassword = new JPanel();
        pnlPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
        //pnl EAST
        bordeEast = new JPanel();
        //bordeEast.setBackground(Color.yellow);
        bordeEast.setPreferredSize(new Dimension(70, 300));
        //PNL WEST
        bordeWest = new JPanel();
        //bordeWest.setBackground(Color.red);
        bordeWest.setPreferredSize(new Dimension(70, 300));
        //PNL NORTH
        bordeNorth = new JPanel();
        titulo = new JLabel("Exámenes");
        titulo.setFont(new Font("Comic Sans MS", 1, 34));
        bordeNorth.add(titulo);
        titulo.setForeground(Color.MAGENTA);
        //bordeNorth.setBackground(Color.green);
        bordeNorth.setPreferredSize(new Dimension(400, 50));
        //PNL SOUTH   
        bordeSouth = new JPanel();
        //bordeSouth.setBackground(Color.black);
        bordeSouth.setPreferredSize(new Dimension(400, 55));
        //SET ACTION LISTENER PARA BOTON Y TXT PASSWORD 
        listenerLogIn = new ActionListener() {
                            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println("CLICKED BTNLOGIN");
                TipoUsuario seleccionado = (TipoUsuario)cboxTipoUsuario.getSelectedItem();
                //System.out.println("Tipo usuario: "+seleccionado);
                switch (seleccionado){
                    case Administrador:
                        if(txtPassword.getText().equals("1234") && txtUser.getText().equals("admin")){
                            System.out.println("LOGRO ENTRAR AL IF");
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    LoginFrame.this.setVisible(false);
                                    MainFrame ventana = new MainFrame("Control Escolar",
                                    controlEscolar);
                                }
                            });
                        }else{
                            JOptionPane.showMessageDialog(LoginFrame.this,
                                    "Usuario o contraseña incorrectos", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    case Profesor:
                        Boolean correctoProfesor = false;
                        for(int i=0; i<controlEscolar.getListaProfesores().size(); i++){
                            if(controlEscolar.getListaProfesores().get(i).getNoControl().equals(txtUser.getText())){
                               if(controlEscolar.getListaProfesores().get(i).getPassword().equals(txtPassword.getText())){
                                   Profesor referencia = controlEscolar.getListaProfesores().get(i);
                                   correctoProfesor = true; 
                                   //System.out.println("SI ENTRO A PROFESOR");
                                   SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    LoginFrame.this.setVisible(false);
                                    ProfesorFrame ventanaProfesor = new ProfesorFrame("Examenes - Profesores",
                                            referencia,
                                            controlEscolar, new ControlExamenes());
                                }
                            });
                                }
                            }
                        }
                        if(!correctoProfesor){
                            JOptionPane.showMessageDialog(LoginFrame.this,
                            "Usuario o contraseña incorrectos", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    case Alumno:
                        Boolean correctoAlumno = false;
                        for(int i=0; i<controlEscolar.getListaAlumnos().size(); i++){
                            System.out.println("i="+i);
                            if(controlEscolar.getListaAlumnos().get(i).getNoControl().equals(txtUser.getText())){
                                System.out.println(controlEscolar.getListaAlumnos().get(i).getNoControl());
                                System.out.println(controlEscolar.getListaAlumnos().get(i).getPassword());
                               if(controlEscolar.getListaAlumnos().get(i).getPassword().equals(txtPassword.getText())){
                                   Alumno referencia = controlEscolar.getListaAlumnos().get(i);
                                   correctoAlumno = true; 
                                   //System.out.println("SI ENTRO A ALUMNO");
                                   SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    LoginFrame.this.setVisible(false);
                                    AlumnoFrame ventanaAlumno= new AlumnoFrame("Examenes - Alumno",
                                            referencia,
                                            controlEscolar, new ControlExamenes());
                                }
                            });
                                }
                            }
                        }
                        if(!correctoAlumno){
                            JOptionPane.showMessageDialog(LoginFrame.this,
                            "Usuario o contraseña incorrectos", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                }
            }
        };
        
        
        //Label y texfield de nombre
        lblUser = new JLabel("Nombre de usuario:");
        lblUser.setAlignmentX(LEFT_ALIGNMENT);
        txtUser = new JTextField(10);
        //Label y texfield de password
        lblpassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField();
        txtPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                    listenerLogIn.actionPerformed(new ActionEvent(ke.getSource(), WIDTH, "enter pressed"));
                }
            }
            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        
        //El mágico boton de aceptar
        btnLogIn = new JButton("Log In");
        //COMBO BOX MAGICO PARA TIPO USUARIO y SU LABEL
        lblTipoUsuario = new JLabel("Tipo de usuario:");
        cboxTipoUsuario = new JComboBox(TipoUsuario.values());
        //ADDS A PNLOGIN
        pnlLogIn.add(btnLogIn);
        pnlLogIn.add(Box.createHorizontalStrut(2));
        pnlLogIn.add(lblTipoUsuario);
        pnlLogIn.add(cboxTipoUsuario);
        //ADDS A PANELSITO
        pnlUsuario.add(lblUser);
        panelsito.add(pnlUsuario);
        panelsito.add(txtUser);
        panelsito.add(Box.createVerticalStrut(15)); 
        pnlPassword.add(lblpassword);
        panelsito.add(pnlPassword);
        panelsito.add(txtPassword);
        panelsito.add(Box.createVerticalStrut(15));
        panelsito.add(pnlLogIn);
        
        //SET ACTION LISTENER COMBO BOX
        cboxTipoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    TipoUsuario seleccionado = (TipoUsuario)cboxTipoUsuario.getSelectedItem();
                    switch (seleccionado){
                    case Administrador:
                        lblUser.setText("Nombre de usuario:");
                        LoginFrame.this.repaint();
                        break;
                    case Profesor:
                        lblUser.setText("Numero de control profesor:");
                        LoginFrame.this.repaint();
                        break;
                    case Alumno:
                        lblUser.setText("Numero de control alumno:");
                        LoginFrame.this.repaint();
                        break;
                }
                    
            }
        });
        //SET ACTION LISTENER BTN LOGIN
        btnLogIn.addActionListener(listenerLogIn);
        
        //ADDS A SUPER
        
        super.add(bordeEast, BorderLayout.EAST);
        super.add(bordeWest, BorderLayout.WEST);
        super.add(bordeNorth, BorderLayout.NORTH);
        super.add(bordeSouth, BorderLayout.SOUTH);
        super.add(panelsito, BorderLayout.CENTER);
        
        
        
        
        super.setVisible(true);
        
    }
}
