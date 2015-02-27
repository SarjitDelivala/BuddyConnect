/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyconnect;

import classlib.Common;
import classlib.DatabaseData;
import classlib.PortDetails;
import classlib.UserSession;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import threads.ChatRecThread;
import threads.ChatSendThread;
import threads.CheckNet;

/**
 *
 * @author SJPVAPCPCPP
 */

public class TextChat extends javax.swing.JFrame{

    
    /**
     * Creates new form TextChat
     */
    String buddyId;
    String userId;
    UserSession userObject;
    
    public TextChat(UserSession userObj) 
    {
        userId = userObj.getUser();
        buddyId = userObj.getBuddy();
        userObject = userObj;
    }

    public void start()
    {
       setVisible(true);
       
       Common.setIcon(this);
       
       Common.setCenterGravity(this);
       
       initComponents();
 
       Common.setInternetLabel(lblInternet);
       
       CheckNet internetCheckThread = new CheckNet(lblInternet);
       internetCheckThread.start();
       
       
       setClosingEvent(this);
        
       getMessage(buddyId,this,userObject);
       
       userObject = setFnLname(userObject);
       
       lblBuddyLocation.setText("Buddy's Location: " + PortDetails.getBuddyLocation(userObject.getBuddy()));
    }
    
    public static void getMessage(final String buddyId,final TextChat t,final UserSession userObject)
    {
        Thread getMsg = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                {
                    try 
                    {
                        String msg = ChatRecThread.getMsg();
                        if(msg.equals(""))
                        {}
                        else
                        {
                            System.out.println("Received : " + msg);
                            if("BUDDY_CHAT_EXIT".equals(msg))
                            {
                                JOptionPane.showMessageDialog(null, "Buddy closed this Session");
                                t.closeTextChat();
                                break;
                            }
                            String oldMessage = lblChat.getText().toString();
                            String newmessage = oldMessage + "\n" + userObject.getBuddyFirstName() + " " + userObject.getBuddyLastName() + " : " + msg + "\n";
                            lblChat.setText(newmessage);
                            ChatRecThread.setMsg("");
                        }
                    }catch(Exception e)
                    {
                    }
                }
            }
        });
        
        getMsg.start();
       
    }
    
    public void closeTextChat()
    {
       Common.hideForm(this);
       PortDetails.updateAccess(userObject.getUser(),userObject.getBuddy(), "Yes", "Yes");
       Home h = new Home(userObject.getUser());
       h.start();
    }
    
    public void sendMsg()
    {
        String msg = txtChatBox.getText().toString();
        if(!msg.equals(""))
        {
            lblChat.setText(lblChat.getText().toString() + "\n" + userObject.getFirstName() + " " + userObject.getLastName() + " : " + msg  + "\n");
            System.out.println("Sending msg = " + msg);
            ChatSendThread.setMsg(msg);
            txtChatBox.setText("");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChatBox = new javax.swing.JTextArea();
        lblInternet = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblChat = new javax.swing.JTextArea();
        lblBuddyLocation = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chat");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Send");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 3));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtChatBox.setColumns(20);
        txtChatBox.setRows(5);
        txtChatBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtChatBoxKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtChatBox);

        lblInternet.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInternet.setText("none");
        lblInternet.setToolTipText("Internet Status");

        lblChat.setEditable(false);
        lblChat.setBackground(new java.awt.Color(51, 51, 51));
        lblChat.setColumns(20);
        lblChat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblChat.setForeground(new java.awt.Color(255, 255, 255));
        lblChat.setLineWrap(true);
        lblChat.setRows(5);
        jScrollPane2.setViewportView(lblChat);

        lblBuddyLocation.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBuddyLocation.setForeground(new java.awt.Color(255, 255, 255));
        lblBuddyLocation.setText("none");
        lblBuddyLocation.setToolTipText("Internet Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblInternet, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)
                                .addComponent(lblBuddyLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInternet)
                    .addComponent(lblBuddyLocation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sendMsg();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtChatBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChatBoxKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            sendMsg();
        }
    }//GEN-LAST:event_txtChatBoxKeyPressed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JLabel lblBuddyLocation;
    public static javax.swing.JTextArea lblChat;
    private static javax.swing.JLabel lblInternet;
    private static javax.swing.JTextArea txtChatBox;
    // End of variables declaration//GEN-END:variables

    private void setClosingEvent(final JFrame frm) 
    {
        frm.addWindowListener(new java.awt.event.WindowAdapter() 
        {
           @Override
             public void windowClosing(java.awt.event.WindowEvent windowEvent) 
                {
                    if (JOptionPane.showConfirmDialog(frm,"Do you want to close The TextChat Form ?", "Closing the TextChat Form",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        Common.hideForm(frm);
                        ChatSendThread.setMsg("BUDDY_CHAT_EXIT");
                        try { Thread.sleep(2000);
                        }catch(InterruptedException e){}
                        PortDetails.updateAccess(userObject.getUser(),userObject.getBuddy(), "Yes", "Yes");
                        Home h = new Home(userObject.getUser());
                        h.start();
                    }
            } 
        });
    }

    private UserSession setFnLname(final UserSession userObject) 
    {
       Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                   userObject.setFirstName(DatabaseData.getFname(userObject.getUser()));
                   userObject.setLastName(DatabaseData.getLname(userObject.getUser()));
                   userObject.setBuddyFirstName(DatabaseData.getFname(userObject.getBuddy()));
                   userObject.setBuddyLastName(DatabaseData.getLname(userObject.getBuddy()));
            }
        });
       t.start();
       return userObject;
    }
}