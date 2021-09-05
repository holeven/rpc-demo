import com.gk.rpc.Request;
import com.gk.rpc.ServerDesc;
import com.gk.rpc.ServerInstance;
import com.gk.rpc.ServerManage;
import com.gk.rpc.codec.json.JsonEncode;
import com.gk.rpc.utils.ReflectionUtils;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        ServerManage serverManage = new ServerManage();
        serverManage.register(JsonEncode.class,new JsonEncode());
        Method[] publicMethods = ReflectionUtils.getPublicMethods(JsonEncode.class);
        ServerDesc serverDesc = ServerDesc.from(JsonEncode.class,publicMethods[0]);
        Request request = new Request();
        request.setServerDesc(serverDesc);
        ServerInstance lookup = serverManage.lookup(request);
        System.out.println(lookup);
    }
}
