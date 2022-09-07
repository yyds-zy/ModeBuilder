package com.tencent.modebuilder.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.modebuilder.BaseActivity;
import com.tencent.modebuilder.R;
import com.tencent.modebuilder.presenter.ModelHandlePresenter;
import com.tencent.modebuilder.util.DoubleClickHelper;
import com.tencent.modebuilder.util.MMkvUtil;
import com.tencent.modebuilder.util.StringUtil;
import com.tencent.modebuilder.util.ToastUtils;
import com.tencent.modebuilder.view.PageActionBar;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/7
 * Describe:模型数据生成器首页
 */
public class MainActivity extends BaseActivity {

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
    public int getLayoutById() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(){
        ClipboardManager cm =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        String prefix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.PREFIX, MMkvUtil.KEY_PREFIX);
        prefixStrArray = StringUtil.getStringArray(prefix);
        String suffix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.SUFFIX, MMkvUtil.KEY_SUFFIX);
        suffixStrArray = StringUtil.getStringArray(suffix);
        String content = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.CONTENT, MMkvUtil.KEY_CONTENT);
        contentStrArray = StringUtil.getStringArray(content);
        if (prefixStrArray == null || suffixStrArray == null || contentStrArray == null) {
            ToastUtils.toast(R.string.data_format_error);
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
            SettingActivity.start(this,SettingActivity.class);
        });
        textView.setOnLongClickListener(view -> {
            ClipData clipData = ClipData.newPlainText(null,textView.getText());
            cm.setPrimaryClip(clipData);
            ToastUtils.toast(R.string.copy_success);
            return true;
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
            ToastUtils.toast(R.string.data_format_error);
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

    @Override
    public void onBackPressed() {
        if (!DoubleClickHelper.isOnDoubleClick()) {
            ToastUtils.toast(R.string.home_exit_hint);
            return;
        }
        moveTaskToBack(false);
    }
}