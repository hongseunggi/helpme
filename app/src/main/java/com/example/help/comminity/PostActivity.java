package com.example.help.comminity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.help.FirebaseID;
import com.example.help.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private EditText mTitle, mContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mTitle = findViewById(R.id.post_title_edit);
        mContents = findViewById(R.id.post_contents_edit);

        findViewById(R.id.post_save_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mAuth.getCurrentUser() != null){
            String postId = mStore.collection(FirebaseID.post).document().getId();
            Map<String,Object> data = new HashMap<>();
            data.put(FirebaseID.userId, mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.title, mTitle.getText().toString());
            data.put(FirebaseID.contents, mContents.getText().toString());
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            mStore.collection(FirebaseID.post).document(postId).set(data, SetOptions.merge());
            finish();
        }
    }
}