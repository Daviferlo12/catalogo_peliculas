/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import conection.con_DB;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author davif
 */
public class cls_reservas {
    int reserva_id;
    int usuario_id;
    int catalogo_id;
    String fecha_reserva;
    String fecha_inicio;
    String fecha_fin;
    int estado;
    
    // DB INSTANCE
    con_DB conn = new con_DB();
    

    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getCatalogo_id() {
        return catalogo_id;
    }

    public void setCatalogo_id(int catalogo_id) {
        this.catalogo_id = catalogo_id;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    public void mostrar_revervas_activas(JTable tabla_reservas){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> ordenar_tabla = new TableRowSorter<>(modelo);
        tabla_reservas.setRowSorter(ordenar_tabla);
        
        String query = "";
        
        modelo.addColumn("Usuario");
        modelo.addColumn("Contenido");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");
        
        query = "SELECT us.nombre, ca.titulo, re.fecha_reserva, re.estado FROM reservas re "
                + "INNER JOIN usuarios us ON re.id_usuario = us.usuario_id "
                + "INNER JOIN catalogo ca ON re.id_contenido = ca.catalogo_id WHERE re.estado = 1;";
        
        tabla_reservas.setModel(modelo);
        
        String[] datos = new String[4];
        Statement st;
        
        try {
            
            st = conn.establishConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);
            }
            
            tabla_reservas.setModel(modelo);
            System.err.println("Datos de la tabla cargados");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llamar los datos de la tabla, ERROR: "+ e.toString());
        }
    }
    
     public void mostrar_revervas_all(JTable tabla_reservas){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> ordenar_tabla = new TableRowSorter<>(modelo);
        tabla_reservas.setRowSorter(ordenar_tabla);
        
        String query = "";
        
        modelo.addColumn("Usuario");
        modelo.addColumn("Contenido");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");
        
        query = "SELECT us.nombre, ca.titulo, re.fecha_reserva, re.estado FROM reservas re "
                + "INNER JOIN usuarios us ON re.id_usuario = us.usuario_id "
                + "INNER JOIN catalogo ca ON re.id_contenido = ca.catalogo_id;";
        
        tabla_reservas.setModel(modelo);
        
        String[] datos = new String[4];
        Statement st;
        
        try {
            
            st = conn.establishConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);
            }
            
            tabla_reservas.setModel(modelo);
            System.err.println("Datos de la tabla cargados");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llamar los datos de la tabla, ERROR: "+ e.toString());
        }
    }
    
}
