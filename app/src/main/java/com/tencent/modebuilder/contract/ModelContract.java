package com.tencent.modebuilder.contract;

import android.widget.TextView;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/7
 * Describe:模型种类接口
 */
public interface ModelContract {

    interface OnHandleModelEventListener {

        void AX(TextView textView);

        void AXB(TextView textView);

        void AXBB(TextView textView);

        void X(TextView textView);

        void XA(TextView textView);

        void XB(TextView textView);

        void XBB(TextView textView);

        void BX(TextView textView);

        void BXA(TextView textView);

        void BXB(TextView textView);

        void BXBB(TextView textView);
    }
}
