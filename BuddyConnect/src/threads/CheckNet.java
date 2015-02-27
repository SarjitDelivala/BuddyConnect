/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

/**
 *
 * @author SJPVAPCPCPP
 */
import java.awt.Color;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CheckNet extends Thread{
    
    static boolean status;
    JLabel lblInternet;
    
    public CheckNet()
    {
        
    }
    
    public CheckNet(JLabel label)
    {
        lblInternet = label;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            Socket sock = new Socket();
            InetSocketAddress addr = new InetSocketAddress("www.google.com",80);
            try
            {
                sock.connect(addr,60000);
                Thread.sleep(500);
                try {
                lblInternet.setForeground(Color.GREEN);
                lblInternet.setText("Internet is working");
                }catch(Exception e2){}
            }
            catch (Exception e) 
            {   try{
                lblInternet.setForeground(Color.RED);
                lblInternet.setText("Internet is not working");
                JOptionPane.showMessageDialog(null, "Internet is not working");
                System.exit(0);
                }catch(Exception e3){}
            }
            finally
            {
                try
                {
                    sock.close();
                }
                catch (IOException e) {}
            }
        }
    }
}