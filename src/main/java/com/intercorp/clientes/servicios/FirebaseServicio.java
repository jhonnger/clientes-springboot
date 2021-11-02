package com.intercorp.clientes.servicios;

import com.google.cloud.firestore.Firestore;

public interface FirebaseServicio {
    Firestore obtenerInstanciaFirestore();
}
