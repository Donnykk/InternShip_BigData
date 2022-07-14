package com.ct_producer;

import com.ct_common.bean.Producer;
import com.ct_producer.bean.LocalFileProducer;
import com.ct_producer.io.LocalFileDataIn;
import com.ct_producer.io.LocalFileDataOut;

import java.io.IOException;

/**
 * @ClassName:Bootstrap
 * @Description:启动对象
 * @Author:huge823865619
 * @Date:2022/7/2 21:28
 * @Version: 1.0
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            System.out.println("系统参数不正确");
            System.exit(1);
        }
        //构建生产者对象
        Producer producer = new LocalFileProducer();
        //producer.setIn(new LocalFileDataIn("F:\\Environment\\ct_project\\contact.log"));
        //producer.setOut(new LocalFileDataOut("F:\\Environment\\ct_project\\call.log"));
        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));
        //生产数据
        producer.produce();
        //关闭生产者对象
        producer.close();
    }
}