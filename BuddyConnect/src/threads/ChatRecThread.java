
package threads;

import classlib.Loading;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatRecThread extends Thread
{
    Socket receiver;
    public static String msg;
    public ChatRecThread()
    {
        
    }
    public ChatRecThread(Socket receiver)
    {
        this.receiver = receiver;
    }
    
    public static void setMsg(String message)
    {
        msg = message;
    }
    
    public static String getMsg()
    {
        String str = msg;
        return str;
    }
    
    @Override
    public void run()
    {
      String msg;  
        char[] data;
        InputStreamReader in;
        try
        {
            in = new InputStreamReader(receiver.getInputStream());
        }
        catch(IOException e)
        {
            in = null;
        }
        if(in != null)
        {
            InputStream inRply;
            try
            {
                inRply = receiver.getInputStream();
                int i = inRply.read();
                Loading.setValue(i);
            }
            catch(IOException e)
            {
                
            }
            while(!this.isInterrupted())
            {
                try
                {
                    data = new char[2000];
                    in.read(data);
                }
                catch(IOException e)
                {
                    System.out.println("Error in rec thread... " + e.toString());
                    continue;
                }
                msg = new String(data);
                msg = msg.trim();
                setMsg(msg);
                if(msg.equals("BUDDY_CHAT_EXIT"))
                {
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException e)
                    {
                    }
                    break;
                }
            }
            do
            {
                try
                {
                    in.close();
                    break;
                }
                catch(IOException e)
                {
                    continue;
                }
            }while(true);
        }
    }
}
