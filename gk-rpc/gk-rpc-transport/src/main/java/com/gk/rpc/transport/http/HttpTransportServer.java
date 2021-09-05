package com.gk.rpc.transport.http;

import com.gk.rpc.transport.RequestHandle;
import com.gk.rpc.transport.TransportServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpTransportServer implements TransportServer {

    private RequestHandle requestHandle;

    private Server server;

    @Override
    public void init(int port, RequestHandle requestHandle) {
        this.requestHandle = requestHandle;
        this.server = new Server(port); //init jetty server
        ServletContextHandler ctx = new ServletContextHandler();
        ServletHolder holder = new ServletHolder(new RequestServlet(this.requestHandle));
        ctx.addServlet(holder, "/*");
        server.setHandler(ctx);

    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

class RequestServlet extends HttpServlet {

    private RequestHandle handle;

    public RequestServlet() {

    }

    public RequestServlet(RequestHandle handle) {
        this.handle = handle;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //post请求之后的处理

        ServletInputStream inputStream = req.getInputStream();
        ServletOutputStream outputStream = resp.getOutputStream();
        if (handle != null) {
            handle.onRequest(inputStream, outputStream);
        }
        outputStream.flush();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
