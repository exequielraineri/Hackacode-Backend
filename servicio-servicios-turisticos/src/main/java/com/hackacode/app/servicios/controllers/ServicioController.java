package com.hackacode.app.servicios.controllers;

import com.hackacode.app.servicios.models.service.ServicioTuristico;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.aspectj.bridge.MessageUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ServicioController {

    @Autowired
    private ServicioTuristico servicioTuristico;

    private static org.slf4j.Logger log = LoggerFactory.getLogger(ServicioController.class);

    @Value("${ruta.imagenes}")
    private String RUTA_IMAGENES;

    @GetMapping("/")
    public List<ServicioDTO> listar() {
        return servicioTuristico.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Long id) {
        ServicioDTO servicioBD_DTO = servicioTuristico.buscarPorId(id);
        Map<String, Object> response = new HashMap<>();
        if (servicioBD_DTO == null) {
            response.put("mesaje", "El servicio no se encuentra en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("servicio", servicioBD_DTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<?> guardar(@RequestPart(name = "servicio", required = true) ServicioDTO servicioDTO,
            @RequestPart(name = "imagen", required = false) MultipartFile imagen) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (!imagen.isEmpty()) {

                Path directorio = Paths.get(RUTA_IMAGENES);

                if (Files.notExists(directorio) || !Files.isDirectory(directorio)) {
                    Files.createDirectories(directorio);
                }

                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = directorio.resolve(imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                servicioDTO.setImagen(imagen.getOriginalFilename());
            }

            return new ResponseEntity<>(servicioTuristico.guardar(servicioDTO), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            response.put("mensaje", "Existen elementos unicos que no pueden duplicarse");
            response.put("error", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (IOException ex) {
            response.put("mensaje", ex.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception {
        try {

            ServicioDTO servicioBD_DTO = servicioTuristico.buscarPorId(id);
            Map<String, Object> response = new HashMap<>();
            if (servicioBD_DTO == null) {
                response.put("mensaje", "El servicio no se encuentra en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (!servicioBD_DTO.getPack().isEmpty()) {
                response.put("mensaje", String.format("El servicio se encuentra activo en %d paquete/s", servicioBD_DTO.getPack().size()));
                response.put("paquetes",
                        servicioBD_DTO.getPack().stream()
                                .map(pack -> pack.getId())
                                .collect(Collectors.toList()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            servicioTuristico.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> editar(
            @PathVariable Long id,
            @RequestPart(name = "servicio", required = true) ServicioDTO servicioDTO,
            @RequestPart(name = "imagen", required = false) MultipartFile imagen) {
        Map<String, Object> response = new HashMap<>();
        try {
            ServicioDTO servicioBD_DTO = servicioTuristico.buscarPorId(id);

            if (servicioBD_DTO == null) {
                response.put("mensaje", "El servicio no se encuentra en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            servicioBD_DTO.setId(id);
            servicioBD_DTO.setCosto(servicioDTO.getCosto());
            servicioBD_DTO.setDescripcion(servicioDTO.getDescripcion());
            servicioBD_DTO.setDestino(servicioDTO.getDestino());
            servicioBD_DTO.setFechaRegistro(servicioDTO.getFechaRegistro());
            servicioBD_DTO.setFechaServicio(servicioDTO.getFechaServicio());
            servicioBD_DTO.setNombre(servicioDTO.getNombre());
            servicioBD_DTO.setPack(servicioDTO.getPack());

            Path directorio = Paths.get(RUTA_IMAGENES);

            byte[] bytes = imagen.getBytes();
            Path rutaAbsoluta = directorio.resolve(imagen.getOriginalFilename());
            Files.write(rutaAbsoluta, bytes);
            servicioBD_DTO.setImagen(imagen.getOriginalFilename());

            return new ResponseEntity<>(servicioTuristico.guardar(servicioBD_DTO), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            response.put("mensaje", "Existen elementos unicos que no pueden duplicarse");
            response.put("error", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response.put("mensaje", "Existen elementos unicos que no pueden duplicarse");
            response.put("error", e.getCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
