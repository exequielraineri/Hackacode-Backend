package com.hackacode.app.pack.controllers;

import com.hackacode.app.pack.models.service.IPackTuristicoServicio;
import com.hackacode.commons.entity.models.entity.PackTuristico;
import com.hackacode.commons.entity.models.entity.Servicio;
import com.hackacode.commons.entity.models.entity.dto.PackTuristicoDTO;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PackTuristicoController {

    private Logger logger = LoggerFactory.getLogger(PackTuristicoController.class);

    @Autowired
    private IPackTuristicoServicio packServicio;

    @GetMapping("/")
    public List<PackTuristicoDTO> listar() {
        return packServicio.listar();
    }

    @GetMapping("/{id}")
    public PackTuristicoDTO obtener(@PathVariable Long id) {
        return packServicio.buscarPorId(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public PackTuristicoDTO crear(@RequestBody PackTuristicoDTO packDto) {
        return packServicio.guardar(packDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        packServicio.eliminarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackTuristicoDTO> editar(@RequestBody PackTuristicoDTO packDto, @PathVariable Long id) {
        PackTuristicoDTO packBD = packServicio.buscarPorId(id);
        if (packDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        packBD.setCostoPack(packDto.getCostoPack());
        return new ResponseEntity<>(packServicio.guardar(packBD), HttpStatus.OK);

    }

    @PostMapping("/{idPack}/servicio/{idServicio}")
    public ResponseEntity<?> agregarServicio(@PathVariable(name = "idPack") Long idPack,
            @PathVariable(name = "idServicio") Long idServicio) {
        ServicioDTO servicioAdd_DTO = packServicio.agregarServicio(idPack, idServicio);
        if (servicioAdd_DTO == null) {
            return new ResponseEntity<>("Servicio No Agregado", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Servicio Agregado", HttpStatus.OK);
    }

    @DeleteMapping("/{idPack}/servicio/{idServicio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> quitarServicio(@PathVariable(name = "idPack") Long idPack,
            @PathVariable(name = "idServicio") Long idServicio) {
        ServicioDTO quitarServicio_DTO = packServicio.quitarServicio(idPack, idServicio);
        if (quitarServicio_DTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quitarServicio_DTO, HttpStatus.OK);
    }

}
