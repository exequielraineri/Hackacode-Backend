package com.hackacode.app.pack.models.dao;

import com.hackacode.commons.entity.models.entity.PackTuristico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPackTuristicoDao extends CrudRepository<PackTuristico, Long>{
    
}
