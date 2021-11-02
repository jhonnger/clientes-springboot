package com.intercorp.clientes.mappers;

import com.intercorp.clientes.dto.ClienteDto;
import com.intercorp.clientes.entidades.Cliente;
import com.intercorp.clientes.util.ClienteUtil;

public class ClienteMapper {

    public static ClienteDto mapearDesdeEntidad(Cliente cliente){
        ClienteDto clienteDto;
        clienteDto = new ClienteDto();

        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        clienteDto.setEdad(cliente.getEdad());
        clienteDto.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteDto.setFechaProbableMuerte(ClienteUtil.obtenerFechaProbableMuerte(cliente.getFechaNacimiento()));

        return  clienteDto;
    }
}
