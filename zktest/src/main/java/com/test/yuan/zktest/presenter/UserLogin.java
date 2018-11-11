package com.test.yuan.zktest.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.test.yuan.zktest.MainActivity;
import com.test.yuan.zktest.bean.User;
import com.test.yuan.zktest.net.Net;

/**
 * date:2018/11/10
 * author:y(123)
 * function:
 */
public class UserLogin {
    String phone;
    String pass;
    private Net mNet;
    String url;

    public UserLogin(MainActivity userLoginInterface) {
        mUserLoginInterface = userLoginInterface;
    }

    public boolean noNull(String phone1,String pass1){
        phone=phone1;
        pass=pass1;
        url = "http://www.xieast.com/api/user/login.php?username="+phone+"&password="+pass+"";
        if (TextUtils.isEmpty(phone1)&&TextUtils.isEmpty(pass1)) {
            return false;
        }
        return true;
    }



    public void commit(){
        mNet = new Net(url);
        System.out.print(url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(mNet.getResquest()){
                    mUserLoginInterface.success();
                }else {
                    mUserLoginInterface.fail();
                }
            }
        }).start();

    }

    public interface UserLoginInterface{
        void success();
        void fail();
    }
    private UserLoginInterface mUserLoginInterface;

   /* public void setUserLoginInterface(UserLoginInterface userLoginInterface) {
        mUserLoginInterface = userLoginInterface;
    }*/
}
