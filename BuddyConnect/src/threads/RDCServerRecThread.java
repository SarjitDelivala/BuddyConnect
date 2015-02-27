
package threads;

import buddyconnect.RemoteDesktopSender;
import classlib.Loading;
import classlib.UserSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

public class RDCServerRecThread extends Thread
{
    UserSession user;
    Socket sender;
    BufferedImage img;
    static boolean flag = true ;
    
    
    public RDCServerRecThread()
    {
        
    }
    
    public RDCServerRecThread(Socket sender,UserSession user)
    {
        this.sender = sender;
        this.user = user;
    }
    
    public static boolean getFlag()
    {
        return flag;
    }
    
    public static void setFlag(boolean f)
    {
        flag = f;
    }
    
    @Override
    public void run()
    {
        InputStream in;
        try
        {
             in = sender.getInputStream();
             int val = in.read();
             Loading.setValue(val);
        }
        catch(Exception e)
        {
            System.out.println("Error in RDC SERVER REC Thread : " + e.toString());
            in = null;
        }
        while(!isInterrupted())
        {
            try
            {
                if(in == null)
                {
                    System.out.println("Null InputStream");
                    break; 
                }
                img = null;
                //System.out.println("Receiving Image");
                if(!getFlag())
                {
                    break;
                }
                img = ImageIO.read(in);
                if(img == null)
                {
                    continue;
                }
                System.out.println("Received Image");
                RemoteDesktopSender.setImage(img);
            }
            catch(IOException e)
            {
                System.out.println("Error in server rec : " + e.toString());
            }
        }
    }
}