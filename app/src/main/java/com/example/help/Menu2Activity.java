package com.example.help;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.help.camera_java_1.Helpme;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Menu2Activity extends AppCompatActivity {

    private FirebaseFirestore food_store = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private long Cur1=0;
    private long sc=0;
    private long c=0;
    private long one=0;
    private long yesterCal=0;
    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    SimpleDateFormat secFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    String year = yearFormat.format(currentTime);
    String month = monthFormat.format(currentTime);
    String day = dayFormat.format(currentTime);
    String sec = secFormat.format(currentTime);
    String DATE = (year + month + day);
    String DATE2 = (month);
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        final Intent intent1 = new Intent(this, Helpme.class);

        ImageButton button1 = findViewById(R.id.menu2_camera);
        ImageButton button2 = findViewById(R.id.menu2_text);
        final EditText input_food = findViewById(R.id.input_food);
        MainActivity.Global.counter=-1;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.Global.counter=MainActivity.Global.cot;
                Log.d("counter", String.valueOf(MainActivity.Global.counter));
                MainActivity.Global.counterplus();
                startActivity(intent1);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view){
                try {
                    MainActivity.Global.counter=-1;
                    final String AA=input_food.getText().toString();
                    if(AA.equals("커피"))
                    {
                        MainActivity.Global.coff=MainActivity.Global.coff+1;
                        Log.d("v1v1v1vv",String.valueOf(MainActivity.Global.coff));
                    }
                    Log.d("dkdkdk", AA);
                    food_store.collection("fooddata")
                            .document(AA)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        final DocumentSnapshot document1 = task.getResult();
                                        if (document1.exists()) {
                                            food_store.collection("user")
                                                    .document(mAuth.getUid())
                                                    .collection("user_info")
                                                    .document("total" + DATE)
                                                    .get()
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                            if (task.isSuccessful()) {
                                                                DocumentSnapshot document = task.getResult();
                                                                if (document.exists()) {
                                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                                    Map<String, Object> data = new HashMap<>();
                                                                    Map<String, Number> Cal = new HashMap<>();
                                                                    data.put("foodname", (input_food.getText().toString()));
                                                                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                                                                    data.put("data", DATE);
                                                                    Cal.put("coffee", MainActivity.Global.coff);
                                                                    Cur1 = (long) document.getData().get("Cal");
                                                                    sc = (long) document1.getData().get("cal");
                                                                    one = (long) document1.getData().get("cal");
                                                                    Log.d("a의 값", String.valueOf(Cur1));
                                                                    MainActivity.Global.Cal = Cur1 + sc;
                                                                    Cal.put("Cal", MainActivity.Global.Cal);
                                                                    Cur1 = (long) document.getData().get("Na");
                                                                    sc = (long) document1.getData().get("na");
                                                                    MainActivity.Global.Na = Cur1 + sc;
                                                                    Cal.put("Na", MainActivity.Global.Na);
                                                                    Cur1 = (long) document.getData().get("Col");
                                                                    sc = (long) document1.getData().get("col");
                                                                    MainActivity.Global.Col = Cur1 + sc;
                                                                    Cal.put("Col", MainActivity.Global.Col);
                                                                    Cur1 = (long) document.getData().get("Sug");
                                                                    sc = (long) document1.getData().get("sug");
                                                                    MainActivity.Global.Sug = Cur1 + sc;
                                                                    Cal.put("Sug", MainActivity.Global.Sug);
                                                                    Cur1 = (long) document.getData().get("Pro");
                                                                    sc = (long) document1.getData().get("pro");
                                                                    MainActivity.Global.Pro = Cur1 + sc;
                                                                    Cal.put("Pro", MainActivity.Global.Pro);
                                                                    Cur1 = (long) document.getData().get("Fat");
                                                                    sc = (long) document1.getData().get("fat");
                                                                    MainActivity.Global.Fat = Cur1 + sc;
                                                                    Cal.put("Fat", MainActivity.Global.Fat);
                                                                    food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(Cal);
                                                                    food_store.collection(FirebaseID.user).document(user.getUid()).collection("food_info").document().set(data, SetOptions.merge());
                                                                    food_store.collection("user")
                                                                            .document(mAuth.getUid())
                                                                            .collection("user_info")
                                                                            .document(DATE2)
                                                                            .get()
                                                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        DocumentSnapshot document3 = task.getResult();
                                                                                        if (document3.exists()) {
                                                                                            FirebaseUser user = mAuth.getCurrentUser();
                                                                                            Map<String, Number> n = new HashMap<>();
                                                                                            c = (long) document3.getData().get("MonthTotal");
                                                                                            Log.d("MOnthe", String.valueOf(c));
                                                                                            Cur1 = one + c;
                                                                                            Log.d("MOnthe", String.valueOf(Cur1));
                                                                                            n.put("MonthTotal", Cur1);
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total"+DATE).set(n, SetOptions.merge());
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                                        }
                                                                                        else{
                                                                                            FirebaseUser user = mAuth.getCurrentUser();
                                                                                            Map<String, Number> n =new HashMap<>();
                                                                                            n.put("MonthTotal", one);
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total"+DATE).set(n, SetOptions.merge());
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                                        }
                                                                                    }
                                                                                }

                                                                            });
                                                                    finish();
                                                                }
                                                                else{

                                                                    java.util.Date dDate = new java.util.Date();
                                                                    long ICurTime = dDate.getTime();
                                                                    dDate=new java.util.Date(ICurTime+(1000*60*60*24*-1));
                                                                    String yesterday = sdf.format(dDate);
                                                                    food_store.collection("user")
                                                                            .document(mAuth.getUid())
                                                                            .collection("user_info")
                                                                            .document("total" + yesterday)
                                                                            .get()
                                                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        DocumentSnapshot document4 = task.getResult();
                                                                                        if (document4.exists()) {
                                                                                            FirebaseUser user = mAuth.getCurrentUser();
                                                                                            Map<String, Number> data = new HashMap<>();
                                                                                            yesterCal = (long) document4.getData().get("Cal");
                                                                                        }
                                                                                        else{
                                                                                            yesterCal=0;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            });
                                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                                    Map<String, Object> data = new HashMap<>();
                                                                    Map<String, Number> Cal = new HashMap<>();
                                                                    data.put("foodname", (input_food.getText().toString()));
                                                                    data.put("data", DATE);
                                                                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                                                                    sc = (long) document1.getData().get("cal");
                                                                    one = (long) document1.getData().get("cal");
                                                                    MainActivity.Global.Cal = sc;
                                                                    Cal.put("Cal", MainActivity.Global.Cal);
                                                                    Cal.put("coffee", MainActivity.Global.coff);
                                                                    sc = (long) document1.getData().get("na");
                                                                    MainActivity.Global.Na = sc;
                                                                    Cal.put("Na", MainActivity.Global.Na);
                                                                    sc = (long) document1.getData().get("col");
                                                                    MainActivity.Global.Col = sc;
                                                                    Cal.put("Col", MainActivity.Global.Col);
                                                                    sc = (long) document1.getData().get("sug");
                                                                    MainActivity.Global.Sug = sc;
                                                                    Cal.put("Sug", MainActivity.Global.Sug);
                                                                    sc = (long) document1.getData().get("pro");
                                                                    MainActivity.Global.Pro = sc;
                                                                    Cal.put("Pro", MainActivity.Global.Pro);
                                                                    sc = (long) document1.getData().get("fat");
                                                                    MainActivity.Global.Fat = sc;
                                                                    Cal.put("Fat", MainActivity.Global.Fat);
                                                                    food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(Cal);
                                                                    food_store.collection(FirebaseID.user).document(user.getUid()).collection("food_info").document().set(data, SetOptions.merge());
                                                                    food_store.collection("user")
                                                                            .document(mAuth.getUid())
                                                                            .collection("user_info")
                                                                            .document(DATE2)
                                                                            .get()
                                                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        DocumentSnapshot document = task.getResult();
                                                                                        if (document.exists()) {
                                                                                            FirebaseUser user = mAuth.getCurrentUser();
                                                                                            Map<String, Number> n = new HashMap<>();
                                                                                            c = (long) document.getData().get("MonthTotal");
                                                                                            Log.d("MOnthe", String.valueOf(c));
                                                                                            Cur1 = one + c;
                                                                                            Log.d("wwww", String.valueOf(yesterCal));
                                                                                            Log.d("MOnthe", String.valueOf(Cur1));
                                                                                            n.put("MonthTotal", Cur1);
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total"+DATE).set(n, SetOptions.merge());
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                                        }
                                                                                        else{
                                                                                            FirebaseUser user = mAuth.getCurrentUser();
                                                                                            Map<String, Number> n =new HashMap<>();
                                                                                            n.put("MonthTotal", one);
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total"+DATE).set(n, SetOptions.merge());
                                                                                            food_store.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                                        }
                                                                                    }
                                                                                }

                                                                            });
                                                                    finish();
                                                                }
                                                            }
                                                            else {
                                                                Toast.makeText(Menu2Activity.this, "음식이름이 정확하지 않습니다 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                    });
                                        }
                                    }
                                }
                            });
                }
                catch (Exception e) {
                    Toast.makeText(Menu2Activity.this, "입력을 해주세요.", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

}