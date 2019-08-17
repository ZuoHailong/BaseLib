package com.drumbeat.baselib.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;
/**
 * 为了解决 使用相同layout的fragment间切换，后面的fragment不显示 的问题
 * Created by ZuoHailong on 2019/8/17.
 */
public class CustomFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public CustomFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        if (fragments == null)
            return null;
        return fragments.get(position);
    }

    @Override
    public long getItemId(int position) {
        //用hash值，避免不同fragment使用同一个布局文件导致其他fragment不显示的问题
        //因为itemId是根据布局文件的id确定的
        return fragments.get(position).hashCode();
    }

    @Override
    public int getCount() {
        if (fragments == null)
            return 0;
        return fragments.size();
    }

}
