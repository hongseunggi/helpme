package com.example.help;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.help.adapters.UinfoAdapter;
import com.example.help.comminity.ComActivity;
import com.example.help.models.Uinfo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.help.FirebaseID.user;
//import static class android.os.Build.ID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private RecyclerView mUinfoRecyclerView;
    private UinfoAdapter mAdapter;
    private List<Uinfo> mDatas;
    private long a = 0;
    private long b = 0;
    private int check = 0;
    private long one=0;
    private long c=0;
    private long yesterCal=0;
    private float acal=0;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private String gender;
    TextView textView;
    ImageButton imagebutton;
    TextView Update;
    BarChart barChart;
    TextView cofcup;
    HorizontalBarChart barchart2;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    FirebaseUser user_m = mAuth.getCurrentUser();

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


    //시간 정보
    public static class Global {
        public static long Cal, Fat, Sug, Col, Pro, Na;
        public static int counter = 0;
        public static int cot = 0;
        public static long coff=0;
        public static double Setcal() {
            return Cal;
        }

        public static double Setcol() {
            return Col;
        }

        public static double Setfat() {
            return Fat;
        }

        public static double Setpro() {
            return Pro;
        }

        public static double Setsug() {
            return Sug;
        }

        public static double Setna() {
            return Na;
        }

        public static void counterplus() {
            Global.counter++;
            Global.cot++;
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Intent intent2 = new Intent(this, Menu2Activity.class);
        final Intent intent4 = new Intent(this, ComActivity.class);
        final Intent intent3 = new Intent(this, Menu3Activity.class);
        Update = (TextView) findViewById(R.id.Update);
        imagebutton = findViewById(R.id.coffeebtn);
        mUinfoRecyclerView = findViewById(R.id.uinfo_recycleview);
        mStore.collection("user")
                .document(user_m.getUid())
                .collection("user_info")
                .document("add")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                if (check == 0) {
                                    Map<String, Number> n = new HashMap<>();
                                    gender = document.getData().get("Gender").toString();
                                    if(gender.equals("Man"))
                                    {
                                        acal=2500;
                                    }
                                    else if(gender.equals("Woman"))
                                    {
                                        acal=2000;
                                    }
                                    else acal=0;
                                }
                            }
                        }

                    }

                });
        mStore.collection("user")
                .document(user_m.getUid())
                .collection("user_info")
                .document("total" + DATE)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                if (check == 0) {
                                    Map<String, Number> n = new HashMap<>();
                                    b = (long) document.getData().get("MonthTotal");
                                    n.put("MonthTotal", b);
                                    a = (long) document.getData().get("Cal");
                                    Log.d("a의 값", String.valueOf(a));
                                    MainActivity.Global.Cal = a;
                                    Log.d("Cal1341 :", String.valueOf(MainActivity.Global.Cal));
                                    a = (long) document.getData().get("Na");
                                    MainActivity.Global.Na = a;
                                    a = (long) document.getData().get("Col");
                                    MainActivity.Global.Col = a;
                                    a = (long) document.getData().get("Sug");
                                    MainActivity.Global.Sug = a;
                                    a = (long) document.getData().get("Pro");
                                    MainActivity.Global.Pro = a;
                                    a = (long) document.getData().get("Fat");
                                    MainActivity.Global.Fat = a;
                                    a = (long) document.getData().get("coffee");
                                    Global.coff= a;
                                    cofcup =(TextView)findViewById(R.id.coffeecup);
                                    cofcup.setText(Global.coff+""+"cup");
                                    textView = findViewById(R.id.cf_1);
                                    textView.setText("오늘 커피를 "+Global.coff+"컵 마셨습니다.");
                                    if(Global.coff>3&&Global.coff<5)
                                    {
                                        textView.setText("오늘 커피를 3잔 이상 미셨습니다, 카페인 섭취가 너무 많으니 건강에 유의하세요");
                                    }
                                    if(Global.coff>=5)
                                    {
                                        textView.setText("오늘 커피를 벌써 5잔 이상 마셨습니다 더 이상의 카페인 섭취는 건강에 매우 해롭습니다. 카페인 섭취를 자제하세요!");
                                    }

                                    mStore.collection("user").document(mAuth.getUid()).collection("user_info").document(DATE2).set(n);
                                }
                            }
                        }

                    }

                });

        findViewById(R.id.main_uinfo_edit).setOnClickListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        break;
                    }
                    case R.id.navigation_menu2: {
                        startActivity(intent2);
                        break;
                    }
                    case R.id.navigation_menu3: {
                        startActivity(intent3);
                        break;
                    }
                    case R.id.navigation_menu4: {
                        startActivity(intent4);
                        break;
                    }
                }
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        cofcup =(TextView)findViewById(R.id.coffeecup);
        cofcup.setText(Global.coff+""+"cup");
        mStore.collection(user)
                .document(user_m.getUid())
                .collection("user_info")
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.userId));
                                String name = String.valueOf(shot.get(FirebaseID.name));
                                String height = String.valueOf(shot.get(FirebaseID.height));
                                String weight = String.valueOf(shot.get(FirebaseID.weight));
                                String birthdate = String.valueOf(shot.get(FirebaseID.birthdate));
                                Uinfo data = new Uinfo(documentId, name, height, weight, birthdate);
                                mDatas.add(data);
                            }
                            mAdapter = new UinfoAdapter(mDatas);
                            mUinfoRecyclerView.setAdapter(mAdapter);
                        }

                    }
                });
        mStore.collection("user")
                .document(user_m.getUid())
                .collection("user_info")
                .document("add")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                if (check == 0) {
                                    Map<String, Number> n = new HashMap<>();
                                    gender = document.getData().get("Gender").toString();
                                    if(gender.equals("Man"))
                                    {
                                        acal=2500;
                                    }
                                    else if(gender.equals("Woman"))
                                    {
                                        acal=2000;
                                    }
                                    else acal=0;
                                }
                            }
                        }

                    }

                });
