package com.hackacode.app.empleados.models.dao;

import com.hackacode.commons.entity.models.entity.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoDao extends CrudRepository<Empleado, Long> {

}
