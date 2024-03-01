package com.hackacode.commons.entity.models.entity.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private DireccionDTO direccion;
    private String dni;
    private Date fechaNac;
    private String nacionalidad;
    private Long celular;
    private String email;
    private Date fechaRegistro;

}
