package com.wyt.wytlab.dubbo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCenter implements Server {

    private static HashMap<String, Class> serviceRegister=new HashMap<>();
    private static int PORT=0;
    //根据本地计算机性能生成对应容量的线程池
    private static ExecutorService servicePool=
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ServerCenter() {

    }

    public ServerCenter(int port) {
        this.PORT=port;
    }

    @Override
    public void start() {
        ServerSocket server=null;
        try {
            server=new ServerSocket();
            server.bind(new InetSocketAddress(PORT));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while(true) {
            System.out.println("等待客户端连接...");
            Socket socket = null;
            try {
                //服务器等待连接，每当有客户端连接就开启线程执行调用信息处理类
                socket = server.accept();
                servicePool.execute(new ServiceTask(socket));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    @Override
    public void stop() {

        servicePool.shutdown();

    }

    @Override
    public void register(Class service, Class serviceImpl) {

        serviceRegister.put(service.getName(), serviceImpl);

    }

    //具体调用信息处理类，解析客户端发来的调用信息并执行对应的业务方法并相应方法的返回值
    private class ServiceTask implements Runnable{

        private Socket socket=null;

        public ServiceTask() {

        }

        public ServiceTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream ois=null;
            ObjectOutputStream oos=null;
            try {
                System.out.println("客户端已连接");
                ois=new ObjectInputStream(socket.getInputStream());
                //获取客户端发来的接口名
                String className=ois.readUTF();
                //获取客户端发来的方法
                String methodName=ois.readUTF();
                //获取客户端发来的方法参数类型
                Class[] methodTypes=(Class[]) ois.readObject();
                //获取客户端发来的方法参数值
                Object[] args =(Object[]) ois.readObject();
                //从map中找到需要的接口并执行客户端调用的方法
                Class service=serviceRegister.get(className);
                Method method = service.getMethod(methodName,methodTypes);
                Object returns = method.invoke(service.newInstance(), args);

                oos=new ObjectOutputStream(socket.getOutputStream());
                //返回方法执行的结果
                oos.writeObject(returns);

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭资源
                    if(oos!=null)oos.close();
                    if(ois!=null)ois.close();
                    if(socket!=null)socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
