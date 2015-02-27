
package threads;

import buddyconnect.RDCReceiver;
import classlib.PortDetails;
import classlib.UserSession;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread
{
    String ip;
    int port;
    Socket receiver;
    UserSession userObj;
    UserSession user;
    ChatSendThread sendMsg;
    ChatRecThread recMsg;
    FileRecThread fileRec;
    RDCClientSendThread rdcSend;
    RDCClientRecThread rdcRec;
    
    public Receiver()
    {
        
    }
    
    public Receiver(UserSession user)
    {
        userObj = user;
    }
    
    @Override
    public void run()
    {
        try
        {
            // Getting Server IP
            do
            {
                ip = PortDetails.getServerIP();
            }while(ip.equals(""));
            
            System.out.println(ip);
            // Getting Server Port
            do
            {
                port = PortDetails.getUserPort(userObj.getUser(),userObj.getBuddy());
            }while(port == 0);
            
            System.out.println(port);
            // Establishing connection with Server...
            while(true)
            {
                try
                {
                    receiver = new Socket(ip, port);
                    break;
                }
                catch(IOException e)
                {
                }
            }
            
            System.out.println("Connected to server portno : " + port);
            switch(userObj.getOper())
            {
                // Chat
                case 1:
                {
                    recMsg = new ChatRecThread(receiver);
                    recMsg.start();
                    sendMsg = new ChatSendThread(receiver);
                    sendMsg.start();
                    sendMsg.join();
                    recMsg.join();
                    break;
                }
                // RDC
                case 2:
                {
                    rdcRec = new RDCClientRecThread(receiver);
                    rdcSend = new RDCClientSendThread(receiver);
                    rdcRec.start();
                    rdcSend.start();
                    rdcRec.join();
                    rdcSend.join();
                    break;
                }
                // File
                case 3:
                {
                    fileRec = new FileRecThread(receiver);
                    fileRec.start();
                    fileRec.join();
                    break;
                }
                // Audio
                case 4: 
                {
                }
                // Video
                case 5:
                {
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error while creating reciever : " + e.toString());
            e.printStackTrace();
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
                rdcRec.interrupt();
                rdcSend.interrupt();
                break;
            }
            case 3:
            {
                fileRec.interrupt();
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