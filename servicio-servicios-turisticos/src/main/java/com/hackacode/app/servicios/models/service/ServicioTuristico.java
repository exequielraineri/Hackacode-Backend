package com.hackacode.app.servicios.models.service;

import com.hackacode.app.servicios.models.dao.IServicioDao;
import com.hackacode.commons.entity.models.entity.Servicio;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioTuristico implements InterfazServicio<ServicioDTO> {

    @Autowired
    private IServicioDao servicioDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ServicioDTO> listar() {
        List<Servicio> servicios = (List<Servicio>) servicioDao.findAll();
        List<ServicioDTO> serviciosDTO = servicios.stream()
                .map(servicio -> modelMapper.map(servicio, ServicioDTO.class))
                .collect(Collectors.toList());

        return serviciosDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public ServicioDTO buscarPorId(Long id) {
        Servicio servicioBD = servicioDao.findById(id).orElse(null);
        if (servicioBD == null) {
            return null;
        }
        return modelMapper.map(servicioBD, ServicioDTO.class);
    }

    @Override
    @Transactional
    public ServicioDTO guardar(ServicioDTO servicioDTO) {
        Servicio servicio = modelMapper.map(servicioDTO, Servicio.class);
        servicio.setFechaRegistro(new Date());
        servicio = servicioDao.save(servicio);
        return modelMapper.map(servicio, ServicioDTO.class);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        servicioDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<?> serviciosMasVendidos() {

        List<List<String>> serviciosMasVendidos = servicioDao.serviciosMasVendidos();
        Map<String, Object> servicioMap;
        List<Map<String, Object>> responseServicio = new ArrayList<>();
        for (List<String> servicio : serviciosMasVendidos) {
            servicioMap = new HashMap();
            servicioMap.put("servicio", servicio.get(0).toString());
            servicioMap.put("cantidad_ventas", servicio.get(1).toString());

            responseServicio.add(servicioMap);
        }

        return responseServicio;

    }

}
