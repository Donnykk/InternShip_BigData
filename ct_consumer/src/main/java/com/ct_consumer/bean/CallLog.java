package com.ct_consumer.bean;

import com.ct_common.api.Column;
import com.ct_common.api.RowKey;
import com.ct_common.api.TableRef;

import java.io.Serializable;

/**
 * @ClassName:CallLog
 * @Description:通话日志
 * @Author:huge823865619
 * @Date:2022/7/9 17:40
 * @Version: 1.0
 */
@TableRef("ct:callLog")
public class CallLog implements Serializable {
    @RowKey
    private String rowKey;
    @Column(family = "caller")
    private String call1;
    @Column(family = "caller")
    private String call2;
    @Column(family = "caller")
    private String calltime;
    @Column(family = "caller")
    private String duration;

    @Column(family = "caller")
    private String flag = "1"; //主叫被叫

    public CallLog() {
    }

    public CallLog(String data) {
        String[] values = data.split("\t");
        call1 = values[0];
        call2 = values[1];
        calltime = values[2];
        duration = values[3];
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getCall1() {
        return call1;
    }

    public void setCall1(String call1) {
        this.call1 = call1;
    }

    public String getCall2() {
        return call2;
    }

    public void setCall2(String call2) {
        this.call2 = call2;
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
