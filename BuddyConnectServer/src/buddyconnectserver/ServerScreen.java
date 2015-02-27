
package buddyconnectserver;

import java.awt.Color;
import threads.BuddyServer;
import threads.CheckNet;
import threads.OnlineUserThread;

public class ServerScreen extends javax.swing.JFrame {

    
    BuddyServer server = null;
    Task task;
    OnlineUserThread onlineThread;
    
    static int rdc=0,chat=0,file=0;
    
    
    public static void setOnlineUsers(int onlineUser)
    {
        lblOnlineUsers.setText("Online Users: " + onlineUser);
    }
    
    public static void setRDCUsers()
    {
        rdc++;
        lblRdcSession.setText("Total RDC Session: " + rdc);
    }
    
    public static void setChatUsers()
    {
        chat++;
        lblChatSession.setText("Total Chat Session: " + chat);
    }
    
    public static void setFileUsers()
    {
        file++;
        lblFileSession.setText("Total File Transfer Session: " + file);
    }
    
    
    
    
    public ServerScreen() {
        task = new Task();
        initComponents();
        jProgressBar1.setForeground(Color.white);
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setVisible(false);
        CheckNet check = new CheckNet(lblCheckNet);
        check.start();
    }
    
    public static void setText()
    {
        btnServerOnOff.setText("Stop Server");
        btnServerOnOff.setEnabled(true);
        jProgressBar1.setVisible(false);
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
        lblRdcSession = new javax.swing.JLabel();
        btnServerOnOff = new javax.swing.JToggleButton();
        lblOnlineUsers = new javax.swing.JLabel();
        lblFileSession = new javax.swing.JLabel();
        lblChatSession = new javax.swing.JLabel();
        lblCheckNet = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        lblRdcSession.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRdcSession.setForeground(new java.awt.Color(255, 255, 255));
        lblRdcSession.setText("Total RDC Session: 0");

        btnServerOnOff.setText("Start Server");
        btnServerOnOff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnServerOnOffItemStateChanged(evt);
            }
        });
        btnServerOnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServerOnOffActionPerformed(evt);
            }
        });

        lblOnlineUsers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOnlineUsers.setForeground(new java.awt.Color(255, 255, 255));
        lblOnlineUsers.setText("Online Users : 0");

        lblFileSession.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFileSession.setForeground(new java.awt.Color(255, 255, 255));
        lblFileSession.setText("Total File Transfer Session: 0");

        lblChatSession.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblChatSession.setForeground(new java.awt.Color(255, 255, 255));
        lblChatSession.setText("Total Chat Session: 0");

        lblCheckNet.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCheckNet.setText("Label2");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BUDDY CONNECT SERVER");

        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblOnlineUsers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRdcSession, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblChatSession, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFileSession, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(btnServerOnOff)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCheckNet, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(lblOnlineUsers)
                .addGap(28, 28, 28)
                .addComponent(lblRdcSession)
                .addGap(18, 18, 18)
                .addComponent(lblChatSession)
                .addGap(18, 18, 18)
                .addComponent(lblFileSession)
                .addGap(53, 53, 53)
                .addComponent(lblCheckNet)
                .addGap(23, 23, 23)
                .addComponent(btnServerOnOff)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
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

    private void btnServerOnOffItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnServerOnOffItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnServerOnOffItemStateChanged

    private void btnServerOnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServerOnOffActionPerformed

        if(btnServerOnOff.isSelected())
        {
            jProgressBar1.setVisible(true);
            jProgressBar1.setToolTipText("Server is Starting");
            btnServerOnOff.setEnabled(false);
            task.execute();
            Task.setValue(10);
            try
            {
                Thread.sleep(500);
            }
            catch(Exception e)
            {

            }
            server = new BuddyServer();
            try
            {
                Thread.sleep(500);
            }
            catch(Exception e)
            {
            }
            server.start();
            Task.setValue(20);
            try
            {
                Thread.sleep(500);
            }
            catch(Exception e)
            {
            }
            onlineThread = new OnlineUserThread();
            onlineThread.start();
        }
        else
        {
            server.interrupt();
            onlineThread.interrupt();
            btnServerOnOff.setText("Start Server");
        }
    }//GEN-LAST:event_btnServerOnOffActionPerformed

    public static void setTotalUserText(int total)
    {
        lblOnlineUsers.setText("Online Users : " + total);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerScreen().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JToggleButton btnServerOnOff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    protected static javax.swing.JProgressBar jProgressBar1;
    private static javax.swing.JLabel lblChatSession;
    private javax.swing.JLabel lblCheckNet;
    private static javax.swing.JLabel lblFileSession;
    private static javax.swing.JLabel lblOnlineUsers;
    private static javax.swing.JLabel lblRdcSession;
    // End of variables declaration//GEN-END:variables
}
