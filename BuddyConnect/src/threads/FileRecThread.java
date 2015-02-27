
package threads;

import buddyconnect.FileTransfer;
import buddyconnect.Task;
import classlib.Loading;
import classlib.TransferDetails;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;


public class FileRecThread extends Thread
{
    Socket receiveSocket;
    static int allow;
    
    public FileRecThread()
    {
        allow = 5;
    }
    public FileRecThread(Socket receiveSocket)
    {
        this.receiveSocket = receiveSocket;
        allow = 5;
    }
    
    public static void setPermission(int perm)
    {
        allow = perm;
        System.out.println("Permission set to : " + allow);
    }
    
    public static int getPermission()
    {
        return allow;
    }
    
    @Override
    public void run()
    {
        
        // Getting file details
        ObjectInputStream getDetails = null;
        TransferDetails details;
        System.out.println("Receiving object");
        try
        {
            InputStream in = receiveSocket.getInputStream();
            int i = in.read();
            Loading.setValue(i);
            getDetails = new ObjectInputStream(receiveSocket.getInputStream());
            details = (TransferDetails) getDetails.readObject();
            FileTransfer.setDetails(details);
            System.out.println("Received Object");
        }
        catch(IOException e)
        {
            getDetails = null;
            details = null; 
            System.out.println("Error in file rec thread IOException: " + e.toString());
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error in file rec thread Class not found: " + e.toString());
            details = null;
        }
        int i;
        System.out.print("above do while");
        do
        {
            
            i = getPermission();
            //System.out.print(i);
            if(i==0)
            {
                System.out.println("Yes");
                break;
            }
            if(i==1)
            {
                System.out.println("No");
                break;
            }
        }while(i > 1);
        System.out.println("Answer = " + allow);
        if(i==0)
        {
            OutputStream out = null;
            try
            {
                out = receiveSocket.getOutputStream();
                out.write(allow);
                out.flush();
            }
            catch(IOException e)
            {
                System.out.println("Error in sending answer = " + e.toString());
            }
            
            if(details != null && getDetails != null)
            {
                String fileName = details.getName();
                long fileSize = details.getSize();
                byte data[] = new byte[65535];
                System.out.println("File Name : " + fileName + " , size = " + details.getSize());
                Task receTask = new Task(FileTransfer.progressReceiver, fileSize);
                FileTransfer.progressSender.setVisible(false);
                FileTransfer.progressReceiver.setVisible(true);
                FileTransfer.progressReceiver.setMaximum((int)fileSize);
                receTask.execute();
                FileOutputStream fileOut = null;
                InputStream fileIn = null;
                BufferedOutputStream fileBuffer = null;
                try
                {
                    fileOut = new FileOutputStream("D:\\" + fileName);
                    fileIn = receiveSocket.getInputStream();
                    fileBuffer = new BufferedOutputStream(fileOut);
                    int count;
                    System.out.println("Receiving file");
                    long sum = 0;
                    while((count = fileIn.read(data)) > 0)
                    {
                        sum += count;
                        System.out.println("Sum = " + sum);
                        fileBuffer.write(data, 0, count);
                        System.out.println("Data Received : " + count + " out of " + details.getSize());
                        fileBuffer.flush();
                        receTask.setValue(sum);
                        if(sum==details.getSize())
                        {
                            System.out.println("Breaking");
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "File Received.");
                    try 
                    {
                        Thread.sleep(1000);
                        finalize();
                    }
                    catch (InterruptedException ex) 
                    {
                    }
                    catch (Throwable ex) 
                    {
                        //Logger.getLogger(FileSendThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FileTransfer.setFileCompleteStatus(true);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    while(true)
                    {
                        try
                        {
                            if(out != null)
                            {
                                out.close();
                                break;
                            }
                            else
                            {
                                break;
                            }
                        }
                        catch(IOException e)
                        {
                        }
                    }
                    while(true)
                    {
                        try
                        {
                            if(fileOut != null)
                            {
                                fileOut.close();
                                break;
                            }
                            else
                            {
                                break;
                            }
                        }
                        catch(IOException e)
                        {
                        }
                    }
                    while(true)
                    {
                        try
                        {
                            if(fileBuffer != null)
                            {
                                fileBuffer.close();
                                break;
                            }
                            else
                            {
                                break;
                            }
                        }
                        catch(IOException e)
                        {
                        }
                    }
                    while(true)
                    {
                        try
                        {
                            if(fileIn != null)
                            {
                                fileIn.close();
                                break;
                            }
                            else
                            {
                                break;
                            }
                        }
                        catch(IOException e)
                        {
                        }
                    }
                }
            }
        }
        else
        {
            OutputStream out = null;
            try
            {
                out = receiveSocket.getOutputStream();
                out.write(allow);
                out.flush();
            }
            catch(IOException e)
            {
                
            }
            finally
            {
                while(true)
                {
                    try
                    {
                        out.close();
                        break;
                    }
                    catch(IOException e)
                    {
                        
                    }
                }
            }
        }       
    }
}
