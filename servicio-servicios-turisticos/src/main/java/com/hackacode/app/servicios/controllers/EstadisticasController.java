/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackacode.app.servicios.controllers;

import com.hackacode.app.servicios.models.service.ServicioTuristico;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private ServicioTuristico servicioTuristico;

    @GetMapping("/servicios-mas-vendidos")
    public List<?> serviciosMasVendidos() {
        return servicioTuristico.serviciosMasVendidos();
    }

}
