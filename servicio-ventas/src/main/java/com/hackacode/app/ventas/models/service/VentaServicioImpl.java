package com.hackacode.app.ventas.models.service;

import com.hackacode.app.ventas.models.dao.IVentaDao;
import com.hackacode.commons.entity.models.entity.Venta;
import com.hackacode.commons.entity.models.entity.dto.VentaDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServicioImpl implements IVentaServicio {

    @Autowired
    private IVentaDao ventaDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO> listar() {
        List<Venta> ventas = (List<Venta>) ventaDao.findAll();
        List<VentaDTO> ventasDTO = ventas.stream()
                .map(venta -> modelMapper.map(venta, VentaDTO.class))
                .collect(Collectors.toList());
        return ventasDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public VentaDTO buscarPorId(Long id) {
        Venta venta = ventaDao.findById(id).orElse(null);
        if (venta == null) {
            return null;
        }

        return modelMapper.map(venta, VentaDTO.class);
    }

    @Override
    @Transactional
    public VentaDTO guardar(VentaDTO ventaDTO) {
        //IMPORTANTE, FALTA ANTES DE GUARDAR VERIFICAR SI EXISTE TANTO EMPLEADO, CLIENTE, PACK,SERVICIOS
        Venta venta = modelMapper.map(ventaDTO, Venta.class);
        venta = ventaDao.save(venta);
        return modelMapper.map(venta, VentaDTO.class);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        ventaDao.deleteById(id);
    }

   
}
