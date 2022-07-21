package com.nd.yq.common.bean;

import java.io.Closeable;
import java.io.IOException;

public interface Consumer extends Closeable {
    void consumer() throws IOException;
}
