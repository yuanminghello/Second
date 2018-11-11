package com.test.yuan.zktest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.test.yuan.zktest.adpter.ListViewAdapter;
import com.test.yuan.zktest.bean.ItemBean;
import com.test.yuan.zktest.bean.NewResponse;
import com.test.yuan.zktest.net.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * date:2018/11/10
 * author:y(123)
 * function:
 */
public class ListViewFragment extends Fragment {

    private XListView mXlv;
    private ListViewAdapter mAdapter;
    int page=0;
    NewResponse json;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(),R.layout.layout_listview,null);
        mXlv = view.findViewById(R.id.xListView);

        mAdapter = new ListViewAdapter(getContext());

        mXlv.setAdapter(mAdapter);

        mXlv.setPullRefreshEnable(true);
        mXlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page=1;
                init(page);
            }

            @Override
            public void onLoadMore() {
                init(page);
            }
        });
        init(page);
        return view;
    }

    private void init(final int page) {
        new AsyncTask<String,String,ArrayList<ItemBean>>(){

            @Override
            protected ArrayList<ItemBean> doInBackground(String... strings) {
                HttpUtil util = new HttpUtil(url);
                String work = util.requeatUtil();
                Gson gson = new Gson();

                json = gson.fromJson(work, NewResponse.class);

                return json.getData();
            }

            @Override
            protected void onPostExecute(ArrayList<ItemBean> itemBeans) {
                if(page==1){
                    mAdapter.setData(itemBeans);
                }else {
                    mAdapter.addData(itemBeans);
                }
                comit();

            }
        }.execute(url+page);
    }

    String url="http://www.xieast.com/api/news/news.php?page=1";

    public void comit(){
        page++;
        mXlv.stopLoadMore();
        mXlv.stopRefresh();
    }
}
