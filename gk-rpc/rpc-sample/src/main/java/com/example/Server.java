package com.example;

import com.example.imp.CalServiceImp;
import com.gk.rpc.RpcServer;
import com.gk.rpc.RpcServerConfig;

/**
 * 服务端测试
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalService.class,new CalServiceImp());
        server.start();
    }
}
