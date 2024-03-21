/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hackacode.app.empleados.models.service;

import com.hackacode.commons.entity.models.entity.dto.EmpleadoDTO;
import com.hackacode.commons.entity.util.InterfazServicio;

/**
 *
 * @author 54385
 */
public interface IEmpleadoServicio extends InterfazServicio<EmpleadoDTO>{
    
    public EmpleadoDTO login(String email,String password); 
}
