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
           SELECT s.id, COUNT(*) AS total_ventas
           	FROM ventas v
                    JOIN pack_servicio p
                        ON v.producto_id = p.pack_id
                            JOIN servicios s
                                ON p.servicio_id= s.id
                                    GROUP BY s.id ORDER BY total_ventas DESC LIMIT 5;
           """,
            nativeQuery = true)
    List<List<String>> serviciosMasVendidos();

}
