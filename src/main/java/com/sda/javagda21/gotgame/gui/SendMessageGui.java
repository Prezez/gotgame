package com.sda.javagda21.gotgame.gui;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SendMessageGui {


    public SendMessageGui() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("C:\\Users\\kzawi\\IdeaProjects\\gotgame\\src\\main\\resources\\static\\friendlychat-b2806-firebase-adminsdk-zttwa-a318ab83ec.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://friendlychat.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

    @GetMapping("/messages")
    private void message(@RequestParam String message) {


        Firestore db = FirestoreClient.getFirestore();
        Map<String, String> docData = new HashMap<>();
        docData.put("message", message);
        ApiFuture<WriteResult> future = db.collection("messages").document("message").set(docData);
    }
}
