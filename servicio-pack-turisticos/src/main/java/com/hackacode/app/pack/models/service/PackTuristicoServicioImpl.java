package com.hackacode.app.pack.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackacode.app.pack.models.dao.IPackTuristicoDao;
import com.hackacode.app.pack.models.dao.feign.ServicioFeignCliente;
import com.hackacode.commons.entity.models.entity.PackTuristico;
import com.hackacode.commons.entity.models.entity.Servicio;
import com.hackacode.commons.entity.models.entity.dto.PackTuristicoDTO;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PackTuristicoServicioImpl implements IPackTuristicoServicio {

    @Autowired
    private IPackTuristicoDao packTuristicoDao;

    @Autowired
    private ServicioFeignCliente servicioFeignCliente;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(PackTuristicoServicioImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<PackTuristicoDTO> listar() {
        List<PackTuristico> paquetes = (List<PackTuristico>) packTuristicoDao.findAll();
        List<PackTuristicoDTO> paquetesDTO = paquetes.stream()
                .map(paquete -> modelMapper.map(paquete, PackTuristicoDTO.class))
                .collect(Collectors.toList());
        return paquetesDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public PackTuristicoDTO buscarPorId(Long id) {
        PackTuristico packBD = packTuristicoDao.findById(id).orElse(null);
        if (packBD == null) {
            return null;
        }
        return modelMapper.map(packBD, PackTuristicoDTO.class);

    }

    @Override
    @Transactional
    public PackTuristicoDTO guardar(PackTuristicoDTO packDto) {
        PackTuristico pack = modelMapper.map(packDto, PackTuristico.class);
        pack.setServicios(new ArrayList<>());
        pack.setFechaRegistro(new Date());
        pack = packTuristicoDao.save(pack);
        return modelMapper.map(pack, PackTuristicoDTO.class);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        packTuristicoDao.deleteById(id);
    }

    @Override
    public ServicioDTO agregarServicio(Long idPack, Long idServicio) {
        PackTuristico packBD = packTuristicoDao.findById(idPack).orElse(null);

        ResponseEntity<?> responseFeign = servicioFeignCliente.obtenerServicio(idServicio);

        if (responseFeign.getStatusCode().is2xxSuccessful() && responseFeign.getBody() instanceof Map) {
            Map<String, Object> responseBody = (Map<String, Object>) responseFeign.getBody();

            if (responseBody.containsKey("servicio")) {
                Map<String, Object> servicioMap = (Map<String, Object>) responseBody.get("servicio");

                ServicioDTO servicioDto = modelMapper.map(servicioMap, ServicioDTO.class);

                packBD.getServicios().add(modelMapper.map(servicioDto, Servicio.class));
                packTuristicoDao.save(packBD);
                return servicioDto;
            }
        }

        return null;
    }

    @Override
    public ServicioDTO quitarServicio(Long idPack, Long idServicio) {
        PackTuristico packBD = packTuristicoDao.findById(idPack).orElse(null);

        //Obtengo el responseEntity del cliente feig de servicios turisticos
        ResponseEntity<?> responseFeign = servicioFeignCliente.obtenerServicio(idServicio);

        //si la respuesta es correcta se castea a un map con tipo string y objeto, para obtener el servicio
        if (responseFeign.getStatusCode().is2xxSuccessful() && responseFeign.getBody() instanceof Map) {
            Map<String, Object> responseBody = (Map<String, Object>) responseFeign.getBody();

            if (responseBody.containsKey("servicio")) {
                Map<String, Object> servicioMap = (Map<String, Object>) responseBody.get("servicio");
                ServicioDTO servicioDto = modelMapper.map(servicioMap, ServicioDTO.class);

                packBD.getServicios().removeIf(servicio -> servicio.getId().equals(idServicio));

                packTuristicoDao.save(packBD);
                return servicioDto;
            }
        }

        return null;
    }

}
