package com.hackacode.app.empleados.controllers;

import com.hackacode.commons.entity.models.entity.dto.EmpleadoDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpleadoController {

    @Autowired
    private InterfazServicio<EmpleadoDTO> empleadoServicio;
    @GetMapping("/")
    public List<EmpleadoDTO> listar() {
        return empleadoServicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Map<String, Object> response = new HashMap();
        EmpleadoDTO empleadoBD_DTO = empleadoServicio.buscarPorId(id);
        if (empleadoBD_DTO == null) {
            response.put("mensaje", "El empleado no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empleadoBD_DTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody EmpleadoDTO empleadoDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            return new ResponseEntity<>(empleadoServicio.guardar(empleadoDTO), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            response.put("mensaje", "Existen elementos unicos que no pueden duplicarse");
            response.put("error", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        EmpleadoDTO empleadoDTO = empleadoServicio.buscarPorId(id);
        Map<String, Object> response = new HashMap();
        if (empleadoDTO == null) {
            response.put("mensaje", "El empleado no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        empleadoServicio.eliminarPorId(id);
        response.put("mensaje", "Empleado eliminado exitosamente");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody EmpleadoDTO empleadoDTO, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            EmpleadoDTO empleadoBD_DTO = empleadoServicio.buscarPorId(id);
            if (empleadoBD_DTO == null) {
                response.put("mensaje", "El empleado no existe en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            empleadoBD_DTO.setId(id);
            empleadoBD_DTO.setApellido(empleadoDTO.getApellido());
            empleadoBD_DTO.setCelular(empleadoDTO.getCelular());
            empleadoBD_DTO.setDireccion(empleadoDTO.getDireccion());
            empleadoBD_DTO.setDni(empleadoDTO.getDni());
            empleadoBD_DTO.setEmail(empleadoDTO.getEmail());
            empleadoBD_DTO.setFechaNac(empleadoDTO.getFechaNac());
            empleadoBD_DTO.setFechaRegistro(empleadoDTO.getFechaRegistro());
            empleadoBD_DTO.setNacionalidad(empleadoDTO.getNacionalidad());
            empleadoBD_DTO.setNombre(empleadoDTO.getNombre());
            empleadoBD_DTO.setCargo(empleadoDTO.getCargo());
            empleadoBD_DTO.setSueldo(empleadoDTO.getSueldo());
            empleadoBD_DTO.setPassword(empleadoDTO.getPassword());
            return new ResponseEntity<>(empleadoServicio.guardar(empleadoBD_DTO), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            response.put("mensaje", "Existen elementos unicos que no pueden duplicarse");
            response.put("error", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

}
