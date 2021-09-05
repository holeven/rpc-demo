package com.gk.rpc;

import com.gk.rpc.transport.TransportClient;
import com.gk.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector {

    private List<TransportClient> clients;

    public RandomTransportSelector(){
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        int max = Math.max(count, 1);
        for (Peer p : peers) {
            for (int i = 0; i < max; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);//保证单例
                client.connect(p);
                clients.add(client);
            }
            log.info("connect server : {}", p);
        }
    }

    @Override
    public synchronized TransportClient select() {
        return clients.remove(new Random().nextInt(clients.size()));
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        clients.forEach(TransportClient::close);
        clients.clear();
    }
}
