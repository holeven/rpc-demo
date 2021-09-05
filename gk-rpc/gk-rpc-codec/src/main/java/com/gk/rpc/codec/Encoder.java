package com.gk.rpc.codec;

/**
 * 序列化
 */
public interface Encoder {
     byte[] encode(Object o);
}
