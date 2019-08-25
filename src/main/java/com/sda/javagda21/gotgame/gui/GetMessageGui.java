package com.sda.javagda21.gotgame.gui;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.sda.javagda21.gotgame.service.HistoryMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GetMessageGui {


    private HistoryMessageService historyMessageService;

    @Autowired
    public GetMessageGui(HistoryMessageService historyMessageService) {
        this.historyMessageService = historyMessageService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void main() {

        Firestore db = FirestoreClient.getFirestore();


        DocumentReference docRef = db.collection("messages").document("message");
        docRef.addSnapshotListener((documentSnapshot, e) -> {
            assert documentSnapshot != null;
            historyMessageService.addMessage(String.valueOf(documentSnapshot.getData()));
        });


    }


}

