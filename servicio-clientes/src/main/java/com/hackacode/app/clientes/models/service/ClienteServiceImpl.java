package com.hackacode.app.clientes.models.service;

import com.hackacode.app.clientes.models.dao.ClienteDao;
import com.hackacode.commons.entity.models.entity.Cliente;
import com.hackacode.commons.entity.models.entity.dto.ClienteDTO;
import com.hackacode.commons.entity.util.InterfazServicio;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements InterfazServicio<ClienteDTO> {
    
    @Autowired
    private ClienteDao clienteDao;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> listar() {
        List<Cliente> clientesBD = (List<Cliente>) clienteDao.findAll();
        List<ClienteDTO> clientesDTOs = clientesBD.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        
        return clientesDTOs;
    }
    
    @Override
    @Transactional(readOnly = true)
    public ClienteDTO buscarPorId(Long id) {
        
        Cliente clienteBD = clienteDao.findById(id).orElse(null);
        if (clienteBD == null) {
            return null;
        }
        return modelMapper.map(clienteBD, ClienteDTO.class);
        
    }
    
    @Override
    @Transactional
    public ClienteDTO guardar(ClienteDTO clienteDto) {
        clienteDto.setFechaRegistro(new Date());
        Cliente cliente = new Cliente();
        cliente = modelMapper.map(clienteDto, Cliente.class);
        cliente = clienteDao.save(cliente);
        return modelMapper.map(cliente, ClienteDTO.class);
    }
    
    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        clienteDao.deleteById(id);
    }
    
}
