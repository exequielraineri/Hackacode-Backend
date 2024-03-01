package com.hackacode.app.pack.models.service;

import com.hackacode.commons.entity.models.entity.PackTuristico;
import com.hackacode.commons.entity.models.entity.Servicio;
import com.hackacode.commons.entity.models.entity.dto.PackTuristicoDTO;
import com.hackacode.commons.entity.models.entity.dto.ServicioDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import org.springframework.http.ResponseEntity;

public interface IPackTuristicoServicio extends InterfazServicio<PackTuristicoDTO> {

    public ServicioDTO agregarServicio(Long idPack, Long idServicio);

    public ServicioDTO quitarServicio(Long idPack, Long idServicio);

}
