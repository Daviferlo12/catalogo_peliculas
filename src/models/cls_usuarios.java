/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.toedter.calendar.JDateChooser;
import conection.con_DB;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author davif
 */
public class cls_usuarios {
    int usuarios_id;
    String nombre;
    String Correo;
    String genero;
    String contraseña;

    public int getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(int usuarios_id) {
        this.usuarios_id = usuarios_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
   
    
    
    public void save_user(JTextField name, JTextField correo, JComboBox genero, JPasswordField pwd){
        
        setNombre(name.getText());
        setCorreo(correo.getText());
        setGenero(genero.getSelectedItem().toString());
        String password = new String(pwd.getPassword());
        setContraseña(password);
        
        con_DB conection = new con_DB();
        
        String query = "insert into usuarios(nombre, Correo, genero, contraseña) values(?,?,?,?)";
        
        try {
            
            CallableStatement cs = conection.establishConnection().prepareCall(query);
            
            cs.setString(1, getNombre());
            cs.setString(2, getCorreo());
            cs.setString(3, getGenero());
            cs.setString(4, getContraseña());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se registró Correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertaron los datos correctamente, ERROR: "+ e.toString());
        }
    }
    
    
    public void save_user_from_form(JTextField name, JTextField correo, JComboBox genero, String pwd){
        
        setNombre(name.getText());
        setCorreo(correo.getText());
        setGenero(genero.getSelectedItem().toString());
        String password = new String(pwd);
        setContraseña(password);
        
        con_DB conection = new con_DB();
        
        String query = "insert into usuarios(nombre, Correo, genero, contraseña) values(?,?,?,?)";
        
        try {
            
            CallableStatement cs = conection.establishConnection().prepareCall(query);
            
            cs.setString(1, getNombre());
            cs.setString(2, getCorreo());
            cs.setString(3, getGenero());
            cs.setString(4, getContraseña());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se registró Correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertaron los datos correctamente, ERROR: "+ e.toString());
        }
    }
    
    
   
    
    public boolean acces_user(JTextField correo, JPasswordField pwd){
        con_DB conection = new con_DB();

        String password = new String(pwd.getPassword());

        String correo_correcto = null;
        String contraseña_correcto = null;

        String query = "SELECT Correo, contraseña FROM usuarios WHERE Correo = ? AND contraseña = ?";

        try (Connection conn = conection.establishConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, correo.getText());
            statement.setString(2, password);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    correo_correcto = rs.getString("Correo");
                    contraseña_correcto = rs.getString("contraseña");
                }

                return correo.getText().equals(correo_correcto) && password.equals(contraseña_correcto);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores, registra la excepción
            return false;
        }
    }
    
    
    
    public void mostrar_usuario_all(JTable tabla_reservas){
        
        con_DB conn = new con_DB();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> ordenar_tabla = new TableRowSorter<>(modelo);
        tabla_reservas.setRowSorter(ordenar_tabla);
        
        String query = "";
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Correo");
        modelo.addColumn("Genero");
        
        query = "SELECT nombre, correo, genero FROM usuarios;";
        
        tabla_reservas.setModel(modelo);
        
        String[] datos = new String[3];
        Statement st;
        
        try {
            
            st = conn.establishConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            tabla_reservas.setModel(modelo);
            System.err.println("Datos de la tabla cargados");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llamar los datos de la tabla, ERROR: "+ e.toString());
        }
    }
    
    
    
    public void seleccionar_usuario(JTable tabla_usuarios, JTextField p_nombre, JTextField p_correo, JComboBox p_genero){
        
        
        try {
            
            int fila = tabla_usuarios.getSelectedRow();
            
            if(fila >= 0){
                
                p_nombre.setText(tabla_usuarios.getValueAt(fila, 0).toString());
                p_correo.setText(tabla_usuarios.getValueAt(fila, 1).toString());
                p_genero.setSelectedItem(tabla_usuarios.getValueAt(fila, 2).toString());
                
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, ERROR: " + e.toString());
        }
        
    }
    
    
    public void modificar_usuario( JTextField p_nombre, JTextField p_correo, JComboBox p_genero){
          
        con_DB conn = new con_DB();
        
        setNombre(p_nombre.getText());
        setCorreo(p_correo.getText());
        setGenero(p_genero.getSelectedItem().toString());
        
        
        String query = "UPDATE usuarios us SET us.nombre = ?, us.Correo = ?, us.genero = ? WHERE us.nombre = ? AND us.Correo = ?;";
        
        try {
            CallableStatement cs = conn.establishConnection().prepareCall(query);
            
            cs.setString(1, getNombre());
            cs.setString(2, getCorreo());
            cs.setString(3, getGenero());
            cs.setString(4, getNombre());
            cs.setString(5, getCorreo());
            
            
            System.out.println(query);
            cs.execute();
            System.out.println("Modificado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al actualizar, ERROR: "+ e.toString());
            
        }
    }
    
    
    public void eliminar_usuario( JTextField p_nombre, JTextField p_correo){
        
        
        con_DB conn = new con_DB();
        
        String query = "DELETE FROM usuarios us WHERE us.nombre = ? AND us.Correo = ?;";
        

        try {
            CallableStatement cs = conn.establishConnection().prepareCall(query);

            cs.setString(1, getNombre());
            cs.setString(2, getCorreo());
            
            cs.execute();
            System.out.println("Eliminado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar, ERROR: "+ e.toString());
            
        }
        
    }
    
    

    
    
}
