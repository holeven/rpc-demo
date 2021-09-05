package com.gk.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 表示网络传输一个端点
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Peer {
    private String ip;
    private int port;
}
