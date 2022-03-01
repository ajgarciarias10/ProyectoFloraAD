package com.ieszv.progmulti.proyectofloraad.model.entity;

import retrofit2.Call;

public class Imagen {
    /**
     * Objeto Imagen con sus respectivos campos
     */
    public long id;
    public long idflora;
    public String nombre, descripcion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdflora() {
        return idflora;
    }

    public void setIdflora(long idflora) {
        this.idflora = idflora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", idflora=" + idflora +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
