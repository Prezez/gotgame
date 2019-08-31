package com.sda.javagda21.gotgame.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    public FirebaseConfig() throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/firebase/friendlychat-b2806-firebase-adminsdk-zttwa-a318ab83ec.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(resourceAsStream))
                .setDatabaseUrl("https://friendlychat.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
    }
}
