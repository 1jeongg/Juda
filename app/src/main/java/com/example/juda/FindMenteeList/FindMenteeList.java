package com.example.juda.FindMenteeList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.juda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FindMenteeList extends AppCompatActivity {

    final private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText search_ET;
    private Button search_BTN, filter_BTN;
    private FloatingActionButton newPost_FAB;
    private ListView findMentee_LV;
    private FindMenteePostData dbData[];
    private FindMenteeListAdapter mAdapter = null;

    private SimpleDateFormat format;


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

        format = new SimpleDateFormat("yyyy. MM. dd");

        getMenteePostList();
    }

    /**
     * This method gets data from firebase
     * Get all data under 'MenteePost' and save all at menteePostHashMap
     */
    private void getMenteePostList() {
        db.collection("postProvider")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> temp;
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("PIGMONGKEY", document.getId() + " => " + document.get("date"));

                                Timestamp temp_timestamp = (Timestamp) document.get("date");
                                Date temp_time = new Date(temp_timestamp.getSeconds()*1000);

                                dbData[i++] = new FindMenteePostData(
                                        (String) document.get("author"),
                                        (String) document.get("title"),
                                        (String) document.get("content"),
                                        format.format(temp_time),
                                        (String) document.get("tag1"),
                                        (String) document.get("tag2")
                                );
                            }
                            setMenteePostListAdapter();
                        } else {
                            Log.d("PIGMONGKEY", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    private void setMenteePostListAdapter() {
//        mAdapter = new FindMenteeListAdapter(getApplicationContext(), dbData);
//        findMentee_LV.setAdapter(mAdapter);
    }
}