package com.nd.yq.producer;

import com.nd.yq.common.bean.Producer;
import com.nd.yq.producer.bean.YqDataProducer;

import java.io.*;

public class Bootstrap {
    public static void main(String[] args) throws IOException {
        Producer producer = new YqDataProducer(args[0]);
        producer.producer();
        producer.close();
    }
}