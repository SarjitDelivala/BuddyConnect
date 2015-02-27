package threads;

import classlib.RDCDetails;
import classlib.UserSession;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RDCSendThread extends Thread
{
    Socket sender;
    Socket rec;
    UserSession userObj;
    RDCDetails rdc;
    public RDCSendThread() 
    {
        
    }

    public RDCSendThread(Socket sender, Socket rec, UserSession user)
    {
        this.rec = rec;
        this.sender = sender;
        userObj = user;
    }
    
    @Override
    public void run()
    {
        ObjectInputStream in;
        ObjectOutputStream out;
        try
        {
            in = new ObjectInputStream(sender.getInputStream());
            out = new ObjectOutputStream(rec.getOutputStream());
        }
        catch(IOException e)
        {
            in = null;
            out = null;
        }
        while(true)
        {
            if(in != null && out != null)
            {
                try
                {
                    rdc = (RDCDetails) in.readObject();
                    if(rdc != null)
                    {
                        out.writeObject(rdc);
                        out.flush();
                    }
                    if(rdc.getMouseX()==-5)
                    {
                        RDCRecThread.setFlag(false);
                        try 
                        {
                            Thread.sleep(2000);
                        } 
                        catch (InterruptedException ex) 
                        {
                        }
                        in.close();
                        out.close();
                        break;
                    }
                }
                catch(ClassNotFoundException e)
                {
                    
                }
                catch(IOException e)
                {
                    break;
                }
            }
        }
    }
}
