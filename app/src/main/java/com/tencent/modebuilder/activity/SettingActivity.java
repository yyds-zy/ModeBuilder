package com.tencent.modebuilder.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.modebuilder.R;
import com.tencent.modebuilder.util.MMkvUtil;
import com.tencent.modebuilder.util.StringUtil;

public class SettingActivity extends AppCompatActivity {

    private TextView mSaveTv;
    private EditText mEditPrefix;
    private EditText mEditContent;
    private EditText mEditSuffix;
    private RelativeLayout mShowRL,mClearRL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    private void init(){
        mSaveTv = findViewById(R.id.tv_save_mode);
        mEditPrefix = findViewById(R.id.ed_prefix);
        mEditContent = findViewById(R.id.ed_content);
        mEditSuffix = findViewById(R.id.ed_suffix);
        mShowRL = findViewById(R.id.rl_data_format_explain);
        mClearRL = findViewById(R.id.rl_clear);

        mSaveTv.setOnClickListener(view -> {
            String prefix = mEditPrefix.getText().toString().trim();
            String content = mEditContent.getText().toString().trim();
            String suffix = mEditSuffix.getText().toString().trim();
            if (TextUtils.isEmpty(prefix) || TextUtils.isEmpty(content) || TextUtils.isEmpty(suffix)) {
                Toast.makeText(this,getString(R.string.save_failed),Toast.LENGTH_SHORT).show();
            } else {
                boolean prefixDataFormat = StringUtil.getDataFormat(prefix);
                boolean suffixDataFormat = StringUtil.getDataFormat(suffix);
                boolean contentDataFormat = StringUtil.getDataFormat(content);
                if (prefixDataFormat && suffixDataFormat && contentDataFormat) {
                    MMkvUtil.getInstance().EncodeStringValue(MMkvUtil.PREFIX, MMkvUtil.KEY_PREFIX, prefix);
                    MMkvUtil.getInstance().EncodeStringValue(MMkvUtil.SUFFIX, MMkvUtil.KEY_SUFFIX, suffix);
                    MMkvUtil.getInstance().EncodeStringValue(MMkvUtil.CONTENT, MMkvUtil.KEY_CONTENT, content);
                    Toast.makeText(this,getString(R.string.save_success),Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,getString(R.string.save_failed_format),Toast.LENGTH_SHORT).show();
                }
            }
        });

        mShowRL.setOnClickListener(view -> {
           ImageActivity.start(this);
        });
        mClearRL.setOnClickListener(view -> {
            mEditPrefix.setText("");
            mEditContent.setText("");
            mEditSuffix.setText("");
        });
    }
}
