package com.test.yuan.zktest.bean;

import java.util.ArrayList;

/**
 * date:2018/11/10
 * author:y(123)
 * function:
 */
public class NewResponse {
    String code;
    ArrayList<ItemBean> data;
    String msg;

    public String getCode() {
        return code;
    }

    public ArrayList<ItemBean> getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
