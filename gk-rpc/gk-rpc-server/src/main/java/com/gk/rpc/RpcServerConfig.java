package com.gk.rpc;

import com.gk.rpc.codec.Decoder;
import com.gk.rpc.codec.Encoder;
import com.gk.rpc.codec.json.JsonDecode;
import com.gk.rpc.codec.json.JsonEncode;
import com.gk.rpc.transport.TransportServer;
import com.gk.rpc.transport.http.HttpTransportServer;
import lombok.Data;

/**
 * server配置类
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportServerClass = HttpTransportServer.class; //走http方式进行通讯

    private Class<? extends Encoder> jsonEncoderClass = JsonEncode.class; //json方式处理数据

    private Class<? extends Decoder> jsonDecoderClass = JsonDecode.class;

    private int port = 3000;

}
