package com.tencent.modebuilder.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.modebuilder.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        init();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ImageActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }


    private void init() {
        ImageView imageView = findViewById(R.id.image_show);
        imageView.setImageDrawable(getDrawable(R.drawable.data_format_explain));
    }
}
