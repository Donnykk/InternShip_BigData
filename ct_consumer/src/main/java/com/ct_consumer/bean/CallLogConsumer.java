package com.ct_consumer.bean;

import com.ct_common.bean.Consumer;
import com.ct_common.constant.Names;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class CallLogConsumer implements Consumer {
    //消费数据
    @Override
    public void consumer() {
        try {
            //创建配置对象
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
            //获取flume采集的数据
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
            //关注主题
            consumer.subscribe(Collections.singletonList(Names.TOPIC.getValue()));
            //实现HBase存储
            HBaseDao hBaseDao = new HBaseDao();
            hBaseDao.init();
            //消费数据
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    hBaseDao.insertData(record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //释放资源
    @Override
    public void close() throws IOException {

    }
}
