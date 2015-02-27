
package threads;

import classlib.RDCDetails;
import classlib.UserSession;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RDCServerSendThread extends Thread
{
    Socket sender;
    UserSession user;
    static RDCDetails rdcObj = null;
    
    public RDCServerSendThread()
    {
        
    }
    
    public RDCServerSendThread(Socket sender,UserSession user)
    {
        this.sender = sender;
        this.user = user;
    }
    
    public static RDCDetails getObject()
    {
        return rdcObj;
    }
    
    public static void setObject(RDCDetails rdc)
    {
        rdcObj = rdc;
    }
    
    @Override
    public void run()
    {
        ObjectOutputStream out;
        RDCDetails rdc;
        try
        {
            out  = new ObjectOutputStream(sender.getOutputStream());
        }
        catch(IOException e)
        {
            out = null;
        }
        while(!isInterrupted())
        {
            rdc = getObject();
            if(rdc != null && out != null)
            {
                try
                {
                    out.writeObject(rdc);
                    out.flush();
                    setObject(null);
                    if(rdc.getMouseX() == -5)
                    {
                        RDCServerRecThread.setFlag(false);
                        Thread.sleep(2000);
                        break;
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error in server send : " + e.toString());
                }
            }
        }
    }
}
