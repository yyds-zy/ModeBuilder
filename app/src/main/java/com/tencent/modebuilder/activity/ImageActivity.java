package com.tencent.modebuilder.activity;

import android.widget.ImageView;

import com.tencent.modebuilder.BaseActivity;
import com.tencent.modebuilder.R;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/7
 * Describe:模型数据生成器数据说明页
 */
public class ImageActivity extends BaseActivity {

    @Override
    public int getLayoutById() {
        return R.layout.activity_image;
    }

    @Override
    protected void init() {
        ImageView imageView = findViewById(R.id.image_show);
        imageView.setImageDrawable(getDrawable(R.drawable.data_format_explain));
    }
}
