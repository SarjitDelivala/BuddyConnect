/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;


import classlib.UserSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author SARJIT
 */
public class ChatRecThread extends Thread
{
    Socket sender,client;
    int oper;
    UserSession userObj;
    LogThread t;
    public ChatRecThread()
    {
    }
    
    public ChatRecThread(Socket sender,Socket client,int oper, UserSession userObj)
    {
        this.client = client;
        this.sender = sender;
        this.oper = oper;
        this.userObj = userObj;
    }
    
    @Override
    public void run()
    {
        OutputStreamWriter sendWriter;
        InputStreamReader clientReader;
        try
        {
            sendWriter = new OutputStreamWriter(sender.getOutputStream());
            clientReader = new InputStreamReader(client.getInputStream());
        }
        catch(IOException e)
        {
            sendWriter = null;
            clientReader = null;
        }
        if(sendWriter != null && clientReader != null)
        {
            OutputStream out;
            try
            {
                out = sender.getOutputStream();
                out.write(0);
                System.out.println("Made sender ready");
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
                    System.out.println("Reading data from rec...");
                    clientReader.read(data);
                    msg = new String(data);
                    msg = msg.trim();
                    t = new LogThread(msg, userObj.getBuddy(), userObj.getUser(),oper);
                    t.start();
                    System.out.println("From Rec : " + msg);
                    sendWriter.write(msg);
                    sendWriter.flush();
                    if("BUDDY_CHAT_EXIT".equals(msg))
                    {
                        break;
                    }
                }
            }
            catch(IOException e)
            {
                System.out.println("Error in Receiving... " + e.toString());
            }
            finally
            {
                while(true)
                {
                    try
                    {
                        sendWriter.close();
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
                        clientReader.close();
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
            System.out.println("Error in getting output stream");
        }
        System.out.println("Thread rec completed chat");
    }
}
