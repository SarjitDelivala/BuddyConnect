
package threads;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

public class RDCClientSendThread extends Thread
{
    
    Socket rec;
    static boolean flag = true;
    
    public RDCClientSendThread()
    {
        
    }
    
    public RDCClientSendThread(Socket rec)
    {
        this.rec = rec;
    }
    
    public static boolean getFlag()
    {
        return flag;
    }
    
    public static void setFlag(boolean f)
    {
        flag = f;
    }
    
    public BufferedImage getImage()
    {
        BufferedImage image = null;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        try
        {
            Robot robObj = new Robot();
            image = robObj.createScreenCapture(screenRect);
        }
        catch(Exception e)
        {
            System.out.println("Error in screen shot");
        }
        return image;
    }
    
    @Override
    public void run()
    {
        OutputStream out = null;
        BufferedImage img;
        ByteArrayOutputStream outBytes;
        try
        {
            out = rec.getOutputStream();
            while(!isInterrupted())
            {
                img = null;
                img = getImage();
                System.out.println("Image Captured... ");
                if(!getFlag())
                {
                    break;
                }
                if(img == null)
                {
                    continue;
                }
                outBytes = new ByteArrayOutputStream();
                ImageIO.write(img, "png", outBytes);
                out.write(outBytes.toByteArray());
                out.flush();
                System.out.println("Image Sent from Thread...");
                Thread.sleep(500);
            }
        }
        catch(IOException e)
        {
            System.out.println("IOException in client send thread : " + e.toString());
        }
        catch(InterruptedException e)
        {
            System.out.println("Error in sending Image : " + e.toString());
        }
        finally
        {
            while(true)
            {
                try
                {
                    out.close();
                    break;
                }
                catch(Exception e)
                {
                    
                }
            }
        }
    }
}
