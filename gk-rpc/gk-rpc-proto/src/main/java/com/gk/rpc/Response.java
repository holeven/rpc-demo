package com.gk.rpc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Response {

    private int code;
    private String msg;
    private Object data;

    public static Response ok(){
        return new Response().setMsg(null).setCode(0).setData(null);
    }
}
