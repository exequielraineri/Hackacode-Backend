package com.hackacode.app.clientes.models.dao;

import com.hackacode.commons.entity.models.entity.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE TYPE(c) = Cliente")
    @Override
    List<Cliente> findAll();

}
