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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
        
            // DB INSTANCE
        con_DB conn = new con_DB();
        
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
        
         
            // DB INSTANCE
        con_DB conn = new con_DB();
    
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
     
     
      public void insertar_reserva(JComboBox usuario, JComboBox contenido, JDateChooser fecha_reserva){
        
          
        con_DB conn = new con_DB();
        
        
        setUsuario_id(usuario.getSelectedIndex());
        setCatalogo_id(contenido.getSelectedIndex());
        
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(fecha_reserva.getDate());
        setFecha_reserva(fechaFormateada);*/

        String query = "insert into reservas(id_usuario, id_contenido, fecha_reserva, estado) values(?,?,CURDATE(), 1);";
        
        try {
            
            CallableStatement cs = conn.establishConnection().prepareCall(query);
            
            cs.setInt(1, getUsuario_id()+ 1);
            cs.setInt(2, getCatalogo_id() + 1);
            //cs.setString(3, getFecha_reserva());
            
            System.out.println(query);
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se guardo Correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertaron los datos correctamente, ERROR: "+ e.toString());
        }
    }
      
      
    
    public void seleccionar_reserva(JTable tabla_reserva, JComboBox p_usuario, JComboBox p_contenido, JDateChooser p_fecha_reserva){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            
            int fila = tabla_reserva.getSelectedRow();
            
            if(fila >= 0){
                
                p_usuario.setSelectedItem(tabla_reserva.getValueAt(fila, 0).toString());
                p_contenido.setSelectedItem(tabla_reserva.getValueAt(fila, 1).toString());
                
                String fecha_reserva = (String) tabla_reserva.getValueAt(fila, 2);
                Date fecha_date = sdf.parse(fecha_reserva);       
                p_fecha_reserva.setDate(fecha_date);
                
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, ERROR: " + e.toString());
        }
        
    }
    
    
    public void modificar_reserva( JComboBox p_usuario, JComboBox p_contenido, JDateChooser p_fecha_reserva){
        
        
        con_DB conn = new con_DB();
          
        setUsuario_id(p_usuario.getSelectedIndex());
        setCatalogo_id(p_contenido.getSelectedIndex());
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(p_fecha_reserva.getDate());
        setFecha_reserva(fechaFormateada);*/
        
        
        String query = "UPDATE reservas re SET re.id_usuario = ?, re.id_contenido = ?, re.fecha_reserva = CURDATE() WHERE  re.id_usuario = ? AND re.id_contenido = ?;";
        
        try {
            CallableStatement cs = conn.establishConnection().prepareCall(query);
            
            cs.setInt(1, getUsuario_id() + 1);
            cs.setInt(2, getCatalogo_id() + 1);
            //cs.setString(3, getFecha_reserva());
            cs.setInt(3, getUsuario_id() + 1);
            cs.setInt(4, getCatalogo_id() + 1);
            
            System.out.println(query);
            cs.execute();
            System.out.println("Modificado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al actualizar, ERROR: "+ e.toString());
            
        }
    }
    
    
    public void eliminar_reserva( JComboBox p_usuario, JComboBox p_contenido){
        
        
        con_DB conn = new con_DB();
        
        String query = "DELETE FROM reservas re WHERE re.id_usuario = ? AND re.id_contenido = ?;";
        

        try {
            CallableStatement cs = conn.establishConnection().prepareCall(query);

            cs.setInt(1, getUsuario_id() + 1);
            cs.setInt(2, getCatalogo_id() + 1);
            
            
            cs.execute();
            System.out.println("Eliminado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar, ERROR: "+ e.toString());
            
        }
        
    }
    
    
}
