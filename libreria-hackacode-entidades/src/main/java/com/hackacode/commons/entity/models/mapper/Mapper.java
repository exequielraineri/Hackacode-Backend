///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.hackacode.commons.entity.models.mapper;
//
//import com.hackacode.commons.entity.models.entity.Cliente;
//import com.hackacode.commons.entity.models.entity.Empleado;
//import com.hackacode.commons.entity.models.entity.PackTuristico;
//import com.hackacode.commons.entity.models.entity.Servicio;
//import com.hackacode.commons.entity.models.entity.Venta;
//import com.hackacode.commons.entity.models.entity.dto.ClienteDTO;
//import com.hackacode.commons.entity.models.entity.dto.EmpleadoDTO;
//import com.hackacode.commons.entity.models.entity.dto.PackTuristicoDTO;
//import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
//import com.hackacode.commons.entity.models.entity.dto.VentaDTO;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// *
// * @author 54385
// */
//public final class Mapper {
//    
//    public Mapper() {
//    }
//    
//    public static ClienteDTO mapearAClienteDTO(Cliente cliente) {
//        ClienteDTO clienteDto = new ClienteDTO();
//        clienteDto.setNombre(cliente.getNombre());
//        clienteDto.setApellido(cliente.getApellido());
//        clienteDto.setCelular(cliente.getCelular());
//        clienteDto.setDireccion(cliente.getDireccion());
//        clienteDto.setDni(cliente.getDni());
//        clienteDto.setEmail(cliente.getEmail());
//        clienteDto.setFechaNac(cliente.getFechaNac());
//        clienteDto.setFechaRegistro(cliente.getFechaRegistro());
//        clienteDto.setId(cliente.getId());
//        clienteDto.setNacionalidad(cliente.getNacionalidad());
//        return clienteDto;
//    }
//    
//    public static VentaDTO mapearAVentaDTO(Venta venta) {
//        VentaDTO ventaDTO = new VentaDTO();
//        ventaDTO.setCliente(mapearAClienteDTO(venta.getCliente()));
//        ventaDTO.setEmpleado(mapearAEmpleadoDTO(venta.getEmpleado()));
//        ventaDTO.setFechaRegistro(venta.getFechaRegistro());
//        ventaDTO.setFechaVenta(venta.getFechaVenta());
//        ventaDTO.setId(venta.getId());
//        ventaDTO.setMedioPago(venta.getMedioPago());
//        ventaDTO.setProducto(mapearAPackTuristicoDTO(venta.getProducto()));
//        return ventaDTO;
//    }
//    
//    public static EmpleadoDTO mapearAEmpleadoDTO(Empleado empleado) {
//        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
//        empleadoDTO.setNombre(empleado.getNombre());
//        empleadoDTO.setApellido(empleado.getApellido());
//        empleadoDTO.setCelular(empleado.getCelular());
//        empleadoDTO.setDireccion(empleado.getDireccion());
//        empleadoDTO.setDni(empleado.getDni());
//        empleadoDTO.setEmail(empleado.getEmail());
//        empleadoDTO.setFechaNac(empleado.getFechaNac());
//        empleadoDTO.setFechaRegistro(empleado.getFechaRegistro());
//        empleadoDTO.setId(empleado.getId());
//        empleadoDTO.setNacionalidad(empleado.getNacionalidad());
//        
//        empleadoDTO.setCargo(empleado.getCargo());
//        empleadoDTO.setSueldo(empleado.getSueldo());
//        
//        empleadoDTO.setVentas(mapearAListaVentaDto(empleado.getVentas()));
//        
//        return empleadoDTO;
//    }
//    
//    public static PackTuristicoDTO mapearAPackTuristicoDTO(PackTuristico producto) {
//        PackTuristicoDTO packTuristicoDTO = new PackTuristicoDTO();
//        packTuristicoDTO.setCostoPack(producto.getCostoPack());
//        packTuristicoDTO.setFechaRegistro(producto.getFechaRegistro());
//        packTuristicoDTO.setId(producto.getId());
//        packTuristicoDTO.setVentas(mapearAListaVentaDto(producto.getVentas()));
//        packTuristicoDTO.setServicios(mapearAListaServicioDto(producto.getServicios()));
//        return packTuristicoDTO;
//    }
//    
//    public static ServicioDTO mapearAServicioDTO(Servicio servicio) {
//        ServicioDTO servicioDTO = new ServicioDTO();
//        servicioDTO.setCosto(servicio.getCosto());
//        servicioDTO.setDescripcion(servicio.getDescripcion());
//        servicioDTO.setDestino(servicio.getDestino());
//        servicioDTO.setFechaRegistro(servicio.getFechaRegistro());
//        servicioDTO.setFechaServicio(servicio.getFechaServicio());
//        servicioDTO.setId(servicio.getId());
//        servicioDTO.setNombre(servicio.getNombre());
//        servicioDTO.setPack(mapearAListaPackTuristicoDTO(servicio.getPack()));
//        return servicioDTO;
//    }
//    
//    public static List<PackTuristicoDTO> mapearAListaPackTuristicoDTO(List<PackTuristico> paquete) {
//        return paquete.stream().map(pack -> {
//            PackTuristicoDTO packTuristicoDTO = mapearAPackTuristicoDTO(pack);
//            return packTuristicoDTO;
//        }).collect(Collectors.toList());
//    }
//    
//    public static List<ServicioDTO> mapearAListaServicioDto(List<Servicio> servicios) {
//        return servicios.stream().map(servicio -> {
//            ServicioDTO servicioDto = mapearAServicioDTO(servicio);
//            return servicioDto;
//        }).collect(Collectors.toList());
//    }
//    
//    public static List<VentaDTO> mapearAListaVentaDto(List<Venta> ventas) {
//        return ventas.stream().map(venta -> {
//            VentaDTO ventaDto = mapearAVentaDTO(venta);
//            return ventaDto;
//        }).collect(Collectors.toList());
//    }
//    
//    public static List<ClienteDTO> mapearAListaClienteDto(List<Cliente> clientesBD) {
//        return clientesBD.stream().map(cliente -> {
//            ClienteDTO clienteDto = mapearAClienteDTO(cliente);
//            return clienteDto;
//        }).collect(Collectors.toList());
//        
//    }
//    
//    public static List<EmpleadoDTO> mapearAListaEmpleadoDto(List<Empleado> empleadoDB) {
//        return empleadoDB.stream().map(empleado -> {
//            EmpleadoDTO empleadoDto = mapearAEmpleadoDTO(empleado);
//            return empleadoDto;
//        }).collect(Collectors.toList());
//        
//    }
//    
//    public static Cliente mapearACliente(ClienteDTO clienteDto) {
//        Cliente cliente = new Cliente();
//        cliente.setNombre(clienteDto.getNombre());
//        cliente.setApellido(clienteDto.getApellido());
//        cliente.setCelular(clienteDto.getCelular());
//        cliente.setDireccion(clienteDto.getDireccion());
//        cliente.setDni(clienteDto.getDni());
//        cliente.setEmail(clienteDto.getEmail());
//        cliente.setFechaNac(clienteDto.getFechaNac());
//        cliente.setFechaRegistro(clienteDto.getFechaRegistro());
//        cliente.setId(clienteDto.getId());
//        cliente.setNacionalidad(clienteDto.getNacionalidad());
//        cliente.setVentas(mapearAListaVenta(clienteDto.getVentas()));
//        return cliente;
//    }
//    
//    public static Empleado mapearAEmpleado(EmpleadoDTO empleadoDto) {
//        Empleado empleado = new Empleado();
//        empleado.setNombre(empleadoDto.getNombre());
//        empleado.setApellido(empleadoDto.getApellido());
//        empleado.setCelular(empleadoDto.getCelular());
//        empleado.setDireccion(empleadoDto.getDireccion());
//        empleado.setDni(empleadoDto.getDni());
//        empleado.setEmail(empleadoDto.getEmail());
//        empleado.setFechaNac(empleadoDto.getFechaNac());
//        empleado.setFechaRegistro(empleadoDto.getFechaRegistro());
//        empleado.setId(empleadoDto.getId());
//        empleado.setNacionalidad(empleadoDto.getNacionalidad());
//        
//        empleado.setCargo(empleadoDto.getCargo());
//        empleado.setSueldo(empleadoDto.getSueldo());
//        
//        empleado.setVentas(mapearAListaVenta(empleadoDto.getVentas()));
//        return empleado;
//    }
//    
//    public static Venta mapearAVenta(VentaDTO ventaDto) {
//        Venta venta = new Venta();
//        venta.setCliente(mapearACliente(ventaDto.getCliente()));
//        venta.setEmpleado(mapearAEmpleado(ventaDto.getEmpleado()));
//        venta.setFechaRegistro(ventaDto.getFechaRegistro());
//        venta.setFechaVenta(ventaDto.getFechaVenta());
//        venta.setId(ventaDto.getId());
//        venta.setMedioPago(ventaDto.getMedioPago());
//        venta.setProducto(mapearAPackTuristico(ventaDto.getProducto()));
//        return venta;
//    }
//    
//    public static PackTuristico mapearAPackTuristico(PackTuristicoDTO productoDto) {
//        PackTuristico packTuristico = new PackTuristico();
//        packTuristico.setCostoPack(productoDto.getCostoPack());
//        packTuristico.setFechaRegistro(productoDto.getFechaRegistro());
//        packTuristico.setId(productoDto.getId());
//        packTuristico.setVentas(mapearAListaVenta(productoDto.getVentas()));
//        packTuristico.setServicios(mapearAListaServicio(productoDto.getServicios()));
//        return packTuristico;
//    }
//    
//    public static List<Venta> mapearAListaVenta(List<VentaDTO> ventasDto) {
//        ventasDto = ventasDto == null ? new ArrayList<>() : ventasDto;
//        return ventasDto.stream().map(ventaDto -> {
//            Venta venta = mapearAVenta(ventaDto);
//            return venta;
//        }).collect(Collectors.toList());
//    }
//    
//    public static List<Servicio> mapearAListaServicio(List<ServicioDTO> serviciosDto) {
//        serviciosDto = serviciosDto == null ? new ArrayList<>() : serviciosDto;
//        return serviciosDto.stream().map(servicioDto -> {
//            Servicio servicio = mapearAServicio(servicioDto);
//            return servicio;
//        }).collect(Collectors.toList());
//    }
//    
//    public static Servicio mapearAServicio(ServicioDTO servicioDto) {
//        Servicio servicio = new Servicio();
//        servicio.setCosto(servicioDto.getCosto());
//        servicio.setDescripcion(servicioDto.getDescripcion());
//        servicio.setDestino(servicioDto.getDestino());
//        servicio.setFechaRegistro(servicioDto.getFechaRegistro());
//        servicio.setFechaServicio(servicioDto.getFechaServicio());
//        servicio.setId(servicioDto.getId());
//        servicio.setNombre(servicioDto.getNombre());
//        servicio.setPack(mapearAListaPackTuristico(servicioDto.getPack()));
//        return servicio;
//    }
//    
//    public static List<PackTuristico> mapearAListaPackTuristico(List<PackTuristicoDTO> paqueteDto) {
//        paqueteDto = paqueteDto == null ? new ArrayList<>() : paqueteDto;
//        return paqueteDto.stream().map(packDto -> {
//            PackTuristico packTuristico = mapearAPackTuristico(packDto);
//            return packTuristico;
//        }).collect(Collectors.toList());
//    }
//    
//}
