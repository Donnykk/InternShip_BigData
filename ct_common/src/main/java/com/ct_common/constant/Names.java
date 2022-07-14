package com.ct_common.constant;

import com.ct_common.bean.Val;

//名称常量枚举类
public enum Names implements Val {
    NAMESPACE("ct"),
    TABLE("ct:call_log"),
    CF_CALLER("caller"),
    CF_CALLEE("callee"),
    CF_INFO("info"),
    TOPIC("ct"),
    ;
    private String name;

    private Names(String name) {
        this.name = name;
    }


    @Override
    public void setValue(Object val) {
        this.name = (String) val;
    }

    @Override
    public String getValue() {
        return name;
    }
}