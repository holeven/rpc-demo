package com.gk.rpc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Request {
    private ServerDesc serverDesc;
    private Object[] parameters;
}
