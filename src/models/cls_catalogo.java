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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author davif
 */
public class cls_catalogo {
    int catalogo_id;
    String titulo;
    String tipo_contenido;
    int genero_id;
    int director_id;
    int anio_lanzamiento;
    String descripcion;
    int duracion_episodio;
    int temporadas;
    int duracion;
    int disponibilidad;

    public int getCatalogo_id() {
        return catalogo_id;
    }

    public void setCatalogo_id(int catalogo_id) {
        this.catalogo_id = catalogo_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo_contenido() {
        return tipo_contenido;
    }

    public void setTipo_contenido(String tipo_contenido) {
        this.tipo_contenido = tipo_contenido;
    }

    public int getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public int getAnio_lanzamiento() {
        return anio_lanzamiento;
    }

    public void setAnio_lanzamiento(int anio_lanzamiento) {
        this.anio_lanzamiento = anio_lanzamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion_episodio() {
        return duracion_episodio;
    }

    public void setDuracion_episodio(int duracion_episodio) {
        this.duracion_episodio = duracion_episodio;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
    public void mostrar_contenido(JTable tabla_reservas){
        
        con_DB conn = new con_DB();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> ordenar_tabla = new TableRowSorter<>(modelo);
        tabla_reservas.setRowSorter(ordenar_tabla);
        
        String query = "";
        
        modelo.addColumn("Titulo");
        modelo.addColumn("Tipo");
        modelo.addColumn("Genero");
        modelo.addColumn("Director");
        modelo.addColumn("Año Lanzamiento");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Duracion Ep");
        modelo.addColumn("Temporadas");
        modelo.addColumn("Duracion");
        modelo.addColumn("Disponible");
        
        query = "SELECT ca.titulo, ca.tipo_contenido, ge.nombre_genero, di.nombre_director, ca.anio_lanzamiento, ca.descripcion, ca.duracion_episodio, ca.temporadas, ca.duracion, ca.Disponibilidad  "
                + "FROM catalogo ca INNER JOIN generos ge ON ca.id_genero = ge.genero_id "
                + "INNER JOIN directores di ON ca.id_director = di.director_id;";
        
        tabla_reservas.setModel(modelo);
        
        String[] datos = new String[10];
        Statement st;
        
        try {
            
            st = conn.establishConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                
                modelo.addRow(datos);
            }
            
            tabla_reservas.setModel(modelo);
            System.err.println("Datos de la tabla cargados");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llamar los datos de la tabla, ERROR: "+ e.toString());
        }
    }
    
    
    
    
    public void insertar_catalogo(JTextField titulo, JComboBox tipo, JComboBox genero, 
                                JComboBox director, JTextField anio_l, JTextField temporadas,
                                JTextArea descripcion, JTextField duracion_por_ep, JTextField duracion, JComboBox disponible ){
        
        
        con_DB conn = new con_DB();
        
        setTitulo(titulo.getText());
        setTipo_contenido(tipo.getSelectedItem().toString());
        setGenero_id(genero.getSelectedIndex());
        setDirector_id(director.getSelectedIndex());
        setAnio_lanzamiento(Integer.parseInt(anio_l.getText()));
        setDescripcion(descripcion.getText());
        setDuracion_episodio(Integer.parseInt(duracion_por_ep.getText()));
        setTemporadas(Integer.parseInt(temporadas.getText()));
        setDuracion(Integer.parseInt(duracion.getText()));
        setDisponibilidad(disponible.getSelectedIndex());
        

        String query = "INSER INTO catalofo(titulo, tipo_contenido, id_genero, id_director, anio_lanzamiento, descripcion, duracion_episodio, temporadas, duracion, Disponibilidad) values(?,?,?,?,?,?,?,?,?,?);";
        
        try {
            
            CallableStatement cs = conn.establishConnection().prepareCall(query);
            
            cs.setString(1, getTitulo());
            cs.setString(2, getTipo_contenido());
            cs.setInt(3, getGenero_id() +1);
            cs.setInt(4, getDirector_id() +1);       
            cs.setInt(5, getAnio_lanzamiento());
            cs.setString(6, getDescripcion());
            cs.setInt(7, getDuracion_episodio());
            cs.setInt(8, getTemporadas());
            cs.setInt(9, getDuracion());
            cs.setInt(10, getDisponibilidad());
            
            System.out.println(query);
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se guardo Correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertaron los datos correctamente, ERROR: "+ e.toString());
        }
    }
      
      
    
    public void seleccionar_contenido(JTable tabla_catalogo, JTextField titulo, JComboBox tipo, JComboBox genero, 
                                JComboBox director, JTextField anio_l, JTextField temporadas,
                                JTextArea descripcion, JTextField duracion_por_ep, JTextField duracion, JComboBox disponible){
        
        
        con_DB conn = new con_DB();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            
            int fila = tabla_catalogo.getSelectedRow();
            
            if(fila >= 0){
                
                titulo.setText(tabla_catalogo.getValueAt(fila, 0).toString());
                tipo.setSelectedItem(tabla_catalogo.getValueAt(fila, 1).toString());                
                genero.setSelectedItem(tabla_catalogo.getValueAt(fila, 2).toString());
                director.setSelectedItem(tabla_catalogo.getValueAt(fila, 3).toString());
                anio_l.setText(tabla_catalogo.getValueAt(fila, 4).toString());          
                descripcion.setText(tabla_catalogo.getValueAt(fila, 5).toString());
                
                if(duracion_por_ep != null){
                    
                    duracion_por_ep.setText(tabla_catalogo.getValueAt(fila, 6).toString());
                    
                }
                
                if(temporadas != null){
                    
                    temporadas.setText(tabla_catalogo.getValueAt(fila, 7).toString());
                    
                }
                
                duracion.setText(tabla_catalogo.getValueAt(fila, 8).toString());
                disponible.setSelectedItem(tabla_catalogo.getValueAt(fila, 9));
                
                
                
                
                
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, ERROR: " + e.toString());
        }
        
    }
    
    
    public void modificar_reserva(JTextField titulo, JComboBox tipo, JComboBox genero, 
                                JComboBox director, JTextField anio_l, JTextField temporadas,
                                JTextArea descripcion, JTextField duracion_por_ep, JTextField duracion, JComboBox disponible){
          
        
        con_DB conn = new con_DB();
        
        setTitulo(titulo.getText());
        setTipo_contenido(tipo.getSelectedItem().toString());
        setGenero_id(genero.getSelectedIndex());
        setDirector_id(director.getSelectedIndex());
        setAnio_lanzamiento(Integer.parseInt(anio_l.getText()));
        setDescripcion(descripcion.getText());
        setDuracion_episodio(Integer.parseInt(duracion_por_ep.getText()));
        setDuracion(Integer.parseInt(duracion.getText()));
        setDisponibilidad(disponible.getSelectedIndex());
        
        String query = "UPDATE catalogo ca SET titulo = ?, tipo_contenido = = ?, id_genero = ?, id_director = ?, anio_lanzamiento = ?, descripcion = ?, duracion_episodio = ?, temporadas = ?, duracion = ?, Disponibilidad = ? WHERE  titulo = ? AND id_director = ?;";
        
        try {
            CallableStatement cs = conn.establishConnection().prepareCall(query);
            
            cs.setString(1, getTitulo());
            cs.setString(2, getTipo_contenido());
            cs.setInt(3, getGenero_id() +1);
            cs.setInt(4, getDirector_id() +1);       
            cs.setInt(5, getAnio_lanzamiento());
            cs.setString(6, getDescripcion());
            cs.setInt(7, getDuracion_episodio());
            cs.setInt(8, getDuracion());
            cs.setInt(9, getDisponibilidad());
            System.out.println(query);
            cs.execute();
            System.out.println("Modificado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al actualizar, ERROR: "+ e.toString());
            
        }
    }
    
    
    public void eliminar_reserva( JTextField titulo, JComboBox id_director){
        
        
        
        con_DB conn = new con_DB();
        
        setTitulo(titulo.getText());
        setDirector_id(id_director.getSelectedIndex() +1);
        
        String query = "DELETE FROM reservas re WHERE  titulo = ? AND id_director = ?;";
        

        try {
            CallableStatement cs = conn.establishConnection().prepareCall(query);

            cs.setString(1, getTitulo());
            cs.setInt(2, getDirector_id());
            
            
            cs.execute();
            System.out.println("Eliminado con exito");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar, ERROR: "+ e.toString());
            
        }
        
    }
    
    
}