////////////////////////////////////////////
        if (Global.counter == 1) // 쫄병스낵
        {
            Global.counter=0;
            setfood("쫄병스낵");
        }
        if (Global.counter == 2) //사과쿠키
        {
            Global.counter=0;
            setfood("잼있는사과쿠키");
        }
        if (Global.counter == 3) //콜라
        {
            Global.counter=0;
            setfood("펩시콜라(355ml)");
        }
        if (Global.counter == 4) //사과쿠키
        {
            Global.counter=0;
            setfood("잼있는사과쿠키");
        }
        if (Global.counter == 5) // 쫄병스낵
        {
            Global.counter=0;
            setfood("쫄병스낵");
        }
        if (Global.counter == 6) //콜라
        {
            Global.counter=0;
            setfood("펩시콜라(355ml)");
        }
        Handler delayHandler = new Handler();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                barChart = (BarChart) findViewById(R.id.barChart);
                Log.d("Cal :", String.valueOf(Global.Cal));
                BarDataSet barDataSet = new BarDataSet(getData(), "영양성분");
                barDataSet.setBarBorderWidth(0.9f);
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                BarData barData = new BarData(barDataSet);
                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                final String[] months = new String[]{"칼로리", "나트륨(mg)", "당류(g)", "지방(g)", "단백질(g)", "콜레스테롤(mg)"};
                IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
                xAxis.setGranularity(1f);
                xAxis.setValueFormatter(formatter);
                barChart.setData(barData);
                barChart.setFitBars(true);
                barChart.animateXY(5000, 5000);
                barChart.invalidate();
            }
        }, 3500);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                barchart2 = (HorizontalBarChart) findViewById(R.id.chart1);
                Log.d("Cal :", String.valueOf(Global.Cal));
                BarDataSet barDataSet = new BarDataSet(getData2(), "칼로리");
                barDataSet.setBarBorderWidth(0.9f);
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                BarData barData = new BarData(barDataSet);
                XAxis xAxis = barchart2.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                final String[] months = new String[]{"권장 칼로리", "오늘 칼로리"};
                IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
                xAxis.setGranularity(1f);
                xAxis.setValueFormatter(formatter);
                barchart2.setData(barData);
                barchart2.setFitBars(true);
                barchart2.animateXY(5000, 5000);
                barchart2.invalidate();
            }
        }, 3500);

        textView = findViewById(R.id.cf_1);
        textView.setText("오늘 커피를 "+Global.coff+"컵 마셨습니다.");
        if(Global.coff>3&&Global.coff<5)
        {
            textView.setText("오늘 커피를 3잔 이상 미셨습니다, 카페인 섭취가 너무 많으니 건강에 유의하세요");
        }
        if(Global.coff>=5)
        {
            textView.setText("오늘 커피를 벌써 5잔 이상 마셨습니다 더 이상의 카페인 섭취는 건강에 매우 해롭습니다. 카페인 섭취를 자제하세요!");
        }

        //////////////////////////////
    }


    private ArrayList getData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, (float) Global.Setcal()));
        entries.add(new BarEntry(1f, (float) Global.Setna()));
        entries.add(new BarEntry(2f, (float) Global.Setsug()));
        entries.add(new BarEntry(3f, (float) Global.Setfat()));
        entries.add(new BarEntry(4f, (float) Global.Setpro()));
        entries.add(new BarEntry(5f, (float) Global.Setcol()));
        return entries;
    }

    private ArrayList getData2() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, acal));
        entries.add(new BarEntry(1f, (float) Global.Setcal()));
        return entries;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, UinfoActivity.class));
    }
    private void setfood(String fooddsource)
    {
        final String Food1 = fooddsource;
        mStore.collection("fooddata")
                .document(Food1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            final DocumentSnapshot document1 = task.getResult();
                            if (document1.exists()) {
                                mStore.collection("user")
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
                                                        data.put("foodname", Food1);
                                                        data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                                                        data.put("data", DATE);
                                                        Cal.put("coffee", MainActivity.Global.coff);
                                                        a = (long) document.getData().get("Cal");
                                                        b = (long) document1.getData().get("cal");
                                                        one = (long) document1.getData().get("cal");
                                                        Log.d("a의 값", String.valueOf(a));
                                                        MainActivity.Global.Cal = a + b;
                                                        Cal.put("Cal", MainActivity.Global.Cal);
                                                        a = (long) document.getData().get("Na");
                                                        b = (long) document1.getData().get("na");
                                                        MainActivity.Global.Na = a + b;
                                                        Cal.put("Na", MainActivity.Global.Na);
                                                        a = (long) document.getData().get("Col");
                                                        b = (long) document1.getData().get("col");
                                                        MainActivity.Global.Col = a + b;
                                                        Cal.put("Col", MainActivity.Global.Col);
                                                        a = (long) document.getData().get("Sug");
                                                        b = (long) document1.getData().get("sug");
                                                        MainActivity.Global.Sug = a + b;
                                                        Cal.put("Sug", MainActivity.Global.Sug);
                                                        a = (long) document.getData().get("Pro");
                                                        b = (long) document1.getData().get("pro");
                                                        MainActivity.Global.Pro = a + b;
                                                        Cal.put("Pro", MainActivity.Global.Pro);
                                                        a = (long) document.getData().get("Fat");
                                                        b = (long) document1.getData().get("fat");
                                                        MainActivity.Global.Fat = a + b;
                                                        Cal.put("Fat", MainActivity.Global.Fat);
                                                        mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(Cal);
                                                        mStore.collection(FirebaseID.user).document(user.getUid()).collection("food_info").document().set(data, SetOptions.merge());
                                                        mStore.collection("user")
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
                                                                                a = one + c;
                                                                                Log.d("MOnthe", String.valueOf(a));
                                                                                n.put("MonthTotal", a);
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(n, SetOptions.merge());
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                            } else {
                                                                                FirebaseUser user = mAuth.getCurrentUser();
                                                                                Map<String, Number> n = new HashMap<>();
                                                                                n.put("MonthTotal", one);
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(n, SetOptions.merge());
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                            }
                                                                        }
                                                                    }

                                                                });
                                                    } else {
                                                        java.util.Date dDate = new java.util.Date();
                                                        long ICurTime = dDate.getTime();
                                                        dDate = new java.util.Date(ICurTime + (1000 * 60 * 60 * 24 * -1));
                                                        String yesterday = sdf.format(dDate);
                                                        mStore.collection("user")
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
                                                                            } else {
                                                                                yesterCal = 0;
                                                                            }
                                                                        }
                                                                    }
                                                                });
                                                        FirebaseUser user = mAuth.getCurrentUser();
                                                        Map<String, Object> data = new HashMap<>();
                                                        Map<String, Number> Cal = new HashMap<>();
                                                        data.put("foodname", Food1);
                                                        data.put("data", DATE);
                                                        data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                                                        b = (long) document1.getData().get("cal");
                                                        one = (long) document1.getData().get("cal");
                                                        Log.d("a의 값", String.valueOf(a));
                                                        MainActivity.Global.Cal = b;
                                                        Cal.put("Cal", MainActivity.Global.Cal);
                                                        Cal.put("coffee", MainActivity.Global.coff);
                                                        b = (long) document1.getData().get("na");
                                                        MainActivity.Global.Na = b;
                                                        Cal.put("Na", MainActivity.Global.Na);
                                                        b = (long) document1.getData().get("col");
                                                        MainActivity.Global.Col = b;
                                                        Cal.put("Col", MainActivity.Global.Col);
                                                        b = (long) document1.getData().get("sug");
                                                        MainActivity.Global.Sug = b;
                                                        Cal.put("Sug", MainActivity.Global.Sug);
                                                        b = (long) document1.getData().get("pro");
                                                        MainActivity.Global.Pro = b;
                                                        Cal.put("Pro", MainActivity.Global.Pro);
                                                        b = (long) document1.getData().get("fat");
                                                        MainActivity.Global.Fat = b;
                                                        Cal.put("Fat", MainActivity.Global.Fat);
                                                        mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(Cal);
                                                        mStore.collection(FirebaseID.user).document(user.getUid()).collection("food_info").document().set(data, SetOptions.merge());
                                                        mStore.collection("user")
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
                                                                                a = one + c;
                                                                                Log.d("wwww", String.valueOf(yesterCal));
                                                                                Log.d("MOnthe", String.valueOf(a));
                                                                                n.put("MonthTotal", a);
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(n, SetOptions.merge());
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                            } else {
                                                                                FirebaseUser user = mAuth.getCurrentUser();
                                                                                Map<String, Number> n = new HashMap<>();
                                                                                n.put("MonthTotal", one);
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document("total" + DATE).set(n, SetOptions.merge());
                                                                                mStore.collection(FirebaseID.user).document(user.getUid()).collection("user_info").document(DATE2).set(n);
                                                                            }
                                                                        }
                                                                    }

                                                                });
                                                    }

                                                }
                                            }
                                        });

                            }
                        }
                    }
                });
    }

    /*@Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("Calo", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        mStore.collection("user")
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
                                a = (long)document.getData().get("MonthTotal");
                                editor.putLong("DayCal", a);
                                editor.commit();
                            }
                        }
                    }

                });
    }*/
}
