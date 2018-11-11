package com.test.yuan.zktest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.yuan.zktest.presenter.CheckPresenter;
import com.test.yuan.zktest.presenter.UserLogin;

public class MainActivity extends AppCompatActivity implements UserLogin.UserLoginInterface, View.OnClickListener {

    private TextView test;
    private EditText phone;
    private TextView test1;
    private EditText password;
    private CheckBox jz_pass;
    private CheckBox zd_login;
    private Button submit;
    private UserLogin mUserLogin;
    private ProgressDialog mDialog;
    private CheckPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mUserLogin = new UserLogin(this);

        jz_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                phoneString = phone.getText().toString().trim();
                String passwordString = password.getText().toString().trim();
                if(!b){
                   zd_login.setChecked(jz_pass.isChecked());
                }
                mPresenter.remember(MainActivity.this,phoneString,passwordString,jz_pass.isChecked(),zd_login.isChecked());
            }
        });
        zd_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                phoneString = phone.getText().toString().trim();
                String passwordString = password.getText().toString().trim();
                jz_pass.setChecked(zd_login.isChecked());
                mPresenter.automatic(MainActivity.this,phoneString,passwordString,jz_pass.isChecked(),zd_login.isChecked());
            }
        });
    }

    private void initView() {
        test = (TextView) findViewById(R.id.test);
        phone = (EditText) findViewById(R.id.phone);
        test1 = (TextView) findViewById(R.id.test1);
        password = (EditText) findViewById(R.id.password);
        jz_pass = (CheckBox) findViewById(R.id.jz_pass);
        zd_login = (CheckBox) findViewById(R.id.zd_login);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(this);

        mDialog = new ProgressDialog(this);

        mPresenter = new CheckPresenter();




    }
    String phoneString;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:

                phoneString = phone.getText().toString().trim();
                String passwordString = password.getText().toString().trim();
                mDialog.show();
                boolean aNull = mUserLogin.noNull(phoneString,passwordString);
                if(aNull){
                   mDialog.dismiss();

                    Object[] objects = mPresenter.IsRemember(MainActivity.this);
                    if((boolean) objects[0]){
                        jz_pass.setChecked((boolean) objects[0]);
                        zd_login.setChecked((boolean) objects[1]);
                        phone.setText((String)objects[2]);
                        password.setText((String)objects[3]);
                    }else if((boolean) objects[1]){
                        mUserLogin.commit();
                    }
                    mUserLogin.commit();

                }else {
                    Toast.makeText(this, "请输入手机号、密码", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    @Override
    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//                intent.putExtra("phone",phoneString);
                startActivity(intent);
            }
        });
    }

    @Override
    public void fail() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //销毁

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserLogin=null;
    }
}
