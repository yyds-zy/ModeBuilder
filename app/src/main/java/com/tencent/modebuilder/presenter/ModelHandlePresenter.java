package com.tencent.modebuilder.presenter;

import android.content.Context;
import android.widget.TextView;
import com.google.gson.Gson;
import com.tencent.modebuilder.MyApplication;
import com.tencent.modebuilder.R;
import com.tencent.modebuilder.contract.ModelContract;
import com.tencent.modebuilder.model.GenerateDataBean;
import com.tencent.modebuilder.util.FileUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ModelHandlePresenter implements ModelContract.OnHandleModelEventListener {

    public static final String TAG = "ModelHandlePresenter";
    private static Date date = new Date();
    private static SimpleDateFormat dateFormatFile = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private List<GenerateDataBean> mGenerateBeanList = new ArrayList<>();
    //前缀
    private String[] prefixStrArray;
    //后缀
    private String[] suffixStrArray;
    //主干
    private String[] contentStrArray;
    private Context mContent;

    private static int jsonLength = 250000;

    public ModelHandlePresenter(String[] prefixStrArray, String[] suffixStrArray, String[] contentStrArray) {
        this.prefixStrArray = prefixStrArray;
        this.suffixStrArray = suffixStrArray;
        this.contentStrArray = contentStrArray;
        mContent = MyApplication.application;
    }

    @Override
    public void AX(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < prefixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                synchronized (this) {
                    position++;
                    sb.append(prefixStrArray[i]).append(contentStrArray[j]);
                    List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                    GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                    entitiesBean.setId(position);
                    entitiesBean.setLabel(mContent.getString(R.string.core_question));
                    entitiesBean.setStart_offset(prefixStrArray[i].length());
                    entitiesBean.setEnd_offset(sb.length());
                    mEntitiesBeanList.add(entitiesBean);

                    GenerateDataBean generateDataBean = new GenerateDataBean();
                    generateDataBean.setId(position);
                    generateDataBean.setText(sb.toString());
                    generateDataBean.setEntities(mEntitiesBeanList);

                    sb.delete(0, sb.length());
                    mGenerateBeanList.add(generateDataBean);
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_AX.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void AXB(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < prefixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                for (int k = 0; k < suffixStrArray.length; k++) {
                    synchronized (this) {
                        position++;
                        sb.append(prefixStrArray[i]).append(contentStrArray[j]).append(suffixStrArray[k]);
                        List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                        GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                        entitiesBean.setId(position);
                        entitiesBean.setLabel(mContent.getString(R.string.core_question));
                        entitiesBean.setStart_offset(prefixStrArray[i].length());
                        entitiesBean.setEnd_offset(sb.length() - suffixStrArray[k].length());
                        mEntitiesBeanList.add(entitiesBean);

                        GenerateDataBean generateDataBean = new GenerateDataBean();
                        generateDataBean.setId(position);
                        generateDataBean.setText(sb.toString());
                        generateDataBean.setEntities(mEntitiesBeanList);

                        sb.delete(0, sb.length());
                        mGenerateBeanList.add(generateDataBean);
                    }
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_AXB.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void AXBB(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < prefixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                for (int k = 0; k < suffixStrArray.length; k++) {
                    synchronized (this) {
                        position++;
                        sb.append(prefixStrArray[i]).append(contentStrArray[j]).append(suffixStrArray[k]).append(suffixStrArray[k]);
                        List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                        GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                        entitiesBean.setId(position);
                        entitiesBean.setLabel(mContent.getString(R.string.core_question));
                        entitiesBean.setStart_offset(prefixStrArray[i].length());
                        entitiesBean.setEnd_offset(sb.length() - suffixStrArray[k].length() * 2);
                        mEntitiesBeanList.add(entitiesBean);

                        GenerateDataBean generateDataBean = new GenerateDataBean();
                        generateDataBean.setId(position);
                        generateDataBean.setText(sb.toString());
                        generateDataBean.setEntities(mEntitiesBeanList);

                        sb.delete(0, sb.length());
                        mGenerateBeanList.add(generateDataBean);
                    }
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_AXBB.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void X(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < contentStrArray.length; j++) {
            synchronized (this) {
                position++;
                sb.append(contentStrArray[j]);
                List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                entitiesBean.setId(position);
                entitiesBean.setLabel(mContent.getString(R.string.core_question));
                entitiesBean.setStart_offset(0);
                entitiesBean.setEnd_offset(sb.length());
                mEntitiesBeanList.add(entitiesBean);

                GenerateDataBean generateDataBean = new GenerateDataBean();
                generateDataBean.setId(position);
                generateDataBean.setText(sb.toString());
                generateDataBean.setEntities(mEntitiesBeanList);

                sb.delete(0, sb.length());
                mGenerateBeanList.add(generateDataBean);
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_X.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void XA(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < prefixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                synchronized (this) {
                    position++;
                    sb.append(contentStrArray[j]).append(prefixStrArray[i]);
                    List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                    GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                    entitiesBean.setId(position);
                    entitiesBean.setLabel(mContent.getString(R.string.core_question));
                    entitiesBean.setStart_offset(0);
                    entitiesBean.setEnd_offset(sb.length() - prefixStrArray[i].length());
                    mEntitiesBeanList.add(entitiesBean);

                    GenerateDataBean generateDataBean = new GenerateDataBean();
                    generateDataBean.setId(position);
                    generateDataBean.setText(sb.toString());
                    generateDataBean.setEntities(mEntitiesBeanList);

                    sb.delete(0, sb.length());
                    mGenerateBeanList.add(generateDataBean);
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_XA.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void XB(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < suffixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                synchronized (this) {
                    position++;
                    sb.append(contentStrArray[j]).append(suffixStrArray[i]);
                    List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                    GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                    entitiesBean.setId(position);
                    entitiesBean.setLabel(mContent.getString(R.string.core_question));
                    entitiesBean.setStart_offset(0);
                    entitiesBean.setEnd_offset(sb.length() - suffixStrArray[i].length());
                    mEntitiesBeanList.add(entitiesBean);

                    GenerateDataBean generateDataBean = new GenerateDataBean();
                    generateDataBean.setId(position);
                    generateDataBean.setText(sb.toString());
                    generateDataBean.setEntities(mEntitiesBeanList);

                    sb.delete(0, sb.length());
                    mGenerateBeanList.add(generateDataBean);
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_XB.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void XBB(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < contentStrArray.length; i++) {
            for (int j = 0; j < suffixStrArray.length; j++) {
                for (int k = 0; k < suffixStrArray.length; k++) {
                    synchronized (this) {
                        position++;
                        sb.append(contentStrArray[i]).append(suffixStrArray[j]).append(suffixStrArray[k]);
                        List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                        GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                        entitiesBean.setId(position);
                        entitiesBean.setLabel(mContent.getString(R.string.core_question));
                        entitiesBean.setStart_offset(0);
                        entitiesBean.setEnd_offset(sb.length() - suffixStrArray[j].length() - suffixStrArray[k].length());
                        mEntitiesBeanList.add(entitiesBean);

                        GenerateDataBean generateDataBean = new GenerateDataBean();
                        generateDataBean.setId(position);
                        generateDataBean.setText(sb.toString());
                        generateDataBean.setEntities(mEntitiesBeanList);

                        sb.delete(0, sb.length());
                        mGenerateBeanList.add(generateDataBean);
                    }
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_XBB.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void BX(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < suffixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                synchronized (this) {
                    position++;
                    sb.append(suffixStrArray[i]).append(contentStrArray[j]);
                    List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                    GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                    entitiesBean.setId(position);
                    entitiesBean.setLabel(mContent.getString(R.string.core_question));
                    entitiesBean.setStart_offset(suffixStrArray[i].length());
                    entitiesBean.setEnd_offset(sb.length());
                    mEntitiesBeanList.add(entitiesBean);

                    GenerateDataBean generateDataBean = new GenerateDataBean();
                    generateDataBean.setId(position);
                    generateDataBean.setText(sb.toString());
                    generateDataBean.setEntities(mEntitiesBeanList);

                    sb.delete(0, sb.length());
                    mGenerateBeanList.add(generateDataBean);
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_BX.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void BXA(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < prefixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                for (int k = 0; k < suffixStrArray.length; k++) {
                    synchronized (this) {
                        position++;
                        sb.append(suffixStrArray[k]).append(contentStrArray[j]).append(prefixStrArray[i]);
                        List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                        GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                        entitiesBean.setId(position);
                        entitiesBean.setLabel(mContent.getString(R.string.core_question));
                        entitiesBean.setStart_offset(suffixStrArray[k].length());
                        entitiesBean.setEnd_offset(sb.length() - prefixStrArray[i].length());
                        mEntitiesBeanList.add(entitiesBean);

                        GenerateDataBean generateDataBean = new GenerateDataBean();
                        generateDataBean.setId(position);
                        generateDataBean.setText(sb.toString());
                        generateDataBean.setEntities(mEntitiesBeanList);

                        sb.delete(0, sb.length());
                        mGenerateBeanList.add(generateDataBean);
                    }
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_BXA.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void BXB(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < suffixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                for (int k = 0; k < suffixStrArray.length; k++) {
                    synchronized (this) {
                        position++;
                        sb.append(suffixStrArray[i]).append(contentStrArray[j]).append(suffixStrArray[k]);
                        List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                        GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                        entitiesBean.setId(position);
                        entitiesBean.setLabel(mContent.getString(R.string.core_question));
                        entitiesBean.setStart_offset(suffixStrArray[i].length());
                        entitiesBean.setEnd_offset(sb.length() - suffixStrArray[k].length());
                        mEntitiesBeanList.add(entitiesBean);

                        GenerateDataBean generateDataBean = new GenerateDataBean();
                        generateDataBean.setId(position);
                        generateDataBean.setText(sb.toString());
                        generateDataBean.setEntities(mEntitiesBeanList);

                        sb.delete(0, sb.length());
                        mGenerateBeanList.add(generateDataBean);
                    }
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_BXB.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }

    @Override
    public void BXBB(TextView textView) {
        int position = -1;
        textView.setText("");
        mGenerateBeanList.clear();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < suffixStrArray.length; i++) {
            for (int j = 0; j < contentStrArray.length; j++) {
                for (int k = 0; k < suffixStrArray.length; k++) {
                    synchronized (this) {
                        position++;
                        sb.append(suffixStrArray[i]).append(contentStrArray[j]).append(suffixStrArray[k]).append(suffixStrArray[k]);
                        List<GenerateDataBean.EntitiesBean> mEntitiesBeanList = new ArrayList<>();
                        GenerateDataBean.EntitiesBean entitiesBean = new GenerateDataBean.EntitiesBean();
                        entitiesBean.setId(position);
                        entitiesBean.setLabel(mContent.getString(R.string.core_question));
                        entitiesBean.setStart_offset(suffixStrArray[i].length());
                        entitiesBean.setEnd_offset(sb.length() - suffixStrArray[k].length() * 2);
                        mEntitiesBeanList.add(entitiesBean);

                        GenerateDataBean generateDataBean = new GenerateDataBean();
                        generateDataBean.setId(position);
                        generateDataBean.setText(sb.toString());
                        generateDataBean.setEntities(mEntitiesBeanList);

                        sb.delete(0, sb.length());
                        mGenerateBeanList.add(generateDataBean);
                    }
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(mGenerateBeanList);
        String fileName = dateFormatFile.format(date) + "_BXBB.json";
        FileUtils.createJsonFile(json, "/" + fileName);
        if (json.length() < jsonLength) {
            textView.setText(json);
        } else {
            textView.setText(mContent.getString(R.string.data_is_bigger_already_change_file)
                    + fileName + "\n" + mContent.getString(R.string.tip));
        }
    }
}
