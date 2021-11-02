package com.intercorp.clientes.servicios;

import com.intercorp.clientes.dto.ClienteDto;
import com.intercorp.clientes.dto.ClienteKpiDto;
import com.intercorp.clientes.entidades.Cliente;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ClienteServicio {
    List<ClienteDto> obtenerClientes() throws ExecutionException, InterruptedException;

    ClienteKpiDto obtenerKpiClientes() throws ExecutionException, InterruptedException;

    Cliente crearCliente(Cliente cliente) throws ExecutionException, InterruptedException;
}
