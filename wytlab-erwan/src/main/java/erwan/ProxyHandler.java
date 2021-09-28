package erwan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler<T> implements InvocationHandler {

    private Class<T> proxyInterface;

    ProxyHandler(Class<T> proxyInterface){
        this.proxyInterface = proxyInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("doSomething")){
            String result = (String) method.invoke(proxyInterface,args);
            System.out.println(result);
            //这里可以得到方法抽象对象来调用真的的查询方法
            System.out.println("selectById调用成功");
        }
        return null;
    }

    public T getProxy(){
        return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(),new Class[]{proxyInterface},this);
    }

    public static void main(String[] args) {
        ProxyHandler<Subject> proxy = new ProxyHandler(Subject.class);
        Subject subject = proxy.getProxy();
        subject.doSomething();
    }
}