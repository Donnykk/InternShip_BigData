package com.nd.yq.consumer;

import com.nd.yq.common.bean.Consumer;
import com.nd.yq.consumer.bean.YqDataConsumer;

import java.io.IOException;

public class Bootstrap {
    public static void main(String[] args) throws IOException {
        Consumer consumer = new YqDataConsumer();
        consumer.consumer();
        consumer.close();
    }
}
