package dp.proxy.imitatejdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author trink
 */
public class LogHandler implements InvocationHandler {

    private Object object;

    public LogHandler(Object object) {
        this.object = object;
    }

    @Override
    public void invoke(Object object, Method method) {
        System.out.println(this.getClass().getName() + "Log start ...");
        try {
            method.invoke(this.object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(this.getClass().getName() + "Log end ...");
    }
}
