/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author davif
 */
public class con_DB {
    
    Connection conectar = null;
    
    String user = "root";
    String pass = "admin123";
    String db = "catalogo";
    String ip = "localhost";
    String port = "3306";
    
    String chain = "jdbc:mysql://"+ ip + ":"+port+"/" + db;
    
    
    public Connection establishConnection(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conectar = DriverManager.getConnection(chain, user, pass);
            System.err.println("Conexion establecida..");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, error: " + e.toString());
        }
        
        return conectar;
    }
}
