package zero.thrift.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import zero.thrift.face.HelloWorldService.Iface;
import zero.thrift.face.HelloWorldService.Processor;
import zero.thrift.service.HelloworlServiceImpl;

/**
 * 单线程服务模型，一般用于测试  TSimpleServer服务端 
 * @author unique
 *
 */
public class HelloTSimpleServerDemo {  
  
    public static final int SERVER_PORT = 8090;  
  
    public void startServer() {  
        try {  
            System.out.println("HelloWorld TSimpleServer start ....");  
   
            TProcessor tprocessor = new Processor<Iface>(  
                    new HelloworlServiceImpl());  
            // 简单的单线程服务模型，一般用于测试    
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);  
            Args tArgs = new Args(serverTransport);  
            tArgs.processor(tprocessor);  
            tArgs.protocolFactory(new TBinaryProtocol.Factory());  
            TServer server = new TSimpleServer(tArgs);  
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
        HelloTSimpleServerDemo server = new HelloTSimpleServerDemo();  
        server.startServer();  
    }  
}  