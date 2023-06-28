package com.example.juda.KakaoLogin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juda.MainActivity;
import com.example.juda.R;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class MainLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login);

        Button login_btn = findViewById(R.id.btn_kakao_login);
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken!=null){
                    updateKakaoLoginUi();
                } else {
                    Log.e("tagtag", throwable.getMessage());
                    Log.e("tagtag", throwable.getStackTrace().toString());
                    Log.e("tagtag", "invoke: login fail" );
                }
                return null;
            }
        };
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainLoginActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(MainLoginActivity.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(MainLoginActivity.this, callback);
                }
            }
        });



    }
    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null){
                    Log.d("tagtag", "ID: " + user.getId());

                } else {
                    Log.d("tagtag", "로그인 안됨");
                }
                return null;
            }
        });
    }


}
