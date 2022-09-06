package com.tencent.modebuilder.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.modebuilder.R;
import com.tencent.modebuilder.util.ScreenUtils;

public class PageActionBar extends RelativeLayout {
    private Context mContext;
    RelativeLayout mRootView;
    RelativeLayout mBackRL;
    RelativeLayout mRightRL;
    TextView mLeftView;
    TextView mCenterView;
    TextView mRightView;

    public PageActionBar(Context context) {
        this(context,null);
    }

    public PageActionBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PageActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PageActionBar);
        String leftTitle = ta.getString(R.styleable.PageActionBar_left_text_title);
        String rightTitle = ta.getString(R.styleable.PageActionBar_right_text_title);
        String centerTitle = ta.getString(R.styleable.PageActionBar_center_text_title);
        int titleColor = ta.getColor(R.styleable.PageActionBar_center_text_color, Color.parseColor("#333333"));
        int titleSize = ta.getDimensionPixelSize(R.styleable.PageActionBar_center_text_size,17);

        if (leftTitle != null){
            mLeftView.setText(leftTitle);
        }else {
            mLeftView.setText("AX");
        }

//        if (width >0 && height >0){
        ViewGroup.LayoutParams layoutParams = mLeftView.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLeftView.setLayoutParams(layoutParams);
        mLeftView.setPadding(0,0,ScreenUtils.dp2px(mContext,9),0);
//        }

        if (centerTitle != null){
            mCenterView.setVisibility(VISIBLE);
            mCenterView.setGravity(Gravity.CENTER);
            mCenterView.setText(centerTitle);
            mCenterView.setTextSize(titleSize);
            mCenterView.setTextColor(titleColor);
        }else {
            mCenterView.setVisibility(GONE);
        }
        ta.recycle();
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_title,this);
        mRootView = findViewById(R.id.title);
        mBackRL = findViewById(R.id.backRL);
        mRightRL = findViewById(R.id.right_rl);
        mLeftView =findViewById(R.id.tv_current_model);
        mCenterView =findViewById(R.id.center);
        mRightView =findViewById(R.id.right);

        mBackRL.setOnClickListener(view -> {

        });

        mRightView.setOnClickListener(view -> {

        });
        mRightRL.setVisibility(GONE);
        mBackRL.setVisibility(GONE);
    }

    public void setLeftTitle(String text){
        if (!TextUtils.isEmpty(text)) {
            mLeftView.setText(text);
        }
    }

    public void setCenterTitle(String text){
        if (!TextUtils.isEmpty(text)) {
            mCenterView.setText(text);
        }
    }

    public void setActionBarVisibility( boolean visible){
        if (mRootView != null){
            if (visible){
                mRootView.setVisibility(VISIBLE);
            }else {
                mRootView.setVisibility(GONE);
            }
        }
    }

    public TextView getRightView(){
        return mRightView;
    }

    public TextView getLeftView(){
        return mLeftView;
    }

    public RelativeLayout getBackRL() {
        return mBackRL;
    }

    public RelativeLayout getRightRL() {
        return mRightRL;
    }

    public TextView getCenterView() {
        return mCenterView;
    }
}
