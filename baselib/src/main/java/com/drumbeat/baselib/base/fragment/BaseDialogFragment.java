package com.drumbeat.baselib.base.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.drumbeat.baselib.R;

/**
 * DialogFragment的基类：全屏、背景透明
 * Created by ZuoHailong on 2019/5/9.
 */
public class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.baselib_Dialog_FullScreen);
    }

}
