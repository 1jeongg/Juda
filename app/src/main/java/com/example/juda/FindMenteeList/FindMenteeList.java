package com.example.juda.FindMenteeList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.juda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindMenteeList extends AppCompatActivity {

    final private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FindMenteeListAdapter mAdapter = null;
    private EditText search_ET;
    private Button search_BTN, filter_BTN;
    private FloatingActionButton newPost_FAB;
    private ListView findMentee_LV;
    private List<String> menteePostKey = null;
    private List<String> menteePostTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mentee_list);

        Init();
    }

    /**
     * get ID and Connect
     */
    private void Init() {
        search_ET = findViewById(R.id.search_ET_FindMenteeList);
        search_BTN = findViewById(R.id.search_BTN_FindMenteeList);
        filter_BTN = findViewById(R.id.filter_BTN_FindMenteeList);
        newPost_FAB = findViewById(R.id.newPost_FAB_FindMenteeList);
        findMentee_LV = findViewById(R.id.findMentee_LV_FindMenteeList);

        getMenteePostList();

//        show sample data
//        setSampleAdapter();
    }

    /**
     * This method show sample data on ListView
     */
    private void setSampleAdapter() {
        List<String> sampleListData = new ArrayList<>();
        sampleListData.add("post_1");
        sampleListData.add("post_2");
        sampleListData.add("post_3");
        sampleListData.add("post_4");
        sampleListData.add("post_5");
        sampleListData.add("post_6");
        sampleListData.add("post_7");
        sampleListData.add("post_8");
        ArrayAdapter<String> sampleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sampleListData);
        findMentee_LV.setAdapter(sampleAdapter);
    }

    /**
     * This method gets data from firebase
     * Get all data under 'MenteePost' and save all at menteePostHashMap
     */
    private void getMenteePostList() {
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> temp;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("PIGMONGKEY", document.getId() + " => " + document.getData());
                                Log.d("PIGMONGKEY", document.getId() + " => " + document.getData());
                                menteePostTitle.add(document.getId());
                            }
                            setMenteePostListAdapter();
                        } else {
                            Log.d("PIGMONGKEY", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    private void setMenteePostListAdapter() {
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menteePostTitle);
        findMentee_LV.setAdapter(mAdapter);
    }
}