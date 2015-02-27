/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyconnect;

import classlib.Common;
import classlib.PortDetails;
import classlib.TransferDetails;
import classlib.UserSession;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import threads.CheckNet;
import threads.FileRecThread;
import threads.Sender;

/**
 *
 * @author SJPVAPCPCPP
 */
public class FileTransfer extends javax.swing.JFrame
{

    String userId;
    String buddyId;
    UserSession userObject;
    Sender sender;
    static TransferDetails details;
    static String fileName = "";
    File f;
    static boolean fileCompleteStatus = false;
    /**
     * Creates new form FileTransfer
     */
    
    public FileTransfer(UserSession userObject) 
    {
        this.userObject = userObject;
        details = null;
        fileCompleteStatus = false;
    }
    
    public FileTransfer(UserSession userObject,Sender sender) 
    {
        this.userObject = userObject;
        this.sender = sender;
        details = null;
        fileCompleteStatus = false;
    }
    
    public static void setFileCompleteStatus(boolean val)
    {
        fileCompleteStatus = val;
    }
    
    public static boolean getFileCompleteStatus()
    {
        return fileCompleteStatus;
    }
    
    public static String getFileName()
    {
        return fileName;
    }
    
    
    public void start()
    {
        setVisible(true);
       
        Common.setIcon(this);  
        
        Common.setCenterGravity(this);
        
        initComponents();
        
        Common.setInternetLabel(lblInternet); // used to set label that shoes internet status
        
        CheckNet internetCheckThread = new CheckNet(lblInternet);
        internetCheckThread.start();
        
        progressReceiver.setVisible(false);
        progressSender.setVisible(false);
        
        setClosingEvent(this);  
        
        if(Home.userType.equals("receiver"))
        {
            jTextField1.setVisible(false);
            btnBrowse.setVisible(false);
            btnSend.setVisible(false);
            checkFileObject(this);
        }
        
        checkCompelteStatus(this,userObject);
                
    }
    
    public static TransferDetails getFileObject()
    {
        return details;
    }
    
    public static void setDetails(TransferDetails obj)
    {
        details = obj;
    }
 
    public static void checkFileObject(final JFrame frm)
    {
       Thread checkFileStatus = new Thread(new Runnable() {

           @Override
           public void run() {
                System.out.println("check file object once ");
                System.out.println("Details is Null");
                TransferDetails obj;
                try
                {
                    do
                    {
                        obj = getFileObject();
                        System.out.println("Still Object is Null");
                        if(obj != null)
                        {
                            break;
                        }
                    }while(obj==null);
                    int val = JOptionPane.showConfirmDialog(frm, "Do you want to Receieve " + details.getName() + " of size " + ((details.getSize()/1204)/1024) + " MB ?","",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    //System.out.print(val);
                    FileRecThread.setPermission(val);
                }
                catch(Exception e)
                {
                    System.out.println("Error in transfer : " + e.toString());
                }
                
           }
       });
       checkFileStatus.start();
    }
   
    public void checkCompelteStatus(final FileTransfer f,final UserSession userObject)
    {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                {
                    if(FileTransfer.getFileCompleteStatus()==true)
                    {
                        Common.hideForm(f);
                        FileTransfer.setFileCompleteStatus(false);
                        Home h = new Home(userObject.getUser());
                        h.start();
                        
                        break;
                    }
                }
            }
        });
        t.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        progressSender = new javax.swing.JProgressBar();
        lblInternet = new javax.swing.JLabel();
        progressReceiver = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buddy Connect : File Transfer");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jTextField1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        btnBrowse.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnSend.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSend.setText("Send File");
        btnSend.setToolTipText("Click here send file ");
        btnSend.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        progressSender.setToolTipText("Sending ");
        progressSender.setStringPainted(true);

        lblInternet.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInternet.setText("none");
        lblInternet.setToolTipText("Internet Status");

        progressReceiver.setToolTipText("Receiving");
        progressReceiver.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(progressReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(progressSender, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblInternet, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBrowse)
                .addGap(27, 27, 27)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(progressReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(progressSender, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblInternet))
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

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        if(jTextField1.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Select a file");
            return;
        }
        File f = new File(jTextField1.getText());
        if(f.exists())
        {
            if (f.isFile() && (!f.isHidden()))
            {
                if(f.canRead() == true)
                {
                    fileName = f.getAbsolutePath();

                }
                else
                {
                    JOptionPane.showMessageDialog(FileTransfer.this, "Not a valid file");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(FileTransfer.this, "Not a valid file");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(FileTransfer.this, "File does not exists");
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser();
        if(fc.showOpenDialog(FileTransfer.this) == 0)
        {
            f = fc.getSelectedFile();
            jTextField1.setText(f.getAbsolutePath());
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnSend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private static javax.swing.JLabel lblInternet;
    public static javax.swing.JProgressBar progressReceiver;
    public static javax.swing.JProgressBar progressSender;
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
                        PortDetails.updateAccess(userObject.getUser(),userObject.getBuddy(), "Yes", "Yes");
                        Home h = new Home(userObject.getUser());
                        h.start();
                    }
            } 
        });
    }

}