
package classlib;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import threads.ChatRecThread;
import threads.ChatSendThread;
import threads.FileSendThread;
import threads.RDCServerRecThread;
import threads.RDCServerSendThread;

public class Sender 
{
    String ip,access;
    int port,oper;
    Socket send;
    UserSession user;
    
    public Sender()
    {
        
    }
    public Sender(int oper,String user,String buddy)
    {
        this.user = new UserSession();
        this.user.setOper(oper);
        this.user.setBuddy(buddy);
        this.user.setUser(user);
        this.oper = oper;
    }
    
    public void initiateSession()
    {
        try
        {
            port = 5050;
            do
            {
                ip = PortDetails.getServerIP();
            }while(ip.equals(""));
            
            send = new Socket(ip, port);
            ObjectOutputStream out = new ObjectOutputStream(send.getOutputStream());
            out.writeObject(user);
            Thread.sleep(1000);
            System.out.println("Connected to server....");
            
            do
            {
                port = PortDetails.getUserPort(user.getUser(),user.getBuddy());
            }while(port==0);
        }
        catch(Exception e)
        {
            System.out.println("Error : " + e.getMessage());
        }
    }
    
    public void start()
    {
        try
        {
            send = new Socket(ip, port);
            InputStream in = send.getInputStream();
            while(in.available()<=0)
            {
            }
            int status=0;
            if(status==in.read())
            {
                switch(user.getOper())
                {
                    // Chat 
                    case 1:
                    {
                        ChatSendThread sendMsg = new ChatSendThread(send);
                        ChatRecThread recMsg = new ChatRecThread(send);
                        sendMsg.start();
                        recMsg.start();
                        try
                        {
                            sendMsg.join();
                            recMsg.join();
                        }
                        catch(InterruptedException e)
                        {
                            // Database Queries
                        }
                        break;
                    }
                    // RDC
                    case 2:
                    {
                        RDCServerSendThread sendRDC = new RDCServerSendThread(send,user);
                        RDCServerRecThread recRDC = new RDCServerRecThread(send,user);
                        sendRDC.start();
                        recRDC.start();
                        try
                        {
                            sendRDC.join();
                            recRDC.join();
                        }
                        catch(InterruptedException e)
                        {
                            // Database Queries
                        }
                        break;
                    }
                    // File
                    case 3:
                    {
                        String fileUrl = "";
                        FileSendThread sendFile = new FileSendThread(send);
                        sendFile.start();
                        try
                        {
                            sendFile.join();
                        }
                        catch(InterruptedException e)
                        {
                            // Database Queries
                        }
                        break;
                    }
                    // Audio
                    case 4:
                    {
                        break;
                    }
                    // Video
                    case 5:
                    {
                        break;
                    }
                }
            }
            else
            {
                System.out.println("Buddy didn't connect....");
            }
        }
        catch(Exception e)
        {
            
        }
    }
}