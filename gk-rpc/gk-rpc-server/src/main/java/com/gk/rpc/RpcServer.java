package com.gk.rpc;

import com.gk.rpc.codec.Decoder;
import com.gk.rpc.codec.Encoder;
import com.gk.rpc.transport.TransportServer;
import com.gk.rpc.utils.ReflectionUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Data
@Slf4j
@NoArgsConstructor
public class RpcServer {
    private RpcServerConfig rpcServerConfig;
    private TransportServer transportServer;
    private Encoder encoder;
    private Decoder decoder;
    private ServerManage serverManage;
    private ServerInvoker serverInvoker;

    public RpcServer(RpcServerConfig rpcServerConfig) {
        this.rpcServerConfig = rpcServerConfig;
        this.transportServer = ReflectionUtils.newInstance(rpcServerConfig.getTransportServerClass());
        Response response = new Response();
        this.transportServer.init(rpcServerConfig.getPort(), (receiver, writer) -> {
            try {
                byte[] inBytes = IOUtils.readFully(receiver, receiver.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.debug("get request :{} ", request);
                ServerInstance lookup = serverManage.lookup(request);
                log.debug("server lookup : {}",lookup);
                Object ret = serverInvoker.invoke(lookup, request);
                log.debug("server ret :{}",ret);
                response.setData(ret);
                log.debug("server response : {}",response);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                response.setCode(1);
                response.setData(null);
                response.setMsg("rpc server got error cause class name: "+e.getClass().getName()+",msg: "+e.getMessage());
            }finally {
                try {
                    byte[] out = encoder.encode(response);
                    writer.write(out);
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            }

        });
        this.encoder = ReflectionUtils.newInstance(rpcServerConfig.getJsonEncoderClass());
        this.decoder = ReflectionUtils.newInstance(rpcServerConfig.getJsonDecoderClass());
        this.serverManage = new ServerManage();
        this.serverInvoker = new ServerInvoker();
    }

    /**
     * 注册方式
     *
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        this.serverManage.register(interfaceClass, bean);
    }

    public void start() {
        this.transportServer.start();
    }

    public void stop() {
        this.transportServer.stop();
    }
}
