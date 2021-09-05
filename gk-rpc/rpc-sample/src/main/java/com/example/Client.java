package com.example;

import com.gk.rpc.RpcClient;
import com.gk.rpc.RpcClientConfig;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient(new RpcClientConfig());
        CalService calService = (CalService) client.getProxy(CalService.class);
        System.out.println("rpc 调用已注册的服务方法: "+calService.add(1, 2));
    }
}
