/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hackacode.app.ventas.models.service;

import com.hackacode.commons.entity.models.entity.dto.VentaDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.List;

/**
 *
 * @author exera
 */
public interface IVentaService extends InterfazServicio<VentaDTO>{
    
    
    List<VentaDTO> ventasPorIdEmpleado(Long idEmpleado);
    
}
