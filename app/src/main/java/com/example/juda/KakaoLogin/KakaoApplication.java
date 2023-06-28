package com.example.juda.KakaoLogin;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        KakaoSdk.init(this, "ccca04a65816891d6f1cc07d9814c091");
    }
}
