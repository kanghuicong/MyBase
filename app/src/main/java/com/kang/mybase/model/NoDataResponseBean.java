package com.kang.mybase.model;

/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public class NoDataResponseBean {
    private String error_code;
    private String error_msg;

    public NoDataResponseBean() {}

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

}
