package com.hackacode.commons.entity.models.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackacode.commons.entity.models.entity.Servicio;
import java.util.Date;
import java.util.List;

public class PackTuristicoDTO {

    private Long id;
    private List<ServicioDTO> servicios;
    private Double costoPack;
    private List<VentaDTO> ventas;
    private Date fechaRegistro;

    public PackTuristicoDTO() {
    }

    public PackTuristicoDTO(Long id, List<ServicioDTO> servicios, Double costoPack, List<VentaDTO> ventas, Date fechaRegistro) {
        this.id = id;
        this.servicios = servicios;
        this.costoPack = costoPack;
        this.ventas = ventas;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }

    public void setCostoPack(Double costoPack) {
        this.costoPack = costoPack;
    }

    @JsonIgnore
    public List<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDTO> ventas) {
        this.ventas = ventas;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Double getCostoPack() {
        double total = 0.0;
        for (ServicioDTO servicioDto : servicios) {
            total += servicioDto.getCosto();
        }
        return total;
    }

}
