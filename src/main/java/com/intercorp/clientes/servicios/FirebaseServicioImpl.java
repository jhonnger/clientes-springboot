package com.intercorp.clientes.servicios;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


@Service
public class FirebaseServicioImpl implements FirebaseServicio{

    Firestore db;

    public FirebaseServicioImpl() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("intercorp-cliente-firebase-adminsdk.json")).getFile());

        try {
            InputStream serviceAccount = new FileInputStream(file);
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
