/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyconnect;

import classlib.Common;
import classlib.CommonFunction;
import classlib.DatabaseData;
import classlib.UserFile;
import classlib.UserSession;
import java.awt.event.WindowAdapter;
import javax.imageio.ImageIO;
import javax.swing.*;
import threads.CheckNet;

public class Login extends javax.swing.JFrame 
{    
    String loginUserId;
    String loginUserPass;
    UserSession userObject;
    
    public void showForm() // used to display login screen appear
    {
        setVisible(true);
        
        Common.setIcon(this);
          
        Common.setCenterGravity(this);
        
        initComponents();
        
        Common.setInternetLabel(lblInternet);
        
        CheckNet internetCheckThread = new CheckNet(lblInternet);
        internetCheckThread.start();
        
        userObject = new UserSession();
        userObject.setUser("");
        userObject.setBuddy("");
        
        setClosingEvent(this,userObject);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtUserPassword = new javax.swing.JPasswordField();
        lblInternet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        
        
        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Enter UserName");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Enter Password");

        txtUserId.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtUserId.setDragEnabled(true);

        btnLogin.setBackground(new java.awt.Color(102, 102, 102));
        btnLogin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(51, 51, 51));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(102, 102, 102));
        btnCancel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(51, 51, 51));
        btnCancel.setText("Cancel");
        btnCancel.setToolTipText("Click here to Exit or Close");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtUserPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblInternet.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInternet.setText("jLabel4");
        lblInternet.setToolTipText("Current Internet Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInternet, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addComponent(btnLogin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUserId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(txtUserPassword, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(23, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lblInternet)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0); // used to exit system
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        loginUserId  = txtUserId.getText().toString();
        loginUserPass = txtUserPassword.getText().toString();

        boolean status = DatabaseData.authnticateUser(loginUserId,loginUserPass); //user authntication is done here

        try {
            Thread.sleep(300);
            System.out.println(status);
        }catch(Exception e) {}

        if(status == true)
        {
            Common.hideForm(this);

            UserFile createObject = new UserFile();
            createObject.createAuthnticateFile(loginUserId,loginUserPass);

            /* whenever user login to system this common functionali ty will be done
            * user status will be free , online _ status will be online and current accesscoe is generated randomly
            */

            Thread t = new CommonFunction(loginUserId);
            t.start();

            Home home = new Home(loginUserId);
            home.start();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Enter Valid Username and Password", "Warning" , JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed
          
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JLabel lblInternet;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JPasswordField txtUserPassword;
    // End of variables declaration//GEN-END:variables

    private void setClosingEvent(Login l,UserSession userObject) 
    {
        l.addWindowListener(new WindowAdapter() 
        {
         @Override
             public void windowClosing(java.awt.event.WindowEvent windowEvent) 
             {
               if(JOptionPane.showConfirmDialog(null, "Are you sure want to Close The Login Screen ? ","Closing Login Screen",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)==0)
                {
                    System.exit(0);
                }
             }
        });
    }
}
