package com.hackacode.app.ventas.controllers;

import com.hackacode.commons.entity.models.entity.Servicio;
import com.hackacode.commons.entity.models.entity.Venta;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import com.hackacode.commons.entity.models.entity.dto.VentaDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    @Autowired
    private InterfazServicio<VentaDTO> ventaServicio;

    @GetMapping("/")
    public List<VentaDTO> listar() {
        return ventaServicio.listar();
    }

    @GetMapping("/{id}")
    public VentaDTO obtener(@PathVariable Long id) {
        return ventaServicio.buscarPorId(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public VentaDTO crear(@RequestParam(name = "detalle") List<ServicioDTO> servicios,
            @RequestParam(name = "cliente_id") String idCliente,
            @RequestParam(name = "empleado_id") String idEmpleado) {

        ventaDTO.setFechaRegistro(new Date());
        return ventaServicio.guardar(ventaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ventaServicio.eliminarPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VentaDTO> editar(@RequestBody VentaDTO ventaDTO, @PathVariable Long id) {
        VentaDTO ventaBD_DTO = ventaServicio.buscarPorId(id);

        if (ventaBD_DTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ventaServicio.guardar(ventaBD_DTO), HttpStatus.OK);

    }

}
