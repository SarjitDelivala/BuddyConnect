/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyconnect;

import classlib.RDCDetails;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import threads.RDCClientRecThread;

/**
 *
 * @author SJPVAPCPCPP
 */
public class RDCReceiver extends JWindow 
{
    static JLabel lblInfo;
    String userId;
    Thread t;
    
    public RDCReceiver(String userId)
    {
        lblInfo = new JLabel();
        this.userId = userId;
        lblInfo.setText("Buddy is accessing your Desktop");
    }
    
    public void start(final RDCReceiver receiever)
    {
        setScreen();
        t = new Thread(new Runnable() {
            RDCDetails rdc;
            Robot rob;
            @Override
            public void run()
            {
                try
                {
                    while(true)
                    {
                        rdc = RDCClientRecThread.getObject();
                        if(rdc == null)
                        {
                            continue;
                        }
                        RDCClientRecThread.setObject(null);
                        if(rdc.getMouseX()==-5)
                        {
                            // Close Function of Window
                            receiever.removeAll();
                            receiever.dispose();
                            Home h = new Home(userId);
                            h.start();
                            break;
                        }
                        //System.out.println("Object received");
                        
                        rob = new Robot();
                        if(rdc.getMouseX()>0 && rdc.getMouseY()>0)
                        {
                            //System.out.println("Mouse moved..." + rdc.getMouseX() + rdc.getMouseY());
                            rob.mouseMove(rdc.getMouseX(), rdc.getMouseY());
                        }
                        if(rdc.getKeyCode()>0)
                        {
                            System.out.println("In Key Event : Key Code : " + rdc.getKeyCode());
                            if(rdc.getShift())
                            {
                                
                                rob.keyPress(KeyEvent.VK_SHIFT);
                                rob.keyPress(rdc.getKeyCode());
                                Thread.sleep(100);
                                rob.keyRelease(KeyEvent.VK_SHIFT);
                                rob.keyRelease(rdc.getKeyCode());
                            }
                            else if(rdc.getCtrl())
                            {
                                rob.keyPress(KeyEvent.VK_CONTROL);
                                rob.keyPress(rdc.getKeyCode());
                                Thread.sleep(100);
                                rob.keyRelease(KeyEvent.VK_CONTROL);
                                rob.keyRelease(rdc.getKeyCode());
                            }
                            else if(rdc.getAlt())
                            {
                                rob.keyPress(KeyEvent.VK_ALT);
                                rob.keyPress(rdc.getKeyCode());
                                Thread.sleep(100);
                                rob.keyRelease(KeyEvent.VK_ALT);
                                rob.keyRelease(rdc.getKeyCode());
                            }
                            else if(rdc.getAlt() && rdc.getCtrl())
                            {
                                rob.keyPress(KeyEvent.VK_CONTROL);
                                rob.keyPress(KeyEvent.VK_ALT);
                                rob.keyPress(rdc.getKeyCode());
                                Thread.sleep(100);
                                rob.keyRelease(KeyEvent.VK_CONTROL);
                                rob.keyRelease(KeyEvent.VK_ALT);
                                rob.keyRelease(rdc.getKeyCode());
                            }
                            else if(rdc.getShift() && rdc.getCtrl())
                            {
                                rob.keyPress(KeyEvent.VK_CONTROL);
                                rob.keyPress(KeyEvent.VK_SHIFT);
                                rob.keyPress(rdc.getKeyCode());
                                Thread.sleep(100);
                                rob.keyRelease(KeyEvent.VK_CONTROL);
                                rob.keyRelease(KeyEvent.VK_SHIFT);
                                rob.keyRelease(rdc.getKeyCode());
                            }
                            else
                            {
                                rob.keyPress(rdc.getKeyCode());
                                Thread.sleep(100);
                                rob.keyRelease(rdc.getKeyCode());
                            }
                        }
                        if(rdc.getMouseClick()>0)
                        {
                            System.out.println("In Mouse Event");
                            switch(rdc.getMouseOper())
                            {
                                case MouseEvent.MOUSE_CLICKED:
                                {
                                    System.out.println("Mouse Oper : " + rdc.getMouseOper());
                                    for(int i=1;i<=rdc.getMouseClick();i++)
                                    {
                                        rob.mousePress(rdc.getMouseBtn());
                                        Thread.sleep(100);
                                        rob.mouseRelease(rdc.getMouseBtn());
                                    }
                                    System.out.println("Mouse Clicked...");
                                    break;
                                }
                                case MouseEvent.MOUSE_PRESSED:
                                {
                                    rob.mousePress(rdc.getMouseBtn());
                                    break;
                                }
                                case MouseEvent.MOUSE_RELEASED:
                                {
                                    rob.mouseRelease(rdc.getMouseBtn());
                                    break;
                                }
                            }
                        }
                        rdc = null;
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error in RDC Receiver : " + e.toString());
                }
                
                
            }
        });
        t.start();
    }
    
    public void setScreen()
    {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
         int h = ((int)d.height/2);
         int w = ((int)d.width/2);
         setBounds(w,h-100,250,90);
         JPanel panel = new JPanel(new BorderLayout());
         panel.setBackground(new Color(51, 51, 51));
         lblInfo.setForeground(Color.white);
         lblInfo.setFont(new Font("Arial ", Font.BOLD, 14));
         panel.add(lblInfo,BorderLayout.CENTER);
         add(panel);
         setVisible(true);
    }
    
    public void setLabelText(String str) {
        lblInfo.setText(str);
    }
    
    public void closeScreen() {
       t.interrupt();
        this.removeAll();
       this.dispose();
       Home h = new Home(userId);
       h.start();
    }
}
