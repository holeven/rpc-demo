package com.gk.rpc;

import com.gk.rpc.codec.Decoder;
import com.gk.rpc.codec.Encoder;
import com.gk.rpc.transport.TransportClient;
import com.gk.rpc.utils.ReflectionUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Proxy;


@Data
@NoArgsConstructor
@Slf4j
public class RpcClient {
    private RpcClientConfig clientConfig;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient(RpcClientConfig rpcClientConfig) {
        this.encoder = ReflectionUtils.newInstance(rpcClientConfig.getJsonEncoderClass());
        this.decoder = ReflectionUtils.newInstance(rpcClientConfig.getJsonDecoderClass());
        this.selector = ReflectionUtils.newInstance(rpcClientConfig.getSelectorClass());
        this.clientConfig = rpcClientConfig;

        //初始化客户端连接服务端配置信息
        this.selector.init(
                this.clientConfig.getServers(),
                this.clientConfig.getConnectionCount(),
                this.clientConfig.getTransportClientClass());
    }

    /**
     * 代理调用
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Object getProxy(Class<T> clazz) {
        return Proxy.newProxyInstance(getClass().getClassLoader()
                , new Class[]{clazz}
                , (proxy, method, args) -> {
                    Request request = new Request();
                    request.setServerDesc(ServerDesc.from(clazz, method));
                    request.setParameters(args); // issue
                    Response response = invokeRemote(request);
                    if (response == null || response.getCode() != 0) {
                        throw new IllegalStateException("fail invoke remote server.....");
                    }
                    return response.getData();
                });
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        try {
            client = selector.select();
            byte[] encode = encoder.encode(request);
            InputStream stream = client.write(new ByteArrayInputStream(encode));
            byte[] bytes = IOUtils.readFully(stream, stream.available());
//            System.out.println(new String(bytes));
            return decoder.decode(bytes, Response.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Response(1, e.getMessage(), null);
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
    }
}
