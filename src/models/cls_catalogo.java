/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

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
    
    
}
