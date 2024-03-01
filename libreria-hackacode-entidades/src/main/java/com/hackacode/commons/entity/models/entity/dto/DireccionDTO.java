/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackacode.commons.entity.models.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 54385
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDTO {

    private Long id;
    private String calle;
    private String ciudad;
    private String codigoPostal;
}
