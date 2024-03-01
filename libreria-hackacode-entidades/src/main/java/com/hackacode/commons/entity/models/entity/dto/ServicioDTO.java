package com.hackacode.commons.entity.models.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;


public class ServicioDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String destino;
    private Date fechaServicio;
    private Double costo;
    private Date fechaRegistro;
    private List<PackTuristicoDTO> pack;
    private String imagen;
    
    public ServicioDTO(Long id, String nombre, String descripcion, String destino, Date fechaServicio, Double costo, Date fechaRegistro, List<PackTuristicoDTO> pack, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destino = destino;
        this.fechaServicio = fechaServicio;
        this.costo = costo;
        this.fechaRegistro = fechaRegistro;
        this.pack = pack;
        this.imagen=imagen;
    }

    public ServicioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @JsonIgnore
    public List<PackTuristicoDTO> getPack() {
        return pack;
    }

    public void setPack(List<PackTuristicoDTO> pack) {
        this.pack = pack;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
