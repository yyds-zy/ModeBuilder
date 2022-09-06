package com.tencent.modebuilder;


import android.app.Application;
import android.text.TextUtils;

import com.tencent.modebuilder.util.MMkvUtil;
import com.tencent.modebuilder.util.StringUtil;

public class MyApplication extends Application {

    public static MyApplication application;
    public static final String default_prefix = "{\"你好小微\", \"小微\", \"小微小微\", \"嗨小微\",\n" +
            "            \"你好小薇\", \"小薇\", \"小薇小薇\", \"嗨小薇\"}";

    public static final String default_suffix = "{\"阿\", \"啊\", \"啦\", \"唉\", \"哎\", \"呢\", \"吧\",\n" +
            "            \"罢\", \"呵\", \"哈\", \"了\", \"哇\", \"呀\", \"吖\",\n" +
            "            \"吗\", \"嘛\", \"哪\", \"哦\", \"噢\", \"喔\", \"哈\",\n" +
            "            \"呐\", \"哩\", \"咧\", \"哟\", \"嘞\", \"耶\", \"噻\",\n" +
            "            \"呗\", \"咯\", \"啰\", \"喽\", \"呕\", \"呦\", \"么\",\n" +
            "            \"麽\", \"嗯\", \"啵\", \"呃\", \"来\", \"唻\", \"不\",\n" +
            "            \"的\"}";

    public static final String default_content = "{\"黑洞是什么\",\"烦恼的英文\",\"李小龙是谁\",\"马华腾是谁\",\n" +
            "            \"任正非是谁\",\"彭德文是谁\",\"刘强东是谁\",\"美国的上任总统\",\n" +
            "            \"五角大楼在哪里\",\"中国海拔最高的城市\",\"中国最高的山脉\",\"罗凯楠是谁\",\n" +
            "            \"郑光祖的代表作品\",\"黄河的发源地\",\"杰克船长的饰演者是谁\",\"宋亚轩是谁\",\n" +
            "            \"爱因斯坦是谁\",\"美国总统是谁\",\"上海的经纬度\",\"日本的国歌\",\n" +
            "            \"中国的国家主席是谁\",\"习近平的生日\",\"欧阳娜娜是谁\",\"我国最大的瀑布\",\n" +
            "            \"五岳是什么\",\"菲尔普斯是谁\",\"王守义是谁\",\"袁隆平是谁\",\n" +
            "            \"中国第二大瀑布\",\"袁咏琳是谁\",\"最长的河流\",\"花泽香菜是谁\",\n" +
            "            \"金城武是谁\",\"杨幂的资料\",\"南京艺术学院的官网\",\"科尔沁夫的话\",\n" +
            "            \"李小璐是谁\",\"贾乃亮是谁\",\"安吉丽娜是谁\",\"美国自由女神像是谁\",\n" +
            "            \"李白的古诗\",\"四大名著的作者\",\"人民币美元汇率\",\"白宇是谁\",\n" +
            "            \"郭麒麟是谁\",\"西红市首富\",\"哈尔滨工业大学\",\"谁是萨特\",\n" +
            "            \"刀马旦是什么\",\"满文军的代表作\"}";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MMkvUtil.getInstance().initMmkv(application);
        String prefix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.PREFIX, MMkvUtil.KEY_PREFIX);
        String suffix = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.SUFFIX, MMkvUtil.KEY_SUFFIX);
        String content = MMkvUtil.getInstance().DecodeStringValue(MMkvUtil.CONTENT, MMkvUtil.KEY_CONTENT);
        if (TextUtils.isEmpty(prefix) || TextUtils.isEmpty(suffix) || TextUtils.isEmpty(content)) {
            MMkvUtil.getInstance().EncodeStringValue(MMkvUtil.PREFIX, MMkvUtil.KEY_PREFIX, default_prefix);
            MMkvUtil.getInstance().EncodeStringValue(MMkvUtil.SUFFIX, MMkvUtil.KEY_SUFFIX, default_suffix);
            MMkvUtil.getInstance().EncodeStringValue(MMkvUtil.CONTENT, MMkvUtil.KEY_CONTENT, default_content);
        }
    }
}
