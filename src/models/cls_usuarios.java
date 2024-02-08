/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import conection.con_DB;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

    
    
}
