package threads;

import classlib.DatabaseLog;
import classlib.PortDetails;
import classlib.UserSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

public class RDCRecThread extends Thread
{
    Socket sender,rec;
    BufferedImage img;
    UserSession userObj;
    String fileUrl;
    static boolean flag = true;
    
    public RDCRecThread() 
    {
        
    }
    
    public RDCRecThread(Socket sender, Socket rec, UserSession user)
    {
        this.sender = sender;
        this.rec = rec;
        this.userObj = user;
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
        File file;
        Thread t;
        InputStream in;
        OutputStream out;
        ByteArrayOutputStream outBytes;
        try
        {
            sender.getOutputStream().write(0);
            in = rec.getInputStream();
            
            out = sender.getOutputStream();
        }
        catch(IOException e)
        {
            out = null;
            in = null;
        }
        if(in != null)
        {
            System.out.println("Waiting for image...");
            while(!isInterrupted())
            {
                try
                {
                    img = null;
                    img = ImageIO.read(in);
                    if(!getFlag())
                    {
                        break;
                    }
                    if(img == null)
                    {
                        continue; 
                    }
                    fileUrl = "D:\\BuddyConnectFiles\\RDC\\" + userObj.getUser() + "_" + userObj.getBuddy() + "_" + PortDetails.getCurrentDateTimeFile() + ".png";
                    file = new File(fileUrl);
                    outBytes = new ByteArrayOutputStream();
                    //System.out.println("Image received");
                    ImageIO.write(img, "png", outBytes);
                    out.write(outBytes.toByteArray());
                    out.flush();
                    System.out.println("Image sent");
                    ImageIO.write(img, "png", file);
                    t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DatabaseLog.logRDC(userObj.getUser(), userObj.getBuddy(), userObj.getUser() + "_" + userObj.getBuddy() + "_" + PortDetails.getCurrentDateTimeFile() + ".png");
                        }
                    });
                    t.start();
                }
                catch(IOException e)
                {
                    System.out.println("Error : " + e.toString());
                }
            }
        }
    }
}
