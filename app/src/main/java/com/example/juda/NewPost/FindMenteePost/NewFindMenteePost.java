package com.example.juda.NewPost.FindMenteePost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.juda.R;

public class NewFindMenteePost extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText title_ET, contents_ET;
    private Button confirm_BTN;

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
        confirm_BTN = findViewById(R.id.confirm_BTN_NFMentee);

        setOnClick();
    }

    /**
     *
     */
    public void sendToDatabase() {

    }

    /**
     * 최종 확인 AlertDialog 띄우는 method
     */
    public void showCheckAlert() {
        AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(NewFindMenteePost.this);
        mAlertBuilder.setMessage("등록 하시겠습니까?");
        mAlertBuilder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*
                등록 버튼 로직
                 */
                Log.d("PIGMONGKEY", "confirm");
            }
        });
        mAlertBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*
                취소 버튼 로직
                 */
                Log.d("PIGMONGKEY", "cancel");
            }
        });

        mAlertBuilder.show();
    }

    /**
     * 온클릭 설정 메소드
     */
    public void setOnClick() {
        confirm_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCheckAlert();
            }
        });
    }

    /**
     * 툴바 설정하는 함수
     * @param menu The options menu in which you place your items.
     *
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.only_cancle_menu, menu);
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
            case R.id.cancle:
                finish();
                return true;
        }

        return false;
    }
}