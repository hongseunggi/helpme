package com.example.help.Log_inandout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.help.MainActivity;
import com.example.help.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText mEmail, mPassword;
    // final Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
    //final Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.login_email);
        mPassword = findViewById(R.id.login_password);

        findViewById(R.id.login_signup).setOnClickListener(this);
        findViewById(R.id.login_success).setOnClickListener(this);
    }

   /* @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
        {
            Toast.makeText(this, "자동 로그인 : " + user.getUid(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.login_signup:
                startActivity(new Intent(this, SignupActivity.class));
                break;

            case R.id.login_success:
                try{
                    mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "로그인 성공. " + user.getUid(), Toast.LENGTH_SHORT).show();
                                        MainActivity.Global.Pro=0;MainActivity.Global.Cal=0;MainActivity.Global.Col=0;MainActivity.Global.Fat=0;MainActivity.Global.Sug=0;MainActivity.Global.Na=0;
                                        if(user != null) {
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "로그인 에러.", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                    break;

                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "입력을 해주세요.", Toast.LENGTH_SHORT).show();
                }
        }
    }
}