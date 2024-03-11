package com.hackacode.app.ventas.models.dao;

import com.hackacode.commons.entity.models.entity.Venta;
import java.util.List;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> 1bdf1cf2e09ca2136e98a62efb158ad2fe3a3417
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaDao extends CrudRepository<Venta, Long> {
 
    
    @Query("SELECT v FROM Venta v WHERE v.empleado.id=:empleado_id ORDER BY v.fechaVenta DESC")
    List<Venta> ventasPorIdEmpleado(@Param(value = "empleado_id") Long idEmpleado);
    
    
    
}
