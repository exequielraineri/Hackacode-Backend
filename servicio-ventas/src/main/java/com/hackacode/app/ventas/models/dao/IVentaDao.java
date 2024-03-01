package com.hackacode.app.ventas.models.dao;

import com.hackacode.commons.entity.models.entity.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaDao extends CrudRepository<Venta, Long> {
    
}
