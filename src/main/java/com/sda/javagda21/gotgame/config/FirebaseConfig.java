package com.sda.javagda21.gotgame.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    public FirebaseConfig() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src\\main\\resources\\static\\friendlychat-b2806-firebase-adminsdk-zttwa-a318ab83ec.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://friendlychat.firebaseio.com")
                .build();


        FirebaseApp.initializeApp(options);
    }
}
