package com.intercorp.clientes.servicios;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.intercorp.clientes.dto.ClienteDto;
import com.intercorp.clientes.dto.ClienteKpiDto;
import com.intercorp.clientes.dto.EdadDto;
import com.intercorp.clientes.entidades.Cliente;
import com.intercorp.clientes.mappers.ClienteMapper;
import com.intercorp.clientes.util.ClienteUtil;
import com.intercorp.clientes.util.Constantes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Service
public class ClienteServicioImpl implements ClienteServicio{

    FirebaseServicio firebaseServicio;

    public ClienteServicioImpl(FirebaseServicio firebaseServicio) {
        this.firebaseServicio = firebaseServicio;
    }

    @Override
    public List<ClienteDto> obtenerClientes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firebaseServicio.obtenerInstanciaFirestore()
                .collection(Constantes.SCHEMA_CLIENTE)
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        return documents.stream()
                .map(dc -> dc.toObject(Cliente.class))
                .map(ClienteMapper::mapearDesdeEntidad)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteKpiDto obtenerKpiClientes() throws ExecutionException, InterruptedException {

        ClienteKpiDto clienteKpiDto;
        DocumentReference docRef = firebaseServicio.obtenerInstanciaFirestore()
                .collection(Constantes.SCHEMA_PARAMETROS).document(Constantes.ID_PARAMETROS);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();
        if (document.exists()) {
            clienteKpiDto = document.toObject(ClienteKpiDto.class);

            if(clienteKpiDto != null){
                clienteKpiDto.setDesviacion(Math.sqrt(clienteKpiDto.getVarianza()));

                return clienteKpiDto;
            }
        }

        throw new ClassCastException("Datos no econtrados");
    }

    @Override
    public Cliente crearCliente(Cliente cliente) throws ExecutionException, InterruptedException {

        EdadDto edad;

        edad = ClienteUtil.obtenerEdadDesdeFechaNac(cliente.getFechaNacimiento());

        cliente.setEdad(edad.getAnios());

        ApiFuture<DocumentReference> addedDocRef = firebaseServicio.obtenerInstanciaFirestore()
                .collection(Constantes.SCHEMA_CLIENTE).add(cliente);

        cliente.setId(addedDocRef.get().getId());

        actualizarKpiCliente(cliente.getEdad());

        return cliente;
    }

    private void actualizarKpiCliente(int nuevoNumero) throws ExecutionException, InterruptedException {
        ClienteKpiDto parametro;
        double nuevoPromedio, nuevaVarianza;

        parametro = obtenerKpiClientes();

        nuevoPromedio = ((parametro.getPromedio() * parametro.getCantidadRegistros()) + nuevoNumero) / (parametro.getCantidadRegistros() + 1);
        nuevaVarianza = ((parametro.getCantidadRegistros()) * parametro.getVarianza() + (nuevoNumero - nuevoPromedio) * (nuevoNumero - parametro.getPromedio()) ) / (parametro.getCantidadRegistros() + 1);

        parametro.setPromedio(nuevoPromedio);
        parametro.setVarianza(nuevaVarianza);
        parametro.setCantidadRegistros(parametro.getCantidadRegistros() + 1 );

        firebaseServicio.obtenerInstanciaFirestore()
                .collection(Constantes.SCHEMA_PARAMETROS).document(Constantes.ID_PARAMETROS).set(parametro);
    }
}
