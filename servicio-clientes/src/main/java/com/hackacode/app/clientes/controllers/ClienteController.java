package com.hackacode.app.clientes.controllers;

import com.google.common.collect.HashBasedTable;
import com.hackacode.app.clientes.models.dao.ClienteDao;
import com.hackacode.commons.entity.models.entity.dto.ClienteDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    private InterfazServicio<ClienteDTO> clienteServicio;

    @GetMapping("/")
    public List<ClienteDTO> listar() {
        return clienteServicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ClienteDTO cliente = clienteServicio.buscarPorId(id);
            if (cliente == null) {
                response.put("mensaje", "El cliente no se encuentra en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("cliente", cliente);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            response.put("causa", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@RequestBody ClienteDTO clienteDto) {
        try {
            return new ResponseEntity(clienteServicio.guardar(clienteDto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Existen elementos unicos que no pueden duplicarse");
            response.put("causa", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") Long id) {
        clienteServicio.eliminarPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> editar(@RequestBody ClienteDTO clienteDto, @PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            ClienteDTO clienteBD_DTO = clienteServicio.buscarPorId(id);

            if (clienteBD_DTO == null) {
                response.put("mensaje", "El cliente no existe en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            clienteBD_DTO = clienteDto;
            clienteBD_DTO.setId(id);
            return new ResponseEntity<>(clienteServicio.guardar(clienteBD_DTO), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {

            response.put("mensaje", "Existen elementos unicos que no pueden duplicarse");
            response.put("causa", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

}
