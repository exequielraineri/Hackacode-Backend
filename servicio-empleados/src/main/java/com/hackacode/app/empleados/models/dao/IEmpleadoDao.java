package com.hackacode.app.empleados.models.dao;

import com.hackacode.commons.entity.models.entity.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoDao extends CrudRepository<Empleado, Long> {

    @Query("SELECT e FROM Empleado e WHERE e.email=:email AND e.password=:password")
    public Empleado empleadoPorEmailYPassword(@Param(value = "email") String email, @Param(value = "password") String password);

}
