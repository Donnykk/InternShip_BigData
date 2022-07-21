package com.nd.entity;

import java.io.Serializable;

public class QueryInfo implements Serializable {
    private String province;
    private String date6;

    public QueryInfo() {
    }

    public QueryInfo(String province, String date6) {
        this.province = province;
        this.date6 = date6;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDate6() {
        return date6;
    }

    public void setDate6(String date6) {
        this.date6 = date6.substring(0,4)+'年'+date6.substring(4,6);
    }

    @Override
    public String toString() {
        return "QueryInfo{" +
                "province='" + province + '\'' +
                ", date6='" + date6 + '\'' +
                '}';
    }
}
