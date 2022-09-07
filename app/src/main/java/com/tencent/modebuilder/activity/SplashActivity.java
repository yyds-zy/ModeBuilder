package com.tencent.modebuilder.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.tencent.modebuilder.BaseActivity;
import com.tencent.modebuilder.R;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/7
 * Describe:
 */
public class SplashActivity extends BaseActivity {

    private LottieAnimationView mLottieView;

    @Override
    public int getLayoutById() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        mLottieView = findViewById(R.id.lav_splash_lottie);
        // 设置动画监听
        mLottieView.addAnimatorListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mLottieView.removeAnimatorListener(this);
                MainActivity.start(SplashActivity.this,MainActivity.class);
                finish();
            }
        });
    }
}
