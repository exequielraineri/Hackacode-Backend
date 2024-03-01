package com.hackacode.commons.entity.util;

import java.util.List;

public interface InterfazServicio<T> {

    public List<T> listar();

    public T buscarPorId(Long id);

    public T guardar(T objeto);

    public void eliminarPorId(Long id);

}
