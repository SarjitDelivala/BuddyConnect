
package classlib;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Receiver 
{
    String ip,userId,buddyId;
    int port,oper;
    Socket receiver;
    
    public Receiver()
    {
        
    }
    
    public Receiver(String userId,String buddyId)
    {
        this.buddyId = buddyId;
        this.userId = userId;
    }
    
    public void start()
    {
        try
        {
            // Getting Server IP
            do
            {
                ip = PortDetails.getServerIP();
            }while(ip.equals(""));
            
            // Getting Server Port
            do
            {
                port = PortDetails.getUserPort(buddyId,userId);
            }while(port == 0);
            
            // Establishing connection with Server...
            receiver = new Socket(ip, port);
        }
        catch(Exception e)
        {
            
        }
    }
}