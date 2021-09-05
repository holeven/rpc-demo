package com.gk.rpc.codec.json;

import com.alibaba.fastjson.JSON;
import com.gk.rpc.codec.Encoder;

public class JsonEncode implements Encoder {
    @Override
    public byte[] encode(Object o) {
        return JSON.toJSONBytes(o);
    }
}
