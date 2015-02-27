package threads;

import buddyconnectserver.Task;
import classlib.UserSession;
import classlib.PortDetails;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class BuddyServer extends Thread
{
    ServerSocket connectServer = null;
    UserSession userObj;
    Socket connectSocket;
    ObjectInputStream in;
    ConnectThread connect;
    int oper;

    @Override
    public void interrupt() {
        super.interrupt();
        do
        {
            try
            {
                connect.interrupt();
                connectServer.close();
                break;
            }
            catch(IOException e)
            {
            }
        }while(true);
    }
    
    
    
    @Override
    public void run()
    {
        Task.setValue(30);
        String ip="";
        try
        {
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException unknownHost)
        {
            //System.out.println("Server Unknown Host Exception : " + unknownHost.toString());
        }
        Task.setValue(50);
        PortDetails.setServerIP(ip);
        Task.setValue(80);
        
        try
        {
            Thread.sleep(1000);
            connectServer = new ServerSocket(5050);
        }
        catch(IOException e)
        {
        }
        catch(InterruptedException e)
        {
        }
        Task.setValue(100); 
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
        }
        System.out.println("Waiting for user...");
        while(!this.isInterrupted())
        {
            try
            {
                connectSocket = connectServer.accept();
            }
            catch(IOException e)
            {
                continue;
            }
            System.out.println("Waiting for another user...");
            connect = new ConnectThread(connectSocket);
            connect.start();
        }
        System.out.println("Exit");
    }
}