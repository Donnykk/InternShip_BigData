package com.ct_producer.bean;

import com.ct_common.bean.Data;

/**
 * @ClassName:Contact
 * @Description:联系人
 * @Author:huge823865619
 * @Date:2022/7/2 22:12
 * @Version: 1.0
 */
public class Contact extends Data {
    private String tell;
    private String name;

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object val) {
        content = (String) val;
        String[] split = content.split("\t");
        setTell(split[0]);
        setName(split[1]);
    }

    @Override
    public String toString() {
        return "Contact[" +
                "tell='" + tell + '\'' +
                ", name='" + name + '\'' +
                ']';
    }
}
