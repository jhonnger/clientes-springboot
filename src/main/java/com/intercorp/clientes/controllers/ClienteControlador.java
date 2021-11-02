package com.intercorp.clientes.controllers;

import com.intercorp.clientes.dto.ClienteDto;
import com.intercorp.clientes.dto.ClienteKpiDto;
import com.intercorp.clientes.entidades.Cliente;
import com.intercorp.clientes.servicios.ClienteServicio;
import com.intercorp.clientes.util.ClienteUtil;
import com.intercorp.clientes.util.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/")
public class ClienteControlador {

    ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @Operation(summary = "Crear un cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado correctamente",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)) }),
        @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @PostMapping(value = "/creacliente")
    public ResponseEntity crearCliente(@Valid @RequestBody Cliente cliente, BindingResult result){

        ResponseEntity response;
        try{
            if (result.hasErrors()){
                response =  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClienteUtil.formatMessage(result));
            } else {
                response =  ResponseEntity.status( HttpStatus.CREATED).body(clienteServicio.crearCliente(cliente));
            }

        }catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;


    }

    @Operation(summary = "Listar clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes",
                content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteDto.class))) }),
        @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @GetMapping(value = "/listClientes")
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        ResponseEntity<List<ClienteDto>> response;
        try{
            response =  ResponseEntity.status(HttpStatus.OK).body( clienteServicio.obtenerClientes());
        }catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }

    @Operation(summary = "Indicadores de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicadores",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteKpiDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno",
                    content = @Content) })
    @GetMapping(value = "/kpideclientes")
    public ResponseEntity<ClienteKpiDto> kpideclientes() {

        ResponseEntity<ClienteKpiDto> response;
        try{
            response =  ResponseEntity.status(HttpStatus.OK).body( clienteServicio.obtenerKpiClientes());
        }catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return response;
    }
}
