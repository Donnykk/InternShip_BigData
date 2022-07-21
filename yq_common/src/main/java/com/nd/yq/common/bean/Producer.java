package com.nd.yq.common.bean;

import java.io.Closeable;
import java.io.IOException;

public interface Producer extends Closeable {
    void producer() throws IOException;
    void setOutPath(String path);
}
