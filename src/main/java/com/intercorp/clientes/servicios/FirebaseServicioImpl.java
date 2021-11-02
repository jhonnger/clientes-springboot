package com.intercorp.clientes.servicios;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
public class FirebaseServicioImpl implements FirebaseServicio{

    Firestore db;

    public FirebaseServicioImpl() {
        String rutaJsonFcm = "/opt/intercorp-cliente-firebase-adminsdk-8z3tv-9732c7d252.json";

        try {
            InputStream serviceAccount = new FileInputStream(rutaJsonFcm);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Firestore obtenerInstanciaFirestore() {
        return db;
    }
}
