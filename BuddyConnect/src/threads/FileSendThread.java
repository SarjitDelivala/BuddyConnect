
package threads;

import buddyconnect.FileTransfer;
import buddyconnect.Task;
import classlib.Loading;
import classlib.TransferDetails;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class FileSendThread extends Thread
{
    Socket sender;
    String fileUrl;
    public FileSendThread()
    {
        
    }
    
    public FileSendThread(Socket sender)
    {
        this.sender = sender;
    }
    @Override
    public void run()
    {
        try
        {
            InputStream in = sender.getInputStream();
            int i = in.read();
            Loading.setValue(i);
        }
        catch(IOException e)
        {
        }
        do
        {
            fileUrl = FileTransfer.getFileName();
        }while(fileUrl.equals(""));
        File file = new File(fileUrl);

        // Getting file name and size
        int size = (int) file.length();
        FileTransfer.progressSender.setVisible(true);
        FileTransfer.progressReceiver.setVisible(false);
        FileTransfer.progressSender.setMaximum(size);
        Task sendTask = new Task(FileTransfer.progressSender,size);
        sendTask.execute();
        System.out.println("Size : " + size + " file.length() = " + file.length());
        byte data[] = new byte[65535];
        TransferDetails details = new TransferDetails();
        details.setDetails(file.getName(), file.length(),file.getAbsolutePath());
        System.out.println("File name : " + details.getName() + " , " + "size = " + details.getSize());
        // Sending file details to the client
        
        System.out.println("Sending file details...");
        
        ObjectOutputStream sendDetails;
        try
        {
            sender.setKeepAlive(true);
            sendDetails = new ObjectOutputStream(sender.getOutputStream());
            sendDetails.writeObject(details);
            sendDetails.flush();
            sendTask.setValue(20);
        }
        catch(IOException e)
        {
            sendDetails = null;
            System.out.println("Error in sending objects : " + e.toString());
        }
        try
        {
            InputStream in = sender.getInputStream();
            int result = in.read();
            if(result == 0)
            {
                if(sendDetails != null)
                {
                    // Sending File Data 

                    System.out.println("Sending file data...");
                    int count;
                    FileInputStream fileStream = null;
                    BufferedInputStream fileBuffer = null;
                    OutputStream out = null;

                    try
                    {
                        fileStream = new FileInputStream(file);
                        fileBuffer = new BufferedInputStream(fileStream);
                        out = sender.getOutputStream();
                        long sum = 0;
                        System.out.println("Started to write file in stream..");
                        while((count = fileBuffer.read(data)) > 0)
                        {
                            sum += count;
                            if(sum != size)
                            {
                                sendTask.setValue(sum);
                            }
                            System.out.println("Data Sent : " + count);
                            out.write(data, 0, count);
                            out.flush();
                        }
                        System.out.println("Asking for feedback...");
                        int rply = in.read();
                        if(rply==0)
                        {
                            JOptionPane.showMessageDialog(null, "File Sent");
                            sendTask.setValue(sum);
                            FileTransfer.setFileCompleteStatus(true);
                        }
                        try 
                        {
                            finalize();
                        }
                        catch (Throwable ex) 
                        {
                            //Logger.getLogger(FileSendThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    catch(IOException e)
                    {
                        System.out.println("Send Thread error : " + e.toString());
                    }
                    finally
                    {
                        while(true)
                        {
                            try
                            {
                                if(out!=null)
                                {
                                    out.close();
                                }
                                break;
                            }
                            catch(IOException e)
                            {
                                continue;
                            }
                        }
                        while(true)
                        {
                            try
                            {
                                if(fileBuffer != null)
                                {
                                    fileBuffer.close();
                                }
                                break;
                            }
                            catch(IOException e)
                            {
                                continue;
                            }
                        }
                        while(true)
                        {
                            try
                            {
                                if(fileStream != null)
                                {
                                    fileStream.close();
                                }
                                break;
                            }
                            catch(IOException e)
                            {
                                continue;
                            }
                        }
                        while(true)
                        {
                            try
                            {
                                if(in != null)
                                {
                                    in.close();
                                }
                                break;
                            }
                            catch(IOException e)
                            {
                                continue;
                            }
                        }
                    }
                }
            }
            else
            {
                System.out.println("Buddy Denied...");
            }
        }
        catch(IOException e)
        {
            
        }
    }
}