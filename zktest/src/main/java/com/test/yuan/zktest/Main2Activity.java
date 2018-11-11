package com.test.yuan.zktest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.test.yuan.zktest.adpter.ViewAdapter;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ViewPager view;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initCreate();

    }

    private void initCreate() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new ListViewFragment());
        list.add(new XingFragment());
        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager());
        adapter.setData(list);
        view.setAdapter(adapter);

        tab.setupWithViewPager(view);

        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());

        tab.getTabAt(0).setText("我的数据").setIcon(R.mipmap.ic_launcher);
        tab.getTabAt(1).setText("我的名片").setIcon(R.mipmap.ic_launcher);

    }

    private void initView() {
        view = (ViewPager) findViewById(R.id.view);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
