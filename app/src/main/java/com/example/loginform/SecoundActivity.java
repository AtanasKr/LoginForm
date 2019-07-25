package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecoundActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound2);
        firebaseAuth = FirebaseAuth.getInstance();
        logOff = (Button) findViewById(R.id.btnLogOff);
        logOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                Intent intent7 = new Intent(SecoundActivity.this,MainActivity.class);
                startActivity(intent7);
            }
        });
    }
}
