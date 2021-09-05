package com.gk.rpc.transport;

/**
 * 启动接受端口，启动监听，接受请求，关闭
 */
public interface TransportServer {

    void init(int port, RequestHandle requestHandle);

    void start();

    void stop();
}
