package com.hackacode.commons.entity.models.entity.dto;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
>>>>>>> 1bdf1cf2e09ca2136e98a62efb158ad2fe3a3417
import java.util.List;


public class EmpleadoDTO extends ClienteDTO {

    private String cargo;
    private Double sueldo;
    private String password;
    @JsonIgnore
    private List<VentaDTO> ventas;

    public EmpleadoDTO(String cargo, Double sueldo, List<VentaDTO> ventas,String password) {
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.ventas = ventas;
        this.password=password;
    }

    public EmpleadoDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    @JsonIgnore
    public List<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDTO> ventas) {
        this.ventas = ventas;
    }

    

}
