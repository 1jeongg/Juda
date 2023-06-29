package com.example.juda.NewPost.FindMenteePost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.juda.R;

public class NewFindMenteePost extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText title_ET, contents_ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_find_mentee_post);

        Init();
    }

    private void Init() {
        toolbar = findViewById(R.id.basic_tool_bar_NewFindMentee);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title_ET = findViewById(R.id.title_ET_NFMentee);
        contents_ET = findViewById(R.id.contents_ET_NFMentee);
    }

    /**
     * 툴바 설정하는 함수
     * @param menu The options menu in which you place your items.
     *
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.back_post_no_logo, menu);
        return true;
    }

    /**
     * 툴바 항목 클릭 리스너
     * @param item The menu item that was selected.
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.complite:
                finish();
                return true;
        }

        return false;
    }
}