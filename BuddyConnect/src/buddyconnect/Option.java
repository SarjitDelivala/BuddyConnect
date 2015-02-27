package buddyconnect;

import classlib.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import threads.Sender;
/**
 *
 * @author SJPVAPCPCPP
 */

public class Option extends javax.swing.JFrame
{
    String buddyId;
    String userId;
    JFrame home;
    
    static boolean status = false;
    public static boolean activeScreenStatus;
    UserSession userObject;
    
    
    public Option(String uid,String bid,JFrame frm) {
        userId = uid;
        buddyId = bid;
        userObject = new UserSession();
        userObject.setUser(userId);
        userObject.setBuddy(buddyId);
        
        home = frm;
    
        Home.stopIncomingCheckThread(); // close incoming thread
        Home.stopGetFriendListThread(); // close get friend list thread
    }
        
    public static void setActiveStatusFalse()
    {
        activeScreenStatus = false;
    }
    
    public static void setActiveStatusTrue()
    {
         activeScreenStatus = true;
    }
    
    public void start()
    {
         setActiveStatusTrue();
        
         setVisible(true);
         
         Common.setIcon(this);
          
         Common.setCenterGravity(this);
         
         initComponents();
        
         Common.setClosingEvent(this,userObject,"Option"); // this is only for option form
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btnRDC = new javax.swing.JButton();
        btnFT = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnChat = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Connect Buddy Via");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        btnRDC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRDC.setForeground(new java.awt.Color(51, 51, 51));
        btnRDC.setText("Remote Desktop Connection");
        btnRDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRDCActionPerformed(evt);
            }
        });

        btnFT.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnFT.setForeground(new java.awt.Color(51, 51, 51));
        btnFT.setText("File Transfer");
        btnFT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFTActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnClose.setForeground(new java.awt.Color(51, 51, 51));
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnChat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnChat.setForeground(new java.awt.Color(51, 51, 51));
        btnChat.setText("Chat Message");
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRDC, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(btnFT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRDC, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFT, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void putStatusBusy()
    {
        Thread statusBusy = new Thread(new Runnable() {

            @Override
            public void run() {
                DatabaseData.updateUserStatusBusy(userId);
            }
        });
        
        statusBusy.start();
    }
    
    public void putStatusFree()
    {
         Thread statusFree = new Thread(new Runnable() {

            @Override
            public void run() {
                DatabaseData.updateUserStatusFree(userId);
            }
        });
        
        statusFree.start();
    }
    
    private void btnRDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRDCActionPerformed
	
        Home.stopIncomingCheckThread(); // close incoming thread
        Home.stopGetFriendListThread(); // close get friend list thread
       	
        putStatusBusy();
                
        boolean checkStatus = false;
      
        Home.userType="Server";
               
       if(status==false)
       {
            String code = JOptionPane.showInputDialog(this,"Enter Buddy's access code");
            checkStatus = DatabaseData.authnticateBuddyAceessCode(buddyId, code);
        
          if(checkStatus)
            {
                Common.hideForm(this);
                Common.hideForm(home);
                
                UserSession objRDC = new UserSession();
                objRDC.setUser(userId);
                objRDC.setBuddy(buddyId);
                objRDC.setOper(2);
                
                if(Home.userType.equals("Server"))
                {
                    Sender send = new Sender(2,userId,buddyId);
                    send.initiateSession();
                    
        
                    RemoteDesktopSender rdp = new RemoteDesktopSender(objRDC);
                    rdp.start();
                    
                    Loading x = new Loading(rdp);
                    x.start();
                    
                    send.start();
                }   
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Access Code","Warning Message",JOptionPane.WARNING_MESSAGE);
            }
       }
       
    }//GEN-LAST:event_btnRDCActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        putStatusFree();
        
        setActiveStatusFalse(); // put screen status false
        
        Common.hideForm(this);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnFTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFTActionPerformed
        
        Home.stopIncomingCheckThread(); // close incoming thread
        Home.stopGetFriendListThread(); // close get friend list thread
        
        putStatusBusy();
            
        Common.hideForm(this);
        Common.hideForm(home);

        Home.userType = "Server";

        UserSession objFTP = new UserSession();
        objFTP.setUser(userId);
        objFTP.setBuddy(buddyId);
        objFTP.setOper(3);
                
        if (Home.userType.equals("Server"))
        { 
            Sender sendFile = new Sender(3, userId, buddyId);
            sendFile.initiateSession();
            
            FileTransfer ftp = new FileTransfer(objFTP,sendFile);
            ftp.start();
            
            Loading x = new Loading(ftp);
            x.start();
            
            sendFile.start();
        }
        
    }//GEN-LAST:event_btnFTActionPerformed

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatActionPerformed

        Home.stopIncomingCheckThread(); // close incoming thread
        Home.stopGetFriendListThread(); // close get friend list thread
        
        Common.hideForm(this);
        Common.hideForm(home);
          
        Home.userType = "Server";
        
        putStatusBusy();
        try
        {
            UserSession userObj = new UserSession();
            userObj.setUser(userId);
            userObj.setBuddy(buddyId);
            userObj.setOper(1);
        
        if (Home.userType.equals("Server"))
        {
            Sender sendChat = new Sender(1, userId, buddyId);
            sendChat.initiateSession();
            
            TextChat t = new TextChat(userObj);
            t.start();
           
            Loading x = new Loading(t);
            x.start();
           
            sendChat.start();
        }
        
     }
        catch(Exception ex)
        {
                JOptionPane.showMessageDialog(null, ex.toString());
        }
       
    }//GEN-LAST:event_btnChatActionPerformed

    public static void setClosingEvent(final Option frm) {
        
        frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frm.addWindowListener(new java.awt.event.WindowAdapter() 
        {
            @Override
             public void windowClosing(java.awt.event.WindowEvent windowEvent) 
                {
                   frm.putStatusFree();
        
                   setActiveStatusFalse(); // put screen status false
           
                  Common.hideForm(frm);
                }
        });
    }
      
    
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnFT;
    private javax.swing.JButton btnRDC;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    // End of variables declaration//GEN-END:variables
    
}