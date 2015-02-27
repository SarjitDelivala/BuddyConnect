/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyconnect;

import classlib.Buddy;
import classlib.Common;
import classlib.DatabaseData;
import classlib.Loading;
import classlib.PortDetails;
import classlib.UserSession;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import threads.Receiver;

/**
 *
 * @author SJPVAPCPCPP
 */
public class Request extends javax.swing.JFrame
{

    /**
     * Creates new form Request
     */
    
    Object list[];
    UserSession userObj;
    final String userId;
    Home requestHome;
    
    public static boolean activeStatus = false;
    
    public Request(String uid,ArrayList<Object> data,Home h) 
    {
        userId = uid;
        activeStatus = true;
        list = data.toArray();
        requestHome = h;
    }

    public void start()
    {
        setVisible(true);
        
        Common.setIcon(this);
        
        Common.setCenterGravity(this);
        
        initComponents();
        
        userList.setListData(list);

        Common.setClosingEvent(this,userObj,"Request");
    }
    
    public void cancelAllRequest(final String userId)
    {
        Thread cancelRequest = new Thread(new Runnable() {
            
            @Override
            public void run() {
                   DatabaseData.updateSessionStatusFALSE(userId);
            }
        });
        
        cancelRequest.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("List Of Buddy");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        userList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        userList.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userList.setToolTipText("Select One Buddy From List");
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSelected(evt);
            }
        });
        jScrollPane1.setViewportView(userList);

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Connect Buddy");
        jButton1.setToolTipText("Click Here");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 204), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setText("Cancel All Request");
        jButton2.setToolTipText("Click Here");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 204), 1, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                        .addGap(55, 55, 55)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cancelAllRequest(userId);
        Common.hideForm(this);
        activeStatus = false;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Home.income.interrupt();
        Home.buddies.interrupt();

        int index = userList.getSelectedIndex();
        String buddyName = String.valueOf(userList.getSelectedValue());
        Buddy checkbuddy[] = PortDetails.getBuddyObjectList();
        if(checkbuddy[index].getFLO().equals(buddyName))
        {
            final String buddyId = checkbuddy[index].getID();
            String buddyfnlName = checkbuddy[index].getFname() + " " +  checkbuddy[index].getLname();
            int oper = checkbuddy[index].getOper();
            int val = JOptionPane.showConfirmDialog(this, "Are you sure want to connect with " + buddyfnlName + " Buddy ?","Information",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(val == 0)
            {

                Common.hideForm(this);

                Common.hideForm(requestHome);

                Thread updateStatus = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            boolean flag = true;
                            try
                            {
                                DatabaseData.updateSessionStatusTRUE(userId,buddyId);
                                DatabaseData.updateSessionStatusFALSE(userId);
                            }
                            catch(Exception e){}

                        }
                    });
                    updateStatus.start();

                    userObj = new UserSession();
                    userObj.setUser(userId);
                    userObj.setBuddy(buddyId);
                    userObj.setOper(oper);

                    Home.userType="receiver";
                    Common.hideForm(requestHome);
                    switch(oper)
                    {
                        case 1:
                        {
                            // for text chat
                            Receiver receieve = new Receiver(userObj);
                            TextChat objText = new TextChat(userObj);
                            objText.start();
                            Loading x = new Loading(objText);
                            x.start();
                            receieve.start();
                            break;
                        }
                        case 2:
                        {
                            //for remote dekstop
                            Receiver rec = new Receiver(userObj);
                            rec.start();

                            //below object is for gui
                            RDCReceiver rdc = new RDCReceiver(userObj.getUser());
                            rdc.start(rdc);
                            break;
                        }
                        case 3:
                        {
                            // for file transfer
                            Receiver receieve = new Receiver(userObj);
                            FileTransfer ftpObject = new FileTransfer(userObj);
                            ftpObject.start();
                            Loading x = new Loading(ftpObject);
                            x.start();
                            receieve.start();
                            break;
                        }
                        case 4:
                        {

                        }
                    }
                }
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listSelected(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSelected

    }//GEN-LAST:event_listSelected
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList userList;
    // End of variables declaration//GEN-END:variables
}
