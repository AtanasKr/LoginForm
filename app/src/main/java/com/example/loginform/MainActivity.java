package com.example.loginform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private Button register;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.inputName);
        password = (EditText) findViewById(R.id.inputPasword);
        info = (TextView) findViewById(R.id.failAttempts);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnRegister);
        firebaseAuth = firebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null) {
            finish();
            Intent intent5 = new Intent(this, SecoundActivity.class);
            startActivity(intent5);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent2);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });
    }
    private void validate(String userName,String userPassword){
        progressDialog.setMessage("Loading, please wait");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT);
                    Intent intent6 = new Intent(MainActivity.this,SecoundActivity.class);
                    startActivity(intent6);
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_SHORT);
                    counter--;
                    info.setText("Tries left"+counter);
                    if(counter==0){
                        login.setEnabled(false);
                    }
                }
            }
        });
    }

}
