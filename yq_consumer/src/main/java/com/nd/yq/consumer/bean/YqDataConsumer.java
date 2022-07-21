package com.nd.yq.consumer.bean;

import com.nd.yq.common.bean.Consumer;
import com.nd.yq.common.constact.Names;
import com.nd.yq.consumer.dao.HBaseDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class YqDataConsumer implements Consumer {
    @Override
    public void consumer() throws IOException {
        //创建配置对象
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
        //获取flume数据
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));
        System.out.println("Kafka subscribe success!");
        //向HBase里存数据
        HBaseDao hBaseDao = new HBaseDao();
        hBaseDao.init();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    hBaseDao.insertData(record.value());
                    System.out.println(record.value());
                } catch (ArrayIndexOutOfBoundsException e) {
                    
                }
            }
        }

    }

    @Override
    public void close() throws IOException {

    }
}
