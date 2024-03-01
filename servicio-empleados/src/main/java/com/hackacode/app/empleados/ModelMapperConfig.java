/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackacode.app.empleados;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 54385
 */
@Configuration
public class ModelMapperConfig {
    
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
