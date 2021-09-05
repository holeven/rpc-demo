package com.gk.rpc;

import com.gk.rpc.codec.Decoder;
import com.gk.rpc.codec.Encoder;
import com.gk.rpc.codec.json.JsonDecode;
import com.gk.rpc.codec.json.JsonEncode;
import com.gk.rpc.transport.TransportClient;
import com.gk.rpc.transport.http.HttpTransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> transportClientClass = HttpTransportClient.class;

    private Class<? extends Encoder> jsonEncoderClass = JsonEncode.class;

    private Class<? extends Decoder> jsonDecoderClass = JsonDecode.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectionCount = 1;

    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));

}
