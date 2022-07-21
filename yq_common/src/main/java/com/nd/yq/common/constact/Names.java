package com.nd.yq.common.constact;

import com.nd.yq.common.bean.Val;

public enum Names implements Val {
    NAMESPACE("yq"),
    TABLE("yq:data"),
    CF_INFO("info"),
    TOPIC("yq"),
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
        return this.name;
    }
}
