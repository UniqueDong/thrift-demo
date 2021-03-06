package zero.thrift.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;

import zero.thrift.face.HelloWorldService.Iface;
import zero.thrift.face.HelloWorldService.Processor;
import zero.thrift.service.HelloworlServiceImpl;

/**
 * 
 * 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求    TThreadPoolServer 服务模型
 * @author unique
 *
 */
public class HelloTThreadPoolServerDemo {  
  
    public static final int SERVER_PORT = 8090;  
      
    public void startServer() {  
        try {  
            System.out.println("HelloWorld TThreadPoolServer start ....");  
   
            TProcessor tprocessor = new Processor<Iface>(  
                    new HelloworlServiceImpl());  
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);  
            //TThreadPoolServer 线程池服务模型  
            Args ttpsArgs = new Args(  
                     serverTransport);  
            ttpsArgs.processor(tprocessor);  
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());   //协议要与客户端一致
            //线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。  
            TServer server = new TThreadPoolServer(ttpsArgs);  
            server.serve();  
        } catch (Exception e) {  
            System.out.println("Server start error!!!");  
            e.printStackTrace();  
        }  
    }  
   
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        HelloTThreadPoolServerDemo server = new HelloTThreadPoolServerDemo();  
        server.startServer();  
    }  
}  