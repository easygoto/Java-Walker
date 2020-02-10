package dp.proxy.imitatejdk;

import java.lang.reflect.Method;

/**
 * @author trink
 */
public interface InvocationHandler {

    /**
     * 反射
     * @param object 对象
     * @param method 方法
     */
    void invoke(Object object, Method method);
}
