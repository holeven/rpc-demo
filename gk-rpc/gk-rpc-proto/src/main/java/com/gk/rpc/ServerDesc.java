package com.gk.rpc;

import lombok.*;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *  服务类子数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ServerDesc {

    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    /**
     *
     * @param clazz clazz
     * @param method 类方法对象
     * @return 返回类数据信息
     */
    public static ServerDesc from(Class clazz, Method method){
        ServerDesc serverDesc = new ServerDesc();
        serverDesc.setClazz(clazz.getName());
        serverDesc.setMethod(method.getName());
//        serverDesc.setParameterTypes(Arrays.stream(method.getParameterTypes()).map(Class::getName).toArray());
        String[] parameterTypes = new String[method.getParameterTypes().length];
        for(int i =0;i<method.getParameterTypes().length;i++){
            parameterTypes[i] = method.getParameterTypes()[i].getName();
        }
        serverDesc.setParameterTypes(parameterTypes);

        serverDesc.setReturnType(method.getReturnType().getName());
        return serverDesc;
    }
}
