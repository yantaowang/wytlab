package com.wyt.wytlab.dubbo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress addr) {

        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[] {serviceInterface},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args){

                        Socket socket =null;
                        ObjectInputStream ois=null;
                        ObjectOutputStream oos=null;
                        Object result=null;

                        try {
                            socket=new Socket();
                            socket.connect(addr);

                            oos=new ObjectOutputStream(socket.getOutputStream());
                            //发送需要的接口名
                            oos.writeUTF(serviceInterface.getName());
                            //发送需要的方法名
                            oos.writeUTF(method.getName());
                            //方法参数类型
                            oos.writeObject(method.getParameterTypes());
                            //方法参数
                            oos.writeObject(args);

                            ois=new ObjectInputStream(socket.getInputStream());
                            result=ois.readObject();

                        }catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if(oos!=null)oos.close();
                                if(ois!=null)ois.close();
                                if(socket!=null)socket.close();

                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        return result;
                    }
                });
    }

}
