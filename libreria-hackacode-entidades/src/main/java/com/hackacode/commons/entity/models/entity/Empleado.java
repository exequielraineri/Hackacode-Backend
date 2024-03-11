package com.hackacode.commons.entity.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "empleados")
public class Empleado extends Cliente {

    @Column(nullable = false)
    private String cargo;
    private Double sueldo;

    private String password;
    public Empleado(String cargo, Double sueldo, Long id, String nombre, String apellido, String direccion,String password, String dni, Date fechaNac, String nacionalidad, Long celular, String email, Date fechaRegistro) {
        super(id, nombre, apellido, direccion, dni, fechaNac, nacionalidad, celular, email, fechaRegistro);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.password=password;
    }

    public Empleado(String cargo, Double sueldo) {
        this.cargo = cargo;
        this.sueldo = sueldo;
    }

    public Empleado() {
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

}
