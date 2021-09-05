package com.gk.rpc.codec.json;

import com.alibaba.fastjson.JSON;
import com.gk.rpc.codec.Decoder;

public class JsonDecode implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
