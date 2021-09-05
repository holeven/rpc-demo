package com.gk.rpc;

import com.gk.rpc.transport.TransportClient;

import java.util.List;

/**
 * 选择哪个server仅需连接
 */
public interface TransportSelector {
    /**
     * 等待连接的peer
     *
     * @param peers
     */
    void init(List<Peer> peers,int count, Class<? extends TransportClient> clazz);

    /**
     * 选择连接的peer
     *
     * @return
     */
    TransportClient select();

    /**
     * 释放peer
     *
     * @param client
     */
    void release(TransportClient client);

    /**
     * 关闭连接
     */
    void close();
}
