
package threads;


import classlib.UserSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ChatSendThread extends Thread
{
    Socket sender,client;
    int oper;
    UserSession userObj;
    LogThread t;
    public ChatSendThread()
    {
    }
    
    public ChatSendThread(Socket sender,Socket client,int oper, UserSession userObj)
    {
        this.client = client;
        this.sender = sender;
        this.oper = oper;
        this.userObj = userObj;
    }
    
    @Override
    public void run()
    {
        OutputStreamWriter clientWriter;
        InputStreamReader sendReader;
        try
        {
            clientWriter = new OutputStreamWriter(client.getOutputStream());
            sendReader = new InputStreamReader(sender.getInputStream());
        }
        catch(IOException e)
        {
            sendReader = null;
            clientWriter = null;
        }
        if(sendReader != null && clientWriter != null)
        {
            OutputStream out;
            try
            {
                out = client.getOutputStream();
                out.write(0);
                System.out.println("Made rec ready");
            }
            catch(IOException e)
            {
                System.out.println(e.toString());
            }
            String msg;
            char[] data;
            
            try
            {
                while(!isInterrupted())
                {
                    data = new char[2000];
                    System.out.println("Reading data from sender...");
                    sendReader.read(data);
                    msg = new String(data);
                    msg = msg.trim();
                    t = new LogThread(msg, userObj.getUser(), userObj.getBuddy(),oper);
                    t.start();
                    System.out.println("From Sender : " + msg);
                    clientWriter.write(msg);
                    clientWriter.flush();
                    if("BUDDY_CHAT_EXIT".equals(msg))
                    {
                        break;
                    }
                }
            }
            catch(IOException e)
            {
                System.out.println(e.toString());
            }
            finally
            {
                while(true)
                {
                    try
                    {
                        clientWriter.close();
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
                        sendReader.close();
                        break;
                    }
                    catch(IOException e)
                    {
                    }
                }
            }
            
        }
        else
        {
            System.out.println("Error in getting output & input stream");
        }
        System.out.println("Thread send completed chat");
    }
}
