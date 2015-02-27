/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * @author SJPVAPCPCPP
 */

public class Loading extends JWindow
{
    
     private int X=0;
     private int Y=0;
     static int val = 5;
     JFrame frm = null;
     
     public Loading(JFrame frame)
     {
         frm = frame;
         enableScreen(false);
         val = 5;
     }
     
     public void start()
     {
         setScreen();
         startCheckThread(this);
         System.out.println("start methoid finish");
     }
     
     public void setScreen()
     {
         Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
         int h = ((int)d.height/2);
         int w = ((int)d.width/2);
         setBounds(w,h-100,110,110);
         JLabel lbl = new JLabel(new ImageIcon("collection\\loading.gif"));
         JPanel panel = new JPanel(new FlowLayout());
         panel.setBackground(new Color(51, 51, 51));
         panel.add(lbl);
         add(panel);
         setVisible(true);
     }
     
     public void startCheckThread(final Loading l)
     {
         Thread t = new Thread(new Runnable() {

             @Override
             public void run() {
                    while(true)
                    {
                        if((!l.isVisible()))
                        {
                            l.setVisible(true);
                        }
                        if(getValue()==0)
                        {
                            closeScreen();
                            enableScreen(true);
                            System.out.println(val);
                            break;
                        }
                    }
             }
         });
         t.start();
     }
     
     public static void setValue(int i)
     {
         val = i;
     }
     
     public static int getValue()
     {
         return val;
     }
     
    public void closeScreen()
    {
       this.removeAll();
       this.dispose();
    }
    
    public void enableScreen(boolean status)
    {
        frm.setEnabled(status);
    }
    
    
}

