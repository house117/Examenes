/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Login.LoginFrame;
import controller.GestionadorArchivoControlEscolar;
import guiAdministrador.MainFrame;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 *
 * @author House
 */
public class Main {
    public static void main(String[] args) {
        //pichon();
        grafico();
    }
    public static void grafico(){
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                File controlEscolar = new File("control.ce");
                
                try {
                    
                    /*MainFrame ventana = new MainFrame("Control Escolar", 
                            GestionadorArchivoControlEscolar.abrirArchivo(controlEscolar));*/
                    LoginFrame login = new LoginFrame("Acceso", 
                            GestionadorArchivoControlEscolar.abrirArchivo(controlEscolar));
                    
                    
                } catch (IOException | ClassNotFoundException ex) {
                    MainFrame ventana = new MainFrame("Control Escolar");
                }
                
                
            }
        });
    }
    public static void pichon(){
        
    /*    ControlEscolar control = new ControlEscolar();
        Scanner principalito = new Scanner(System.in);
        Integer opcion;
        do{
            System.out.println("Menú");
            System.out.println("1.-Agregar alumno");
            System.out.println("2.-Menu modifcar alumno...");
            System.out.println("3.-Eliminar alumno");
            System.out.println("4.-Menú materias");
            System.out.println("5.-Mostrar Lista de Alumnos");
            System.out.println("6.-Mostrar Alumno");
            System.out.println("Ingresa tu seleccion: ");
            opcion = principalito.nextInt();
            switch(opcion){
                case 1:
                    Scanner sc = new Scanner(System.in);
                    /*private Integer noControl; 
                    private String nombre;
                         private String aPaterno;
                         private String aMaterno;
                         private Fecha fechaNac;
                            private String Carrera;
                            private Double promedio;
                    
                    try {
                        System.out.println("Ingresa numero de control: ");
                        String noControl  = sc.nextLine();
                        System.out.println("Ingresa nombre: ");
                        String nombre = sc.nextLine();
                        System.out.println("Ingresa apellido paterno: ");
                        String aPaterno = sc.nextLine();
                        System.out.println("Ingresa apellido materno: ");
                        String aMaterno = sc.nextLine();
                        System.out.println("Ingresa dia, mes, y año de la fecha de nacimiento");
                        Fecha fechaNac = new Fecha(sc.nextInt(), sc.nextInt(), sc.nextInt());
                        sc.nextLine();
                        System.out.println("Ingresa el nombre de la carrera");
                        String Carrera = sc.nextLine();
                        System.out.println("El promedio se saca con las calif materias pero ingresalo: ");
                        Double promedio = sc.nextDouble();
                        Alumno aIngresar = new Alumno(noControl, nombre, aPaterno, aMaterno, fechaNac, Carrera, promedio);
                        control.addAlumno(aIngresar);
                    } catch (FechaInvalidaException ex) {
                    } catch (CalificacionInvalidaException ex) {
                     Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NumeroControlRepetidoException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
                    break;
                case 2:
                    Integer seleccion;
                    do{
                        String noControl;
                        System.out.println("Menú modificar alumno");
                        System.out.println("1.-Modificar nombre");
                        System.out.println("2.-Modificar apellido paterno");
                        System.out.println("3.-Modificar apellido materno");
                        System.out.println("4.-Modifcar fechaNacimiento");
                        System.out.println("5.-Modificar Carrera");
                        System.out.println("6.-Regresar menu anterior");
                        System.out.println("Ingresa tu seleccion");
                        seleccion = principalito.nextInt();
                        System.out.println("Ingresa numero de control de alumno "
                                            + "a modificar: ");
                        principalito.nextLine();
                        noControl = principalito.nextLine();
                        
                        switch(seleccion){
                            case 1:
                                System.out.println("Ingresa el nuevo nombre");
                                control.modificarNombre(noControl, principalito.nextLine());
                                
                                break;
                            case 2:
                                System.out.println("Ingresa el nuevo apellido paterno");
                                control.modificarApat(noControl, principalito.nextLine());
                                break;
                            case 3:
                                System.out.println("Ingresa el nuevo apellido materno");
                                control.modificarAMat(noControl, principalito.nextLine());
                                break;
                            case 4:
                                System.out.println("Ingresa el dia mes y año para la nueva fecha de nacimiento");
                        
                                try {
                                    control.modificarFechaNac(noControl, new Fecha(principalito.nextInt(),
                                          principalito.nextInt(), principalito.nextInt()));
                                } catch (FechaInvalidaException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        
                                break;
                            case 5:
                                System.out.println("Ingresa el nuevo apellido materno");
                                control.modificarCarrera(noControl, principalito.nextLine());
                                break;
                            case 6: 
                                break;
                            default:
                                System.out.println("seleccion incorrecta");
                                break;
                        }
                    }while(seleccion != 6);
                    
                    break;
                case 3:
                    System.out.println("Ingresa el numero de control del alumno a eliminar: ");
                    control.eliminarAlumno(principalito.nextLine());
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Ingresa numero de control: ");
                    principalito.nextLine();
                    String noControl  = principalito.nextLine();
                    control.mostrarAlumno(noControl);
                    break;
                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        }while(opcion != 7);*/
    }
}
