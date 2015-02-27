
package threads;


import buddyconnectserver.ServerScreen;
import classlib.UserSession;
import classlib.PortDetails;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ConnectThread extends Thread
{
    UserSession userObj;
    Socket senderSocket;
    ServerSocket buddyServer;
    ChatSendThread sendChat;
    ChatRecThread recChat;
    RDCSendThread send;
    RDCRecThread rec;
    FileSendThread sendFile;
    public ConnectThread()
    {
        
    }
    
    public ConnectThread(Socket senderSocket)
    {
        this.senderSocket = senderSocket;
    }
    
    @Override
    public void interrupt()
    {
        while(true)
        {
            try
            {
                buddyServer.close();
                break;
            }
            catch(Exception e)
            {
            }
        }
        while(true)
        {
            try
            {
                senderSocket.close();
                break;
            }
            catch(Exception e)
            {
            }
        }
        
        sendChat.interrupt();
        recChat.interrupt();
        rec.interrupt();
        send.interrupt();
        sendFile.interrupt();
    }
    
    @Override
    public void run()
    {
        ObjectInputStream inObj = null;
        ServerSocket senderServer = null;
        Socket sender = null;
        OutputStream out = null;
        Socket client = null;
        try
        {
            inObj = new ObjectInputStream(senderSocket.getInputStream());
            userObj = (UserSession) inObj.readObject();
            System.out.println("Server is getting ready");
            senderServer = new ServerSocket(0);
            System.out.println("Sender side is ready : " + senderServer.getLocalPort());
            PortDetails.insertPort(userObj.getUser(), userObj.getBuddy(), senderServer.getLocalPort(),userObj.getOper(),"Pending");
            sender = senderServer.accept();
            System.out.println("Waiting for Buddy to connect...");
            out = sender.getOutputStream();
            client = buddySocket();
            if(client==null)
            {
                System.out.println("Client null");
                try
                {
                    out.write(1);
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                }
            }
            else
            {
                out.write(0);
                out.flush();
                switch(userObj.getOper())
                {
                    // Chat
                    case 1:
                    {
                        ServerScreen.setChatUsers();
                        
                        sendChat = new ChatSendThread(sender, client, userObj.getOper(), userObj);
                        recChat = new ChatRecThread(sender, client, userObj.getOper(), userObj);
                        sendChat.start();
                        System.out.println("Send started");
                        recChat.start();
                        System.out.println("Rec started");
                        try
                        {
                            sendChat.join();
                            recChat.join();
                        }
                        catch(InterruptedException e)
                        {
                            // Database queries all...
                        }
                        break;
                    }
                    // RDC
                    case 2:
                    {
                        ServerScreen.setRDCUsers();
                        send = new RDCSendThread(sender, client, userObj);
                        rec = new RDCRecThread(sender, client,userObj);
                        send.start();
                        rec.start();
                        try
                        {
                            send.join();
                            rec.join();
                        }
                        catch(InterruptedException e)
                        {
                            // Database queries all...
                        }
                        break;
                    }
                    // File
                    case 3:
                    {
                        ServerScreen.setFileUsers();
                        sendFile = new FileSendThread(sender, client, userObj.getOper(), userObj);
                        sendFile.start();
                        try
                        {
                            sendFile.join();
                        }
                        catch(InterruptedException e)
                        {
                            // Database queries all...
                        }
                        break;
                    }
                }
            }
        }
        catch(ClassNotFoundException e)
        {
        }        
        catch(IOException e)
        {
            System.out.println("Error in Connect thread : " + e.toString());
        }
        finally
        {
            while(true)
            {
                try
                {
                    if(inObj!=null)
                    {
                        inObj.close();
                    }
                    break;
                }
                catch(IOException e)
                {
                }
            }
            while(true)
            {
                try
                {
                    if(client!=null)
                    {
                        client.close();
                    }
                    break;
                }
                catch(IOException e)
                {
                }
            }
            while(true)
            {
                try
                {
                    if(sender!=null)
                    {
                        sender.close();
                    }
                    break;
                }
                catch(IOException e)
                {
                }
            }while(true)
            {
                try
                {
                    if(senderServer!=null)
                    {
                        senderServer.close();
                    }
                    break;
                }
                catch(IOException e)
                {
                }
            }
            while(true)
            {
                try
                {
                    if(out!=null)
                    {
                        out.close();
                    }
                    break;
                }
                catch(IOException e)
                {
                }
            }
        }
        PortDetails.updateAccess(userObj.getUser(), userObj.getBuddy(), "Yes", "Yes");
        System.out.println("Thread completed successfully : " + userObj.getOper());
    }
    public Socket buddySocket()
    {
        Socket client;
        boolean flag = false;
        try
        {
            buddyServer = new ServerSocket(0);
            System.out.println("Server for client ready");
            PortDetails.insertPort(userObj.getBuddy(), userObj.getUser(), buddyServer.getLocalPort(),userObj.getOper(),"Yes");
            buddyServer.setSoTimeout(180000);
            client = buddyServer.accept();
            flag = true;
            PortDetails.updateAccess(userObj.getUser(), userObj.getBuddy(), "Yes", "No");
        }
        catch(SocketException e)
        {
            client = null;
        }
        catch(IOException e)
        {
            client = null;
        }
        if(!flag)
        {
            PortDetails.updateAccess(userObj.getUser(), userObj.getBuddy(), "No", "Yes");
        }
        return client;
    }
}