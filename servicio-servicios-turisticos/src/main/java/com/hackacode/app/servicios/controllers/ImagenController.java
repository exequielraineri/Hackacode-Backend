/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackacode.app.servicios.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 54385
 */
@RestController
@RequestMapping("/imagen")
public class ImagenController {

    @Value("${ruta.imagenes}")
    private String RUTA_IMAGENES;

    @GetMapping("/{nombreImagen:.+}")
    public ResponseEntity<?> mostrarImagen(@PathVariable String nombreImagen) {
        Map<String, Object> response = new HashMap<>();
        try {
            Path rutaAbsoluta = Paths.get(RUTA_IMAGENES).resolve(nombreImagen);
            Resource recurso = new UrlResource(rutaAbsoluta.toUri());

            if (recurso.exists() && recurso.isReadable()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.valueOf("image/png"))
                        .body(recurso);
            } else {
                response.put("mensaje", "El recurso no se encuentra");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            // Manejar la excepción según tus necesidades
            response.put("mensaje", e.getMessage());
            response.put("error", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
