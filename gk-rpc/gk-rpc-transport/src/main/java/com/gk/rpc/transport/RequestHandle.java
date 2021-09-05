package com.gk.rpc.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface RequestHandle {

    void onRequest(InputStream receiver, OutputStream writer);
}
