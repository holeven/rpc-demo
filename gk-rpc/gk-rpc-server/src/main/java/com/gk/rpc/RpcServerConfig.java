package com.gk.rpc;

import com.gk.rpc.codec.Decoder;
import com.gk.rpc.codec.Encoder;
import com.gk.rpc.codec.json.JsonDecode;
import com.gk.rpc.codec.json.JsonEncode;
import com.gk.rpc.transport.TransportServer;
import com.gk.rpc.transport.http.HttpTransportServer;
import lombok.Data;

/**
 * server 配置
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportServerClass = HttpTransportServer.class;

    private Class<? extends Encoder> jsonEncoderClass = JsonEncode.class;

    private Class<? extends Decoder> jsonDecoderClass = JsonDecode.class;

    private int port = 3000;

}
