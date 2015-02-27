
package threads;

import classlib.Loading;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ChatSendThread extends Thread
{
    Socket sender;
    public static String msg;
    
    public ChatSendThread()
    {
        msg = "";
    }
    
    public ChatSendThread(Socket sender)
    {
        this.sender = sender;
        msg = "";
    }
    
    public static void setMsg(String message)
    {
         msg = message;
    }
    
    public static String getMessage()
    {
        String str = msg;
        return str;
    }
    
    @Override
    public void run()
    {
        String msg;
        OutputStreamWriter out;
        try
        {
            out = new OutputStreamWriter(sender.getOutputStream());
        }
        catch(IOException e)
        {
            out = null;
        }
        if(out != null)
        {
            InputStream in = null;
            try
            {
                in = sender.getInputStream();
                int i = in.read();
                Loading.setValue(i);
            }
            catch(IOException e)
            {
            }
            while(!this.isInterrupted())
            {
                msg = getMessage();
                msg = msg.trim();
                if(msg.equals(""))
                {}
                else
                {
                    try
                    {
                        if(msg.equals("BUDDY_CHAT_EXIT"))
                        {
                            out.write(msg);
                            out.flush();
                            out.close();
                            break;
                        }
                        out.write(msg);
                        out.flush();
                        System.out.println("Sent msg = " + msg);
                        setMsg("");
                    }
                    catch(IOException e)
                    {
                        System.out.println("Send thread error : " + e.toString());
                        continue;
                    }
                }
            }
            do
            {
                try
                {
                    out.close();
                    break;
                }
                catch(IOException e)
                {
                }
            }while(true);
            do
            {
                try
                {
                    if(in!= null)
                    {
                        in.close();
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
                catch(IOException e)
                {
                }
            }while(true);
        }
        else
        {
            System.out.println("Error in getting output stream");
        }
    }
}
