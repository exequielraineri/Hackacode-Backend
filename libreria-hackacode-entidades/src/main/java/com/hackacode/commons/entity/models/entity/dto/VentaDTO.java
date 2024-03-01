package com.hackacode.commons.entity.models.entity.dto;

import com.hackacode.commons.entity.util.MedioPago;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class VentaDTO {

    private Long id;
    private Date fechaVenta;
    private MedioPago medioPago;
    private ClienteDTO cliente;
    private EmpleadoDTO empleado;
    private PackTuristicoDTO producto;
    private Date fechaRegistro;
    private BigDecimal importe;

    public VentaDTO(Long id, Date fechaVenta, MedioPago medioPago, ClienteDTO cliente, EmpleadoDTO empleado, PackTuristicoDTO producto, Date fechaRegistro, BigDecimal importe) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.medioPago = medioPago;
        this.cliente = cliente;
        this.empleado = empleado;
        this.producto = producto;
        this.fechaRegistro = fechaRegistro;
        this.importe = importe;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public VentaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public PackTuristicoDTO getProducto() {
        return producto;
    }

    public void setProducto(PackTuristicoDTO producto) {
        this.producto = producto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoProducto() {
        if (producto.getServicios().size() > 1) {
            return "Paquete";
        }
        return "Normal";
    }

}
