/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackacode.app.ventas.models.dao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 54385
 */
@FeignClient(name = "servicio-clientes")
public interface ClienteServicioFeign {

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Long id);
    
    
}
