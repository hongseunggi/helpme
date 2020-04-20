
package com.example.help;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.help.adapters.FoodAdapter;
import com.example.help.models.food_info;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Menu3Activity<extension> extends AppCompatActivity {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();


    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private RecyclerView mFoodRecyclerView;

    private FoodAdapter mAdapter;
    private List<food_info> mDatas;
    private  SimpleDateFormat timedataformat = new SimpleDateFormat("yyyy.MM.dd HH:mm");


    private long Month1 = 0;
    private long Month2 = 0;
    private long Month3 = 0;
    private long Month4 = 0;
    private long DAYDAY = 0;
    public TextView textView_timestamp;


    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());


    String year = yearFormat.format(currentTime);
    String month = monthFormat.format(currentTime);
    String day = dayFormat.format(currentTime);
    String DATE = (year + month + day);
    String DATE2 = (month + day);
    String DATE3 = (month);
    FirebaseFirestoreSettings.Builder Setting = new FirebaseFirestoreSettings.Builder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);


        final BarChart[] barChart1 = new BarChart[1];
        final BarChart[] barChart3 = new BarChart[1];
        Button Week;
        Button Mon;
        final TextView text1;

        //text1 = (TextView)findViewById(R.id.state_food);
        Week = (Button)findViewById(R.id.week);
        Mon = (Button)findViewById(R.id.mon);


        Week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mStore.collection("user")
                        .document(mAuth.getUid())
                        .collection("user_info")
                        .document("total"+DATE)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()){
                                        Month1 =  (long) document.getData().get("Cal");
                                    }
                                    else{
                                        Month1 = 0;
                                    }
                                }
                            }

                        });

                int v1 = Integer.valueOf(DATE).intValue()-1;
                String str = String.valueOf(v1);

                mStore.collection("user")
                        .document(mAuth.getUid())
                        .collection("user_info")
                        .document("total"+str)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()){
                                        Month2 =  (long) document.getData().get("Cal");
                                    }
                                    else{
                                        Month2 = 0;
                                    }
                                }
                            }

                        });

                int v2 = Integer.valueOf(DATE).intValue()-2;
                String str2 = String.valueOf(v2);

                mStore.collection("user")
                        .document(mAuth.getUid())
                        .collection("user_info")
                        .document("total"+str2)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()){
                                        Month3 =  (long) document.getData().get("Cal");
                                    }
                                    else{
                                        Month3 = 0;
                                    }
                                }
                            }

                        });

                int v3 = Integer.valueOf(DATE).intValue()-3;
                String str3 = String.valueOf(v3);

                mStore.collection("user")
                        .document(mAuth.getUid())
                        .collection("user_info")
                        .document("total"+str3)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()){
                                        Month4 =  (long) document.getData().get("Cal");
                                    }
                                    else{
                                        Month4 =0;
                                    }
                                }
                            }

                        });


                Handler delayHandler = new Handler();
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        barChart1[0] = (BarChart) findViewById(R.id.barChart1);
                        BarDataSet barDataSet1 = new BarDataSet(getData1(), "");
                        barDataSet1.setBarBorderWidth(0.9f);
                        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData barData1 = new BarData(barDataSet1);
                        XAxis xAxis1 = barChart1[0].getXAxis();
                        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
                        final String[] months1 = new String[]{"3일전", "2일전", "1일전", DATE2};
                        IndexAxisValueFormatter formatter1 = new IndexAxisValueFormatter(months1);
                        xAxis1.setGranularity(1f);
                        xAxis1.setValueFormatter(formatter1);
                        barChart1[0].setData(barData1);
                        barChart1[0].setFitBars(true);
                        barChart1[0].animateXY(5000, 5000);
                        barChart1[0].invalidate();
                    }
                }, 3500);

            }
        });


        Mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mStore.collection("user")
                        .document(mAuth.getUid())
                        .collection("user_info")
                        .document(DATE3)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()){
                                        DAYDAY =  (long) document.getData().get("MonthTotal");
                                        Log.d("dkdkdkd", String.valueOf(DAYDAY));
                                    }
                                }
                            }

                        });

                Handler delayHandler = new Handler();
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        barChart3[0] = (BarChart) findViewById(R.id.barChart2);
                        BarDataSet barDataSet3 = new BarDataSet(getData3(), "");
                        barDataSet3.setBarBorderWidth(0.9f);
                        barDataSet3.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData barData3 = new BarData(barDataSet3);
                        XAxis xAxis3 = barChart3[0].getXAxis();
                        xAxis3.setPosition(XAxis.XAxisPosition.BOTTOM);
                        final String[] months3 = new String[]{"6월", "7월", "8월", "9월", "10월", "11월"};
                        IndexAxisValueFormatter formatter3 = new IndexAxisValueFormatter(months3);
                        xAxis3.setGranularity(1f);
                        xAxis3.setValueFormatter(formatter3);
                        barChart3[0].setData(barData3);
                        barChart3[0].setFitBars(true);
                        barChart3[0].animateXY(5000, 5000);
                        barChart3[0].invalidate();
                    }
                },3500);

            }
        });


        mFoodRecyclerView = findViewById(R.id.food_recycleview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.user)
                .document(mAuth.getUid())
                .collection("food_info")
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(queryDocumentSnapshots != null){
                            mDatas.clear();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            for(DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()){
                                Map<String, Object> shot = snap.getData();
                                String food = String.valueOf(shot.get("foodname"));
                                String time = String.valueOf(shot.get("data"));
                                Log.d("ddddd", time);
                                food_info data = new food_info(food, time);
                                mDatas.add(data);
                            }

                            mAdapter = new FoodAdapter(mDatas);
                            mFoodRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });

    }

    private ArrayList getData1(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, Month4));
        entries.add(new BarEntry(1f, Month3));
        entries.add(new BarEntry(2f, Month2));
        entries.add(new BarEntry(3f, Month1));
        return entries;
    }

    private ArrayList getData3(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 0));
        entries.add(new BarEntry(1f, 0));
        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(3f, 0));
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(5f, DAYDAY));
        return entries;
    }


}