/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyconnect;

import classlib.Common;
import classlib.UserSession;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import threads.RDCEventThread;

/**
 *
 * @author SARJIT
 */
public class RemoteDesktopSender extends javax.swing.JFrame implements MouseListener,MouseMotionListener,KeyListener
{

    /**
     * Creates new form RemoteDesktopSender
     */
    String userId;
    String buddyId;
    UserSession userObject;
    int keyCode,mouseBtn,mouseClicks,mouseOper,mouseX,mouseY;
    boolean shift,alt,ctrl;
    static Image myImage;
    
    public RemoteDesktopSender(UserSession userObject) 
    {
        this.userObject = userObject;
        userObject.setUser(userId);
        userObject.setBuddy(buddyId);
        userObject.setOper(2);
    }
    
    public void start()
    {
        initComponents();
        
        setVisible(true);
        
        Common.setIcon(this);
        
        setScreensize();
        
       /* lblImage.addMouseListener(this);
        
        lblImage.addMouseMotionListener(this);
        */
        
        addMouseListener(this);
        
        addMouseMotionListener(this);
        
        addKeyListener(this);
        
        Common.setClosingEvent(this, userObject, "Remote Desktop Connection");
        
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                {
                    repaint();
                }
            }
        });
        
        t.start();
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(myImage,0,20, null);
    }
    
    public static void setImage(BufferedImage img)
    {
        if(img != null)
        {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            myImage = (Image)img.getScaledInstance(900,700,Image.SCALE_AREA_AVERAGING );
            /*lblImage.setIcon(new ImageIcon(newMyimg));
            lblImage.repaint();*/
         }
    }
   
    public void setScreensize()
    {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension d = tool.getScreenSize().getSize();
        d.setSize(900, 700);
        this.setSize(d);
        this.setResizable(false);
        this.setState(MAXIMIZED_BOTH);
    }
    
    public void sendData()
    {
        RDCEventThread rdc = new RDCEventThread(keyCode, mouseBtn, mouseClicks, mouseOper, mouseX, mouseY, shift, alt, ctrl);
        rdc.start();
        keyCode = mouseBtn = mouseClicks = mouseOper = mouseX = mouseY = -1;
        shift=alt=ctrl = false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1412, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        keyCode = mouseBtn = mouseClicks = mouseOper = mouseX = mouseY = -5;
        shift=alt=ctrl = false;
        sendData();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent evt) 
    {
        mouseBtn = evt.getButton();
        mouseClicks = evt.getClickCount();
        mouseOper = MouseEvent.MOUSE_CLICKED;
        mouseX = evt.getX();
        mouseY = evt.getY();
        sendData();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        mouseX = e.getX();
        mouseY = e.getY();
        sendData();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent evt) 
    {
        System.out.println("Key Code: " + evt.getKeyCode());
        keyCode = evt.getKeyCode();
        shift = evt.isShiftDown();
        alt = evt.isAltDown();
        ctrl = evt.isControlDown();
        sendData();
    }

    @Override
    public void keyReleased(KeyEvent evt) 
    {
    }
}
