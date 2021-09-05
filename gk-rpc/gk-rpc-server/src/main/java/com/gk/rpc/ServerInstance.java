package com.gk.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;


@Data
@AllArgsConstructor
public class ServerInstance {

    private Object target;
    private Method method;
}
