package com.gk.rpc.transport.http;

import com.gk.rpc.Peer;
import com.gk.rpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTransportClient implements TransportClient {

    public static final String URL_PREFIX = "http://";

    public String url = "";

    public HttpURLConnection connection;

    @Override
    public void connect(Peer peer) {
        this.url = String.format("%s%s%s%s", URL_PREFIX, peer.getIp(), ":", peer.getPort());
//        System.out.println(url);
    }

    @Override
    public InputStream write(InputStream data) throws Exception {
        connection = (HttpURLConnection) new URL(this.url).openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.connect();
        IOUtils.copy(data, connection.getOutputStream());
        if (connection.getResponseCode() == 200) { //成功
            return connection.getInputStream();
        } else {
            return connection.getErrorStream();
        }
    }

    @Override
    public void close() {
        if (connection!=null){
            connection.disconnect();
        }
    }
}
