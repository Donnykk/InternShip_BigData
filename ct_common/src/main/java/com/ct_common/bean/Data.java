package com.ct_common.bean;

/**
 * @ClassName:Data
 * @Description:数据对象
 * @Author:huge823865619
 * @Date:2022/7/2 21:09
 * @Version: 1.0
 */
public abstract class Data implements Val {
    public String content;

    @Override
    public void setValue(Object val) {
        content = (String) val;
    }

    @Override
    public String getValue() {
        return content;
    }
}
