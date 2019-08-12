package com.drumbeat.baselib.example.demoactivity;

import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.drumbeat.baselib.base.BaseFragment;
import com.drumbeat.baselib.base.mvp.IBaseView;
import com.drumbeat.baselib.example.R;

import butterknife.BindView;

/**
 * Created by ZuoHailong on 2019/7/31.
 */
public class FragmentDemo extends BaseFragment implements IBaseView {
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void onEmptyPageClick() {

    }

    @Override
    public void initView() {
        customActionBar.setCenterTitleText("Fragment Demo");
        showEmptyView();
    }

    @Override
    public void initData() {

    }
}
