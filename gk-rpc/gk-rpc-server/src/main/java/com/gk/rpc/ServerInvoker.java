package com.gk.rpc;

import com.gk.rpc.utils.ReflectionUtils;

public class ServerInvoker {

    public Object invoke(ServerInstance instance,Request request){
        return ReflectionUtils.invoke(instance.getTarget(),instance.getMethod(),request.getParameters());
    }
}
