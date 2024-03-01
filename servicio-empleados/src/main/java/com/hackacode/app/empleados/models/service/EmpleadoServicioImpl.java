package com.hackacode.app.empleados.models.service;

import com.hackacode.app.empleados.models.dao.IEmpleadoDao;
import com.hackacode.commons.entity.models.entity.Empleado;
import com.hackacode.commons.entity.models.entity.dto.EmpleadoDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServicioImpl implements InterfazServicio<EmpleadoDTO> {

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listar() {
        List<Empleado> empleados = (List<Empleado>) empleadoDao.findAll();
        List<EmpleadoDTO> empleadosDTO = empleados.stream()
                .map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class))
                .collect(Collectors.toList());

        return empleadosDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public EmpleadoDTO buscarPorId(Long id) {
        Empleado empleadoDB = empleadoDao.findById(id).orElse(null);
        if (empleadoDB == null) {
            return null;
        }
        return modelMapper.map(empleadoDB, EmpleadoDTO.class);
    }

    @Override
    @Transactional
    public EmpleadoDTO guardar(EmpleadoDTO empleadoDTO) {
        Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
        empleado.setFechaRegistro(new Date());
        empleado = empleadoDao.save(empleado);
        return modelMapper.map(empleado, EmpleadoDTO.class);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        empleadoDao.deleteById(id);
    }

}
