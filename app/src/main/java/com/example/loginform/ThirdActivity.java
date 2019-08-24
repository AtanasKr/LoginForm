package com.example.loginform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ThirdActivity extends AppCompatActivity {
    private Button cancel,confirm;
    private EditText regname,regpassword,regemail;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        idAssign();

        firebaseAuth=FirebaseAuth.getInstance();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 =  new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(intent3);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(validate()){
            //Upload the data base
                String reg_mail = regemail.getText().toString().trim();
                String reg_password= regpassword.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(reg_mail,reg_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(ThirdActivity.this,"Registration is complete",Toast.LENGTH_SHORT).show();
                            Intent intent4 = new Intent(ThirdActivity.this,MainActivity.class);
                            startActivity(intent4);

                        }
                        else {
                            Toast.makeText(ThirdActivity.this,"Error ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            }
        });
    }
    public void idAssign(){
        cancel = (Button) findViewById(R.id.btnCancel);
        confirm = (Button) findViewById(R.id.btnConfirm);
        regname = (EditText) findViewById(R.id.regName);
        regpassword = (EditText) findViewById(R.id.regPassword);
        regemail = (EditText) findViewById(R.id.regEmail);
    }
    private Boolean validate(){
        Boolean result = false;

        String name  = regname.getText().toString();
        String password = regpassword.getText().toString();
        String email = regemail.getText().toString();

        if(name.isEmpty()||password.isEmpty()||email.isEmpty()){
            Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }
}
