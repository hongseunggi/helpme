package com.example.help;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UinfoActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();


    private EditText mName, mHeight, mWeight, mBirthdate;
    private CheckBox man, woman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uinfo);

        mName = findViewById(R.id.uinfo_name_edit);
        mHeight = findViewById(R.id.uinfo_height_edit);
        mWeight = findViewById(R.id.uinfo_weight_edit);
        mBirthdate = findViewById(R.id.uinfo_birthdate_edit);
        man = findViewById(R.id.Man);
        woman = findViewById(R.id.Woman);
        findViewById(R.id.uinfo_save_button).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (mAuth.getCurrentUser() != null) {
            if(man.isChecked())
            {
            FirebaseUser user = mAuth.getCurrentUser();
            String uinfoId = mStore.collection(FirebaseID.user).document().getId();
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.userId, mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.name, mName.getText().toString());
            data.put(FirebaseID.height, mHeight.getText().toString());
            data.put(FirebaseID.weight, mWeight.getText().toString());
            data.put(FirebaseID.birthdate, mBirthdate.getText().toString());
            data.put("Gender", "Man");
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("add").set(data, SetOptions.merge());
            finish();
            }
            else if(woman.isChecked())
            {
                FirebaseUser user = mAuth.getCurrentUser();
                String uinfoId = mStore.collection(FirebaseID.user).document().getId();
                Map<String, Object> data = new HashMap<>();
                data.put(FirebaseID.userId, mAuth.getCurrentUser().getUid());
                data.put(FirebaseID.name, mName.getText().toString());
                data.put(FirebaseID.height, mHeight.getText().toString());
                data.put(FirebaseID.weight, mWeight.getText().toString());
                data.put(FirebaseID.birthdate, mBirthdate.getText().toString());
                data.put("Gender", "Woman");
                data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("add").set(data, SetOptions.merge());
                finish();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}