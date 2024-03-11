package com.hackacode.app.ventas.controllers;

import com.hackacode.app.ventas.models.dao.feign.ClienteServicioFeign;
import com.hackacode.app.ventas.models.dao.feign.EmpleadoServicioFeign;
import com.hackacode.app.ventas.models.service.IVentaServicio;
import com.hackacode.commons.entity.models.entity.dto.ClienteDTO;
import com.hackacode.commons.entity.models.entity.dto.EmpleadoDTO;
import com.hackacode.commons.entity.models.entity.dto.PackTuristicoDTO;
import com.hackacode.commons.entity.models.entity.dto.VentaDTO;
import com.hackacode.commons.entity.util.MedioPago;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    
    @Autowired
    private IVentaServicio ventaServicio;
    
    @Autowired
    private ClienteServicioFeign clienteFeign;
    
    @Autowired
    private EmpleadoServicioFeign empleadoFeign;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/")
    public List<VentaDTO> listar() {
        return ventaServicio.listar();
    }
    
    @GetMapping("/{id}")
    public VentaDTO obtener(@PathVariable Long id) {
        return ventaServicio.buscarPorId(id);
    }
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@RequestBody PackTuristicoDTO producto,
            @RequestParam(name = "cliente_id") String idCliente,
            @RequestParam(name = "empleado_id") String idEmpleado,
            @RequestParam(name = "medio_pago") String medioPago) {
        ClienteDTO clienteDTO = null;
        EmpleadoDTO empleadoDTO = null;

        //obtengo el response de servicio clientes
        ResponseEntity<?> responseClienteFeign = clienteFeign.obtenerCliente(Long.valueOf(idCliente));

        //obtengo el response de servicio empleados
        ResponseEntity<?> responseEmpleadoFeign = empleadoFeign.obtenerEmpleado(Long.valueOf(idEmpleado));
        if (responseClienteFeign.getStatusCode().is2xxSuccessful() && responseClienteFeign.getBody() instanceof Map) {
            //la solicitud fue exitosa
            Map<String, Object> responseMapBody = (Map<String, Object>) responseClienteFeign.getBody();
            //si el cliente vuelve en la respuesta o no
            if (responseMapBody.containsKey("cliente")) {
                Map<String, Object> mapCliente = (Map<String, Object>) responseMapBody.get("cliente");
                clienteDTO = modelMapper.map(mapCliente, ClienteDTO.class);
            } else {
                return new ResponseEntity<>(responseMapBody, HttpStatus.NOT_FOUND);
            }
        }
        
        if (responseEmpleadoFeign.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> mapEmpleado = (Map<String, Object>) responseEmpleadoFeign.getBody();
            empleadoDTO = modelMapper.map(mapEmpleado, EmpleadoDTO.class);
        } else {
            return new ResponseEntity<>(responseEmpleadoFeign, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setFechaRegistro(new Date());
        ventaDTO.setCliente(clienteDTO);
        ventaDTO.setEmpleado(empleadoDTO);
        ventaDTO.setFechaVenta(new Date());
        ventaDTO.setMedioPago(MedioPago.valueOf(medioPago));
        ventaDTO.setProducto(producto);
        ventaDTO.setImporte(BigDecimal.valueOf(producto.getCostoPack()));
        return new ResponseEntity<>(ventaServicio.guardar(ventaDTO), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ventaServicio.eliminarPorId(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VentaDTO> editar(@RequestBody VentaDTO ventaDTO, @PathVariable Long id) {
        VentaDTO ventaBD_DTO = ventaServicio.buscarPorId(id);
        
        if (ventaBD_DTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ventaServicio.guardar(ventaBD_DTO), HttpStatus.OK);
        
    }
    
}
