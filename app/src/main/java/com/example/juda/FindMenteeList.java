package com.example.juda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FindMenteeList extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private EditText search_ET;
    private Button search_BTN, filter_BTN;
    private FloatingActionButton newPost_FAB;
    private ListView findMentee_LV;
    private HashMap<String, String> menteePostHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mentee_list);

        init();
    }

    /**
     * get ID and Connect
     */
    private void init() {
        search_ET = findViewById(R.id.search_ET_FindMenteeList);
        search_BTN = findViewById(R.id.search_BTN_FindMenteeList);
        filter_BTN = findViewById(R.id.filter_BTN_FindMenteeList);
        newPost_FAB = findViewById(R.id.newPost_FAB_FindMenteeList);
        findMentee_LV = findViewById(R.id.findMentee_LV_FindMenteeList);

        mDatabaseReference = setDatabase();
    }

    /**
     * This method returns FirebaseDatabase.getInstance().getReference()
     * @return DatabaseReference
     */
    private DatabaseReference setDatabase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    /**
     * This method gets data from firebase
     * Get all data under 'MenteePost' and save all at menteePostHashMap
     */
    private void getMenteePostList() {
        Query query = mDatabaseReference.child("MenteePost");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    menteePostHashMap.put(snapshot.getKey(), (String)snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("PIGMONGKEY", "FindMenteeList.java_getMenteePostList() - onCancelled");
            }
        });
    }
}