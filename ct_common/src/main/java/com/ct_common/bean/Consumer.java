package com.ct_common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @InterfaceName:Consumer
 * @Description:消费者接口
 * @Author:huge823865619
 * @Date:2022/7/3 13:22
 * @Version: 1.0
 */
public interface Consumer extends Closeable {
    //消费数据
    void consumer() throws IOException;
}