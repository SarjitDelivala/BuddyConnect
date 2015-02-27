
package threads;

import classlib.RDCDetails;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RDCClientRecThread extends Thread
{
    static RDCDetails rdcObj = null;
    Socket rec;
    
    public RDCClientRecThread()
    {
        
    }
    
    public RDCClientRecThread(Socket rec)
    {
        this.rec = rec;
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
        ObjectInputStream in;
        RDCDetails rdc;
        try
        {
            in = new ObjectInputStream(rec.getInputStream());
            while(!isInterrupted())
            {
                rdc = (RDCDetails) in.readObject();
                if(rdc != null)
                {
                    setObject(rdc);
                }
                if(rdc.getMouseX()==-5)
                {
                    RDCClientSendThread.setFlag(false);
                    Thread.sleep(1000);
                    break;
                }
                //System.out.println("Object Received in Thread...");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in CLient rec : " + e.toString());
        }
    }
}