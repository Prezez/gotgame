package com.sda.javagda21.gotgame.gui;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetMessageGui {

    @EventListener(ApplicationReadyEvent.class)
    public void main() {

        Firestore db = FirestoreClient.getFirestore();


        DocumentReference docRef = db.collection("messages").document("message");
        docRef.addSnapshotListener((documentSnapshot, e) -> {
            assert documentSnapshot != null;
            System.out.println("Current data: " + documentSnapshot.getData());
        });


    }
}
