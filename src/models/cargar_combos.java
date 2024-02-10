/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import conection.con_DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author davif
 */
public class cargar_combos {
    
    
    public void rellenar_combo(String table, String valor, JComboBox combo){
        String query = "SELECT " + valor + " FROM " + table + ";";
        
        Statement st;
        con_DB cn = new con_DB();
        Connection conection = cn.establishConnection();
        
        try {
            st = conection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
            
                combo.addItem(rs.getString(valor));
                
            }
        } catch (Exception e) {
        }
    }
    
    
      public void rellenar_combo_SinRepetir(String table, String valor, JComboBox combo){
        String query = "SELECT DISTINCT " + valor + " FROM " + table + ";";
        
        Statement st;
        con_DB cn = new con_DB();
        Connection conection = cn.establishConnection();
        
        try {
            st = conection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
            
                combo.addItem(rs.getString(valor));
                
            }
        } catch (Exception e) {
        }
    }
    
}
