package com.gk.rpc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {
    /**
     * @param clazz 待创建的类
     * @param <T>   对象参数
     * @return 返回值
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public static Method[] getPublicMethods(Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<Method> publicMethods = new ArrayList<>();
        for (Method declaredMethod : declaredMethods) {
            //修饰符号判断
            if (Modifier.isPublic(declaredMethod.getModifiers())) {
                publicMethods.add(declaredMethod);
            }
        }
        return publicMethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     *
     * @param obj    被调用方法的对象
     * @param method 被调用的方法
     * @param args   方法的参数
     * @return 返回值
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            System.out.println(obj.getClass());
            System.out.println(args[0].getClass());
            System.out.println(obj);
            System.out.println(Arrays.toString(args));
            return method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("IllegalAccessException or InvocationTargetException");
        }
    }
}
