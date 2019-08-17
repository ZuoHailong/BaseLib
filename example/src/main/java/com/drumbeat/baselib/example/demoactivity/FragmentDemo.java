package com.drumbeat.baselib.example.demoactivity;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.drumbeat.baselib.base.fragment.BaseFragment;
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
        customActionBar.setCenterTitleText("Fragment Demo")
                .setRightIconVisiable(true)
                .setRightIconResource(R.mipmap.ic_arrow_down_white);
        ImageView rightIconView = customActionBar.getRightIconView();
        ViewGroup.LayoutParams layoutParams = rightIconView.getLayoutParams();
        layoutParams.width = SizeUtils.dp2px(12);
        layoutParams.height = SizeUtils.dp2px(12);
        rightIconView.setLayoutParams(layoutParams);
        showEmptyView();
    }

    @Override
    public void initData() {

    }
}
