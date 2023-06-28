package com.example.juda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FindMenteeList extends AppCompatActivity {

    EditText search_ET;
    Button search_BTN, filter_BTN;
    FloatingActionButton newPost_FAB;
    ListView findMentee_LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mentee_list);

        init();

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
    }
}