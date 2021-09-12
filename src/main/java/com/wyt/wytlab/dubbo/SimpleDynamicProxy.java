package com.wyt.wytlab.dubbo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleDynamicProxy {
    public static void main(String[] args)  {
        SimpleInterface simpleInterface = createSimple(new SimpleClass());
        simpleInterface.doThing1();
        simpleInterface.doThing2();
    }

    interface SimpleInterface {
        void doThing1();

        void doThing2();
    }

    public static class SimpleClass implements SimpleInterface {

        @Override
        public void doThing1() {
            System.out.println(111);
        }

        @Override
        public void doThing2() {
            System.out.println(222);
        }
    }

    public static SimpleInterface createSimple(SimpleInterface object) {
        /*最简单的例子，传进来一个继承了SimpleInterface的class的实例，我们拿到*/
        return (SimpleInterface) Proxy.newProxyInstance(
                //这里其实可以用谁的classLoader都行，只要是AppClassLoader就可以，可以用new SimpleClass().getClass().getClassLoader()代替
                object.getClass().getClassLoader(),
                //获取到需要代理的接口，这里可以看出来必须要有接口，没有实现接口的class是不可以动态代理的，因为没有实现接口
                object.getClass().getInterfaces(),
                //重写InvocationHandler，这是动态代理最重要的点，我们使用动态代理其实就是为了在下面做事情
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 千万不可以用method.invoke(proxy, args)，自己调用自己会造成递归调用，无法退出 !!!!!!!
                        System.out.println(444);
                        method.invoke(object, args);
                        return null;
                    }
                });
    }
}
