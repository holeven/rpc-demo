package com.gk.rpc;

import com.gk.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理类
 */
@Slf4j
public class ServerManage {
    private Map<ServerDesc, ServerInstance> serverInstanceMap;

    public ServerManage() {
        serverInstanceMap = new ConcurrentHashMap<>();
    }

    /**
     * @param interfaceClass 接口
     * @param bean           实现bean
     * @param <T>            对象类型
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method m : publicMethods) {
            ServerInstance serverInstance = new ServerInstance(bean, m);
            ServerDesc from = ServerDesc.from(interfaceClass, m);
            serverInstanceMap.put(from, serverInstance);
            log.info("register server : {}  {}", from.getClazz(), from.getMethod());
        }
    }

    /**
     * 获取已存储请求的实例
     *
     * @param request
     * @return
     */
    public ServerInstance lookup(Request request) {
        ServerDesc desc = request.getServerDesc();
        return serverInstanceMap.get(desc);
    }
}
