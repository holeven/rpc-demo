package com.gk.rpc.transport;

import com.gk.rpc.Peer;

import java.io.InputStream;

/**
 * 创建连接   发送数据，等待响应，关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data) throws Exception;

    void close();
}
