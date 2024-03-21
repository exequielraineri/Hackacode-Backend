package com.hackacode.app.servicios.models.dao;

import com.hackacode.commons.entity.models.entity.Servicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioDao extends CrudRepository<Servicio, Long> {

    /**
     *
     * @return
     */
    @Query(value = """
                   select ps.servicio_id, count(*) from ventas as v
                   	join pack_servicio as ps on v.producto_id=ps.pack_id group by ps.servicio_id;
           """, nativeQuery = true)
    List<List<String>> serviciosMasVendidos();

}
