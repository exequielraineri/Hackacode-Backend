package com.hackacode.app.pack.models.dao.feign;

import com.hackacode.commons.entity.models.entity.Servicio;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servicio-servicios-turisticos")
public interface ServicioFeignCliente {

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerServicio(@PathVariable Long id);

    @PostMapping("/")
    public ResponseEntity<?> guardar(@RequestBody ServicioDTO servicioDTO);
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody ServicioDTO servicioDTO, @PathVariable Long id);
}
