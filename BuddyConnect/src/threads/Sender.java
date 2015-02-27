
package threads;

import classlib.PortDetails;
import classlib.UserSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender extends Thread
{
    String ip,access;
    int port,oper;
    Socket send;
    UserSession user;
    ChatSendThread sendMsg;
    ChatRecThread recMsg;
    RDCServerRecThread recRDC;
    RDCServerSendThread sendRDC;
    FileSendThread sendFile;
    
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
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                send = new Socket(ip, port);
                break;
            }
            catch(IOException e)
            {
            }
        }
        try
        {
            System.out.println("Connected to server : " + ip + " , " + port);
            InputStream in = send.getInputStream();
            int status=0;
            if(status==in.read())
            {
                System.out.println("OPeration : " + user.getOper());
                switch(user.getOper())
                {
                    // Chat 
                    case 1:
                    {
                        sendMsg = new ChatSendThread(send);
                        recMsg = new ChatRecThread(send);
                        sendMsg.start();
                        recMsg.start();
                        System.out.println("Client side thread is started from Sender class");
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
                        sendRDC = new RDCServerSendThread(send,user);
                        recRDC = new RDCServerRecThread(send,user);
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
                        System.out.println("File Name : " + fileUrl);
                        sendFile = new FileSendThread(send);
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
    
    public void stopAll()
    {
        switch(user.getOper())
        {
            case 1:
            {
                sendMsg.interrupt();
                recMsg.interrupt();
                break;
            }
            case 2:
            {
                sendRDC.interrupt();
                recRDC.interrupt();
                break;
            }
            case 3:
            {
                sendFile.interrupt();
                break;
            }
            case 4:
            {
                break;
            }
            case 5:
            {
                break;
            }
        }
    }
}