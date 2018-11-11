package com.test.yuan.zktest;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * date:2018/11/10
 * author:袁明磊(123)
 * function:
 */
public class XingFragment extends Fragment {
    private TextView tv_xin;
    private ImageView image;
    private Button mBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.layout_zxing, null);
        initView(view);
        //Intent intent = new Intent();
       // String phone = intent.getStringExtra("phone");
        //String textContent = tv_xin.getText().toString();
        String textContent = "admin:13800138000";
        if (TextUtils.isEmpty(textContent)) {
            Toast.makeText(getContext(), "您的输入为空!", Toast.LENGTH_SHORT).show();

        }
        Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        image.setImageBitmap(mBitmap);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });
        return view;
    }

    private void initView(View view) {
        tv_xin = (TextView) view.findViewById(R.id.tv_xin);
        image = (ImageView) view.findViewById(R.id.image);
        mBtn = view.findViewById(R.id.btn);
    }

}
