package com.test.yuan.zktest.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * date:2018/11/10
 * author:y(123)
 * function:
 */
public class ViewAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mList;

    public ViewAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setData(ArrayList<Fragment> list) {

        mList = list;
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }


}
