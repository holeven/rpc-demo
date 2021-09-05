package org.example;

import static org.junit.Assert.assertTrue;

import com.example.imp.CalServiceImp;
import com.gk.rpc.utils.ReflectionUtils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws NoSuchMethodException {
        CalServiceImp cal = new CalServiceImp();
        System.out.println(ReflectionUtils.invoke(cal,cal.getClass().getMethod("add", String.class, String.class),"1","12"));;
    }
}
