/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Modelo;

import Vista.Agregar;
import Vista.Eliminar;
import Vista.Listar;
import Vista.Menu;
import Vista.Modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Controlador extends JFrame implements ActionListener, MenuListener {

    Menu menu;
    Agregar agregar;
    Eliminar eliminar;
    Modificar modificar;
    Listar listar;
    Modelo m;

    public Controlador(Menu menu, Agregar agregar, Eliminar eliminar, Modificar modificar, Listar listar, Modelo m) {
        this.menu = menu;
        this.agregar = agregar;
        this.eliminar = eliminar;
        this.modificar = modificar;
        this.listar = listar;
        this.m = m;

        

        menu.getjAgregar().addMenuListener(this);
        menu.getjListar().addMenuListener(this);
        menu.getjEliminar().addMenuListener(this);
        menu.getjModificar().addMenuListener(this);

        agregar.getjAgregar().addMenuListener(this);
        agregar.getjListar().addMenuListener(this);
        agregar.getjEliminar().addMenuListener(this);
        agregar.getjModificar().addMenuListener(this);
  
        agregar.getBtnAgregar().addActionListener(this);

        eliminar.getjAgregar().addMenuListener(this);
        eliminar.getjListar().addMenuListener(this);
        eliminar.getjEliminar().addMenuListener(this);
        eliminar.getjModificar().addMenuListener(this);
        eliminar.getBtnbuscar().addActionListener(this);
        eliminar.getBtnEliminar2().addActionListener(this);

        modificar.getjAgregar().addMenuListener(this);
        modificar.getjListar().addMenuListener(this);
        modificar.getjEliminar().addMenuListener(this);
        modificar.getjModificar().addMenuListener(this);
        modificar.getBtnBuscar().addActionListener(this);
        modificar.getBtnModificar().addActionListener(this);
        modificar.getBtnLimpiar().addActionListener(this);

        listar.getjAgregar().addMenuListener(this);
        listar.getjListar().addMenuListener(this);
        listar.getjEliminar().addMenuListener(this);
        listar.getjModificar().addMenuListener(this);
        
        listar.getPopEliminar().addActionListener(this);
    }

    public void ingresar() {
        menu.setTitle("Menú | VideoBuster");
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        menu.setResizable(false);

        agregar.setTitle("Agregar Peliculas | VideoBuster");
        agregar.setLocationRelativeTo(null);
        agregar.setVisible(false);
        agregar.setResizable(false);

        eliminar.setTitle("Eliminar Peliculas | VideoBuster");
        eliminar.setLocationRelativeTo(null);
        eliminar.setVisible(false);
        eliminar.setResizable(false);

        modificar.setTitle("Modificar Peliculas | VideoBuster");
        modificar.setLocationRelativeTo(null);
        modificar.setVisible(false);
        modificar.setResizable(false);
        
      
        

        listar.setTitle("Listar Peliculas | VideoBuster");
        listar.setLocationRelativeTo(null);
        listar.setVisible(false);
        listar.setResizable(false);
        actualizarMostrar();

    }

    public void buscarChocolate() {

       eliminar.getTblBuscar().setModel(m.buscarPelicula(Integer.parseInt(eliminar.getTxtCodigo().getText())));

    }

    public void eliminarChocolate() throws ClassNotFoundException, SQLException {
        
        m.Eliminar(Integer.parseInt(eliminar.getTxtCodigo().getText()));
        buscarChocolate();
    }

    public void buscar() throws ClassNotFoundException, SQLException {
        boolean buscar=m.Buscar(Integer.parseInt(modificar.getTxtcodigo().getText()), modificar.getTxtNombre(), modificar.getTxtPrecio(), modificar.getTxtCategoria(),modificar.getjSi(),modificar.getjNo());
        
            
    }

    public void actualizarMostrar() {
        try {
            listar.getTblMostrar().setModel(m.Mostrar());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int tieneazucar(){
        int a=0;
        if(modificar.getjSi().isSelected()){
            a=1;       
        }else if(modificar.getjNo().isSelected()){
        a=0;
         }else ;
        return a;
    }
    
    public void actionPerformed(ActionEvent e) {
        if(agregar.getBtnLimpiar()==e.getSource()){
            limpiarAgregar();
        }
        
        if(modificar.getBtnLimpiar()==e.getSource()){
            limpiarModificar();
        }
        
        
        if(listar.getPopEliminar()==e.getSource()){
            try {
                m.Eliminar(Integer.parseInt(listar.getTblMostrar().getModel().getValueAt(listar.getTblMostrar().getSelectedRow(), 0).toString()));
                actualizarMostrar();
            } catch (ClassNotFoundException ex) {
                
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (SQLException ex) {
                
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                
                
            }
            
        }
        
        
        
       if(modificar.getBtnModificar()==e.getSource()){
            if(validacionesModificar()){
                try {
                    m.Modificar(Integer.parseInt(modificar.getTxtcodigo().getText()),modificar.getTxtNombre().getText(),Integer.parseInt(modificar.getTxtPrecio().getText()),modificar.getTxtCategoria().getSelectedItem().toString(),tieneazucar());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
      }
        }
        
        if (modificar.getBtnBuscar() == e.getSource()) {
            if(true){
            try {
                
                buscar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

        }
       if (eliminar.getBtnEliminar2() == e.getSource()) {
           
            try {
                eliminarChocolate();
                limpiarModificar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
    
        if (eliminar.getBtnbuscar() == e.getSource()) {
            
            buscarChocolate();
            
        }

        if (agregar.getBtnAgregar() == e.getSource()) {
            if(validaciones()==true){
            try {
                m.Insertar(Integer.parseInt(agregar.getTxtCodigo().getText()),agregar.getTxtNombre().getText(),Integer.parseInt(agregar.getTxtPrecio().getText()),agregar.getTxtCategoria().getSelectedItem().toString(),tieneAzucarAgregar());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }

    }

    //public String tieneCola() {
        
      //  if (agregar.getjSi().isSelected()==true&& agregar.getjInstrumento().getSelectedItem().toString().equals("Piano")) {
      //      System.out.println("Sí");
      //      return "Sí";
            
      //  } else if (agregar.getjInstrumento().getSelectedItem().toString().equals("Piano")&&agregar.getjNo().isSelected()==true) {
      //      System.out.println("No");
      //      return "No";
            
      //  } else {
      //      System.out.println("nada");
      //      return "";
      //  }
  //  }
    
     public String tieneAzucarModificar() {
        if (modificar.getjSi().isSelected() == true) {
            return "Sí";
        } else if(modificar.getjNo().isSelected() == true){
            return "No";
        }else{
            return "";
        }
    }
     
      public int tieneAzucarAgregar() {
        if (agregar.getjSi().isSelected() == true ) {
            return 1;
        } else if(modificar.getjNo().isSelected() == true){
            return 0;
        }else{
            return 0;
        }
    }
     

    @Override
    public void menuSelected(MenuEvent e) {
        if (menu.getjAgregar() == e.getSource() || agregar.getjAgregar() == e.getSource() || listar.getjAgregar() == e.getSource() || eliminar.getjAgregar() == e.getSource() || modificar.getjAgregar() == e.getSource()) {
            agregar.setVisible(true);
            menu.setVisible(false);
            listar.setVisible(false);
            eliminar.setVisible(false);
            modificar.setVisible(false);
        }

        if (menu.getjEliminar() == e.getSource() || agregar.getjEliminar() == e.getSource() || listar.getjEliminar() == e.getSource() || eliminar.getjEliminar() == e.getSource() || modificar.getjEliminar() == e.getSource()) {
            eliminar.setVisible(true);
            menu.setVisible(false);
            agregar.setVisible(false);
            listar.setVisible(false);

            modificar.setVisible(false);
        }

        if (menu.getjListar() == e.getSource() || agregar.getjListar() == e.getSource() || listar.getjListar() == e.getSource() || eliminar.getjListar() == e.getSource() || modificar.getjListar() == e.getSource()) {
            actualizarMostrar();
            listar.setVisible(true);
            menu.setVisible(false);
            agregar.setVisible(false);

            eliminar.setVisible(false);
            modificar.setVisible(false);

        }

        if (menu.getjModificar() == e.getSource() || agregar.getjModificar() == e.getSource() || listar.getjModificar() == e.getSource() || eliminar.getjModificar() == e.getSource() || modificar.getjModificar() == e.getSource()) {
            modificar.setVisible(true);
            menu.setVisible(false);
            agregar.setVisible(false);
            listar.setVisible(false);
            eliminar.setVisible(false);

        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    public boolean validaciones() {
        boolean validador = true;
        if (Integer.parseInt(agregar.getTxtCodigo().getText())>99999) {
            JOptionPane.showMessageDialog(this, "El código debe tener 5 digitos");
            validador = false;
        }
        if (Integer.parseInt(agregar.getTxtCodigo().getText())<10000) {
            JOptionPane.showMessageDialog(this, "El código debe tener 5 digitos");
            validador = false;
        }
        if (agregar.getTxtNombre().getText().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nombre debe tener al menos 3 caracteres");
            validador = false;
        }
        if (Integer.parseInt(agregar.getTxtPrecio().getText())<1000||agregar.getTxtPrecio().getText().equals("")) {
            JOptionPane.showMessageDialog(this, "El precio de venta debe ser mayor a 1000");
            validador = false;
        }
        if (agregar.getTxtCategoria().getSelectedIndex()==0) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una Categoria");
            validador = false;
        }
        if (!agregar.getjSi().isSelected()&&!agregar.getjNo().isSelected()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar si la película es o no 4K");
            validador = false;
        }
        
   
      
        return validador;

    }
    
     public boolean validacionesModificar() {
        boolean validador = true;
      //  if (Integer.parseInt(agregar.getTxtCodigo().getText())<=999) {
       //     JOptionPane.showMessageDialog(this, "El código debe tener 3 digitos");
       //     validador = false;
      //  }
        
        if (modificar.getTxtNombre().getText().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nombre debe tener al menos 3 caracteres");
            validador = false;
        }
        if (Integer.parseInt(modificar.getTxtPrecio().getText())<1000||modificar.getTxtPrecio().getText().equals("")) {
            JOptionPane.showMessageDialog(this, "El precio de venta debe ser mayor a 1000");
            validador = false;
        }
        if (modificar.getTxtCategoria().getSelectedIndex()==0) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un tipo de Categoría");
            validador = false;
        }
        if (!modificar.getjSi().isSelected()&&!modificar.getjNo().isSelected()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar si la película es o no 4K");
            validador = false;
        }
        
   
      
        return validador;

    }
    
    public void limpiarModificar(){
        System.out.println("Se limpia todo excepto el código");
        modificar.getTxtNombre().setText("");
        modificar.getTxtPrecio().setText("0");
        modificar.getTxtCategoria().setSelectedItem(" ");
        modificar.getjSi().setSelected(false);
        modificar.getjNo().setSelected(false);
        
    }
    
     public void limpiarAgregar(){
         agregar.getTxtCodigo().setText("");
        agregar.getTxtNombre().setText("");
        agregar.getTxtPrecio().setText("0");
        agregar.getTxtCategoria().setSelectedItem(" ");
        agregar.getjSi().setSelected(false);
        agregar.getjNo().setSelected(false);
        
    }
     
    
    
    
}
