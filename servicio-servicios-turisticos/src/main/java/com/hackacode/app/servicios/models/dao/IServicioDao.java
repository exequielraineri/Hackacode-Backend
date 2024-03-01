package com.hackacode.app.servicios.models.dao;

import com.hackacode.commons.entity.models.entity.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioDao extends CrudRepository<Servicio, Long> {

}
