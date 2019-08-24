package com.sda.javagda21.gotgame.gui;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SendMessageGui {


    @GetMapping("/messages")
    private void message(@RequestParam String message) {


        Firestore db = FirestoreClient.getFirestore();
        Map<String, String> docData = new HashMap<>();
        docData.put("message", message);
        ApiFuture<WriteResult> future = db.collection("messages").document("message").set(docData);
    }
}
