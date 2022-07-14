package com.ct_consumer;

import com.ct_common.bean.Consumer;
import com.ct_consumer.bean.CallLogConsumer;

import java.io.IOException;

/**
 * @ClassName:Bootstrap
 * @Description:启动消费者
 * @Author:huge823865619
 * @Date:2022/7/3 13:21
 * @Version: 1.0
 */
//使用kafka消费者获取flume采集的数据
//将数据存储到HBase中去
public class BootStrap {
    public static void main(String[] args) throws IOException {
        //创建消费者
        Consumer consumer = new CallLogConsumer();
        //消费数据
        consumer.consumer();
        //关闭资源
        consumer.close();
    }
}
