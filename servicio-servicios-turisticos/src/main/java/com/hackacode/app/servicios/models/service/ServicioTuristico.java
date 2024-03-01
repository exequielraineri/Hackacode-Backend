package com.hackacode.app.servicios.models.service;

import com.hackacode.app.servicios.models.dao.IServicioDao;
import com.hackacode.commons.entity.models.entity.Servicio;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.Date;
import java.util.List;
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
    
}
