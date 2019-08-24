package com.sda.javagda21.gotgame.gui;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.vaadin.flow.router.Route;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.FileInputStream;
import java.io.IOException;

@Route ("/")
public class GetMessageGui {

    @EventListener(ApplicationReadyEvent.class)
    public void main() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src\\main\\resources\\static\\friendlychat-b2806-firebase-adminsdk-zttwa-a318ab83ec.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://friendlychat.firebaseio.com")
                .build();


        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();


        DocumentReference docRef = db.collection("messages").document("message");
        docRef.addSnapshotListener((documentSnapshot, e) -> {
            assert documentSnapshot != null;
            System.out.println("Current data: " + documentSnapshot.getData());
        });


    }
}
