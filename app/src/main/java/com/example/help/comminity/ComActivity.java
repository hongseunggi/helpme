package com.example.help.comminity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.help.FirebaseID;
import com.example.help.R;
import com.example.help.adapters.PostAdapter;
import com.example.help.models.Post;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private PostAdapter mAdapter;
    private List<Post> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);

        mPostRecyclerView = findViewById(R.id.main_recycleview);


     /*   mDatas.add(new Post(null, "title", "contents"));
        mDatas.add(new Post(null, "title", "contents"));
        mDatas.add(new Post(null, "title", "contents"));*/



        findViewById(R.id.main_post_edit).setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(queryDocumentSnapshots != null){
                            mDatas.clear();
                            for(DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()){
                                Map<String, Object> shot = snap.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.userId));
                                String title = String.valueOf(shot.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Post data = new Post(documentId, title, contents);
                                mDatas.add(data);
                            }

                            mAdapter = new PostAdapter(mDatas);
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, PostActivity.class));

    }
}