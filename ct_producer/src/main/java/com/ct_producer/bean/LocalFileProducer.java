package com.ct_producer.bean;

import com.ct_common.bean.DataIn;
import com.ct_common.bean.DataOut;
import com.ct_common.bean.Producer;
import com.ct_common.util.DateUtil;
import com.ct_common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @ClassName:LocalFileProducer
 * @Description:本地数据文件生产者
 * @Author:huge823865619
 * @Date:2022/7/2 21:18
 * @Version: 1.0
 */
public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataOut out;
    private volatile boolean flag = true;//增强进程可见性，进程共享

    @Override
    public void setIn(DataIn in) {
        this.in = in;
    }

    @Override
    public void setOut(DataOut out) {
        this.out = out;
    }

    //生产数据
    @Override
    public void produce() {
        try {
            //读取通讯录数据
            List<Contact> contactList = in.read(Contact.class);
            while (flag) {
                //从通讯录中随机查找2个电话号码（主叫、被叫）
                int call1Index = new Random().nextInt(contactList.size());
                int call2Index = -1;
                Contact call1 = contactList.get(call1Index);
                Contact call2 = null;
                do {
                    call2Index = new Random().nextInt(contactList.size());
                    call2 = contactList.get(call2Index);
                } while (call1.getTell().equals(call2.getTell()));
                //生成随机的通话时间
                String startDate = "20220101000000";
                String endDate = "20230101000000";
                long startTime = DateUtil.parse(startDate, "yyyyMMddHHmmss").getTime();
                long endTime = DateUtil.parse(endDate, "yyyyMMddHHmmss").getTime();
                //通话时间
                long callTime = startTime + (long) ((endTime - startTime) * Math.random());
                //通话时间字符串
                String callTimeString = DateUtil.format(new Date(callTime), "yyyyMMddHHmmss");
                //生成随机的通话时长
                String duration = NumberUtil.format(new Random().nextInt(3600), 4);
                //生成通话记录
                CallLog callLog = new CallLog(call1.getTell(), call2.getTell(), callTimeString, duration);
                System.out.println(callLog);
                //将通话记录刷写到数据文件中
                out.write(callLog);
                //设置每秒2条
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //关闭生产者
    @Override
    public void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }
}
