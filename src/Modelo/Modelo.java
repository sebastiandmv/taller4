/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Modelo extends JFrame {

    Conexion con;

    public boolean Insertar(int codigo, String nombre, int precio, String categoria ,int formato4k) throws ClassNotFoundException, SQLException {
        con = new Conexion();
        try {

            String query = " insert into pelicula (codigo, nombre, precio, categoria,formato4k) values (?,?,?,?,?)";
            PreparedStatement stmt = con.connect().prepareStatement(query);
            stmt.setInt(1, codigo);
            stmt.setString(2, nombre);
            stmt.setInt(3, precio);
            stmt.setString(4, categoria);
            stmt.setInt(5, formato4k);

            stmt.executeUpdate();
            System.out.println("Ingreso exitoso");
            con.disconnect();
            JOptionPane.showMessageDialog(this, "Ingreso exitoso");
            return true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Algo falló");
            return false;
        }
    }
    
    public boolean Consulta1() throws ClassNotFoundException, SQLException {
        con = new Conexion();
        try {

            String query = " insert into pelicula (codigo, nombre, precio, categoria,formato4k) values ("+Math.round((int) (Math.random() * 1000 + 13000))+",'Asistencia DuocUC 2017',"+Math.round((int) (Math.random() * 1000 + 1000))+",'Drama',"+Math.ceil((Math.random()))+")";
            PreparedStatement stmt = con.connect().prepareStatement(query);
            
            

            stmt.executeUpdate();
            System.out.println("Ingreso exitoso");
            con.disconnect();
            JOptionPane.showMessageDialog(this, "Ingreso exitoso");
            return true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Algo falló");
            return false;
        }
    }
    
    
     public boolean Consulta2() throws ClassNotFoundException, SQLException {
        con = new Conexion();
        try {

            String query = " insert into pelicula (codigo, nombre, precio, categoria,formato4k) values ("+Math.round((int) (Math.random() * 1000 + 13000))+",'Circo DuocUC 2017',"+Math.round((int) (Math.random() * 1000 + 1000))+",'Comedia',"+Math.ceil((Math.random()))+")";
            PreparedStatement stmt = con.connect().prepareStatement(query);
            
            

            stmt.executeUpdate();
            System.out.println("Ingreso exitoso");
            con.disconnect();
            JOptionPane.showMessageDialog(this, "Ingreso exitoso");
            return true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Algo falló");
            return false;
        }
    }

    
    
    
    

    public boolean Modificar(int codigo, String nombre, int precio, String categoria ,int formato4k) throws ClassNotFoundException, SQLException {
        con = new Conexion();
        try {

            String query = " update pelicula set  nombre=?, precio=?, categoria=?,formato4k=? where codigo=?";
            PreparedStatement stmt = con.connect().prepareStatement(query);

          
            
            stmt.setString(1, nombre);
            stmt.setInt(2, precio);
            stmt.setString(3, categoria);
            stmt.setInt(4, formato4k);
            stmt.setInt(5, codigo);
            

            stmt.executeUpdate();
            System.out.println("Modificación exitosa");
            con.disconnect();
            JOptionPane.showMessageDialog(this, "Modificación exitosa");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Algo falló");
            return false;
        }
    }

    public boolean Buscar(int codigo, javax.swing.JTextField nombre, javax.swing.JTextField precio,javax.swing.JComboBox categoria,javax.swing.JRadioButton jSi,javax.swing.JRadioButton jNo) throws ClassNotFoundException, SQLException {
        con = new Conexion();
        boolean resultado = false;

        try {

            String query = " select nombre, precio, categoria,formato4k from  pelicula where codigo=?";
            PreparedStatement stmt = con.connect().prepareStatement(query);
            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                nombre.setText(rs.getString("nombre"));
                precio.setText(rs.getString("precio"));
                categoria.setSelectedItem(rs.getString("categoria"));
                if(rs.getString("formato4k").equals("1")){
                    System.out.println(1);
                    jSi.setSelected(true);
                    jNo.setSelected(false);
                }else if(rs.getString("formato4k").equals("0")){
                    System.out.println(0);
                    jSi.setSelected(false);
                    jNo.setSelected(true);
                }else{
                     System.out.println("otro");
                        jSi.setSelected(false);
                        jNo.setSelected(false);
                        }
                
               

                System.out.println("Busqueda exitosa");

                resultado = true;
                // print the results
            }
            

            con.disconnect();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Algo falló");
            resultado = false;

        }
        return resultado;
    


}

    public void Eliminar(int codigo) throws ClassNotFoundException, SQLException {
        con = new Conexion();
        try {

            String query = " delete from pelicula where codigo=?";
            PreparedStatement stmt = con.connect().prepareStatement(query);
            stmt.setInt(1, codigo);

            stmt.executeUpdate();
            System.out.println("Registro eliminado");
            con.disconnect();
            JOptionPane.showMessageDialog(this, "Registro eliminado");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Algo falló");
        }
    }

    public DefaultTableModel Mostrar() throws ClassNotFoundException, SQLException {
        con = new Conexion();
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Código", "Nombre", "Precio", "Categoria","Formato4K"};
        //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
        //para formar la matriz de datos
        try {
            PreparedStatement pstm = con.connect().prepareStatement("SELECT count(*) as total FROM pelicula");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][5];
        try {
            //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
            PreparedStatement pstm = con.connect().prepareStatement("SELECT * FROM pelicula order by categoria");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("precio");
                data[i][3] = res.getString("categoria");
                if(res.getString("formato4k").equals("1")){
                 data[i][4] = "Sí"  ;
                }else if(res.getString("formato4k").equals("0")){
                    data[i][4] = "No"  ;
                }else{
                    data[i][4] = "";
            }
                 
                
                i++;
            }
            res.close();
            //se añade la matriz de datos en el DefaultTableModel
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    
     public DefaultTableModel MostrarCategoria(String categoria) throws ClassNotFoundException, SQLException {
        con = new Conexion();
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Código", "Nombre", "Precio", "Categoria","Formato4K"};
        //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
        //para formar la matriz de datos
        try {
            PreparedStatement pstm = con.connect().prepareStatement("SELECT count(*) as total FROM pelicula where categoria=?");
            pstm.setString(1, categoria);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][5];
        try {
            //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
            PreparedStatement pstm = con.connect().prepareStatement("SELECT * FROM pelicula where categoria=?");
            pstm.setString(1, categoria);
            ResultSet res = pstm.executeQuery();
            
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("precio");
                data[i][3] = res.getString("categoria");
                if(res.getString("formato4k").equals("1")){
                 data[i][4] = "Sí"  ;
                }else if(res.getString("formato4k").equals("0")){
                    data[i][4] = "No"  ;
                }else{
                    data[i][4] = "";
            }
                 
                
                i++;
            }
            res.close();
            //se añade la matriz de datos en el DefaultTableModel
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    
    
    
    

    public DefaultTableModel buscarPelicula(int codigo) {
        con = new Conexion();
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Código", "Nombre", "Precio", "Categoria","Formato"};
        //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
        //para formar la matriz de datos
        try {
            PreparedStatement pstm = con.connect().prepareStatement("SELECT count(*) as total FROM pelicula where codigo=?");
            pstm.setInt(1, codigo);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][5];
        try {
            //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
            PreparedStatement pstm = con.connect().prepareStatement("SELECT * FROM pelicula where codigo=?");
            pstm.setInt(1, codigo);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("precio");
                data[i][3] = res.getString("categoria");
                if(res.getString("formato4k").equals("1")){
                 data[i][4] = "Sí"  ;
                }else if(res.getString("formato4k").equals("0")){
                    data[i][4] = "No"  ;
                }else{
                    data[i][4] = "";
            }
              
                i++;
            }
            res.close();
            //se añade la matriz de datos en el DefaultTableModel
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;

    }

}
