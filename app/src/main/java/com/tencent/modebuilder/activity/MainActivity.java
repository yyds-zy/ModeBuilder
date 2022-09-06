package com.tencent.modebuilder.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.modebuilder.R;
import com.tencent.modebuilder.presenter.ModelHandlePresenter;
import com.tencent.modebuilder.util.MMkvUtil;
import com.tencent.modebuilder.util.StringUtil;
import com.tencent.modebuilder.view.PageActionBar;

/**
 * 模型数据生成器
 */
public class MainActivity extends AppCompatActivity {

    //前缀
    private String[] prefixStrArray;
    //后缀
    private String[] suffixStrArray;
    //主干
    private String[] contentStrArray;
    //模型
    private String[] modelSortArray = {"AX", "AXB", "AXBB", "X", "XA", "XB", "XBB",
            "BX", "BXA","BXB","BXBB"};
    private TextView textView;
    ModelHandlePresenter presenter;
    private PageActionBar mPageActionBar;
    private AlertDialog dialog;
    private TextView mTipTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void initDialog(){
        dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(getString(R.string.mode_choose))
                .setSingleChoiceItems(modelSortArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        textView.setText("");
                        mPageActionBar.setLeftTitle(modelSortArray[which]);
                    }
                }).create();
    }

    private void init(){
        String prefix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.PREFIX, MMkvUtil.KEY_PREFIX);
        prefixStrArray = StringUtil.getStringArray(prefix);
        String suffix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.SUFFIX, MMkvUtil.KEY_SUFFIX);
        suffixStrArray = StringUtil.getStringArray(suffix);
        String content = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.CONTENT, MMkvUtil.KEY_CONTENT);
        contentStrArray = StringUtil.getStringArray(content);
        if (prefixStrArray == null || suffixStrArray == null || contentStrArray == null) {
            Toast.makeText(this,getString(R.string.data_format_error),Toast.LENGTH_SHORT).show();
        } else {
            presenter = new ModelHandlePresenter(prefixStrArray,suffixStrArray,contentStrArray);
        }
        mPageActionBar = findViewById(R.id.page_action_bar);
        mPageActionBar.getBackRL().setVisibility(View.VISIBLE);
        mPageActionBar.getRightRL().setVisibility(View.VISIBLE);
        textView = findViewById(R.id.tv_generate_content);
        mTipTv = findViewById(R.id.tv_main_tip);
        initDialog();
        mPageActionBar.getRightView().setOnClickListener(view -> {
            if (mTipTv.getVisibility() == View.VISIBLE) {
                mTipTv.setVisibility(View.GONE);
            }
            switchMode(mPageActionBar.getLeftView().getText().toString());
        });
        mPageActionBar.getBackRL().setOnClickListener(view -> {
            if (mTipTv.getVisibility() == View.GONE) {
                mTipTv.setVisibility(View.VISIBLE);
            }
            dialog.show();
        });
        mPageActionBar.getCenterView().setOnClickListener(view -> {
            SettingActivity.start(this);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String prefix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.PREFIX, MMkvUtil.KEY_PREFIX);
        prefixStrArray = StringUtil.getStringArray(prefix);
        String suffix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.SUFFIX, MMkvUtil.KEY_SUFFIX);
        suffixStrArray = StringUtil.getStringArray(suffix);
        String content = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.CONTENT, MMkvUtil.KEY_CONTENT);
        contentStrArray = StringUtil.getStringArray(content);
        if (prefixStrArray == null || suffixStrArray == null || contentStrArray == null) {
            Toast.makeText(this,getString(R.string.data_format_error),Toast.LENGTH_SHORT).show();
        } else {
            presenter = new ModelHandlePresenter(prefixStrArray,suffixStrArray,contentStrArray);
        }
    }

    private void switchMode(String mode) {
        if (presenter == null) return;
        switch (mode) {
            case "AX":
                presenter.AX(textView);
                break;
            case "AXB":
                presenter.AXB(textView);
                break;
            case "AXBB":
                presenter.AXBB(textView);
                break;
            case "X":
                presenter.X(textView);
                break;
            case "XA":
                presenter.XA(textView);
                break;
            case "XB":
                presenter.XB(textView);
                break;
            case "XBB":
                presenter.XBB(textView);
                break;
            case "BX":
                presenter.BX(textView);
                break;
            case "BXA":
                presenter.BXA(textView);
                break;
            case "BXB":
                presenter.BXB(textView);
                break;
            case "BXBB":
                presenter.BXBB(textView);
                break;
            default:
                break;
        }
    }
}