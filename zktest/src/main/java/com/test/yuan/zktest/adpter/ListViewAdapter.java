package com.test.yuan.zktest.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.yuan.zktest.R;
import com.test.yuan.zktest.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/11/10
 * author:y(123)
 * function:
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<ItemBean> list;
    private ViewHolder mHolder;

    public ListViewAdapter(Context context) {
        mContext = context;

        list = new ArrayList<>();
    }

    public void setData(List<ItemBean> itemBeans) {
        this.list.clear();
        if(itemBeans!=null){
            this.list.addAll(itemBeans);
        }
        notifyDataSetChanged();
    }

    public void addData(List<ItemBean> itemBeans) {
        if(itemBeans!=null){
            this.list.addAll(itemBeans);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = View.inflate(mContext, R.layout.item_list,null);
            mHolder = new ViewHolder(view);
        }else {
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.BindData(list.get(i));
        return view;
    }

    class ViewHolder{
        ImageView icon1;
        ImageView icon2;
        ImageView icon3;
        TextView title;
        View view;

    public ViewHolder(View view) {
        this.view=view;
        icon1 = view.findViewById(R.id.icon1);
        icon2 = view.findViewById(R.id.icon2);
        icon3 = view.findViewById(R.id.icon3);
        title = view.findViewById(R.id.title);
        view.setTag(this);
    }
    public void BindData(ItemBean data){
        title.setText(data.getTitle());
        ImageLoader.getInstance().displayImage(data.getThumbnail_pic_s(),icon1);
        ImageLoader.getInstance().displayImage(data.getThumbnail_pic_s02(),icon2);
        ImageLoader.getInstance().displayImage(data.getThumbnail_pic_s03(),icon3);
    }
  }

}
