
package threads;

import classlib.PortDetails;
import classlib.TransferDetails;
import classlib.UserSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class FileSendThread extends Thread
{
    Socket sender,client;
    int oper;
    UserSession userObj;
    LogThread t;
    public FileSendThread()
    {
        
    }
    
    public FileSendThread(Socket sender,Socket client,int oper, UserSession userObj)
    {
        this.client = client;
        this.sender = sender;
        this.oper = oper;
        this.userObj = userObj;
    }
    @Override
    public void run()
    {
        // Getting file name and size
        
        ObjectOutputStream sendDetails = null;
        TransferDetails details = null;
        ObjectInputStream readObject = null;
        OutputStream out = null;
        InputStream in = null;
        try
        {
            out = sender.getOutputStream();
            out.write(0);
            out = client.getOutputStream();
            out.write(0);
            System.out.println("In run method..");
            readObject = new ObjectInputStream(sender.getInputStream());
            details = (TransferDetails) readObject.readObject();
            System.out.println("Object Received from Sender");
            sendDetails = new ObjectOutputStream(client.getOutputStream());
            sendDetails.writeObject(details);
            sendDetails.flush();
            System.out.println("Sent Object to client");
        }
        catch(ClassNotFoundException e)
        {
            details = null;
        }
        catch(IOException e)
        {
            sendDetails = null;
            readObject = null;
            System.out.println(e.toString());
        }
        int val = 5;
        try
        {
            in = client.getInputStream();
            out = sender.getOutputStream();
            System.out.println("Awaiting Client Answer");
            val = in.read();
            out.write(val);
            out.flush();
            System.out.println("Answer = " + val);
        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
        if(val==0)
        {
            if(sendDetails != null && details != null && readObject != null)
            {
                // Sending File Data 
                int count;
                BufferedOutputStream fileBuffer = null;
                FileOutputStream fileOut = null;
                int size = (int) details.getSize();
                byte data[] = new byte[size];
                String ext = details.getName().substring(details.getName().lastIndexOf("."));
                String fileName = userObj.getUser() + "_" + userObj.getBuddy() + "_" + PortDetails.getCurrentDateTimeFile() + ext;
                System.out.println("Sending file now...");
                try
                {
                    fileOut = new FileOutputStream("D:\\BuddyConnectFiles\\" + fileName);
                    fileBuffer = new BufferedOutputStream(fileOut);
                    in = sender.getInputStream();
                    out = client.getOutputStream();
                    int sum = 0;
                    t = new LogThread(fileName, userObj.getUser(), userObj.getBuddy(), oper);
                    t.start();
                    while((count = in.read(data)) > 0)
                    {
                        sum += count;
                        System.out.println("Sum = " + sum);
                        out.write(data, 0, count);
                        out.flush();
                        System.out.println("Data Sent to client : " + count + " out of " + details.getSize());
                        fileBuffer.write(data, 0, count);
                        fileBuffer.flush();
                        System.out.println("Data Received in server : " + count + " out of " + details.getSize());
                        if(sum==details.getSize())
                        {
                            System.out.println("Breaking");
                            break;
                        }
                    }
                    Thread.sleep(1000);
                    System.out.println("Getting stream of sender and client");
                    OutputStream outRply = sender.getOutputStream();
                    System.out.println("Receiving response from Client");
                    System.out.println("Got Response from Client");
                    outRply.write(0);
                    System.out.println("File Transfer complete");
                    outRply.close();
                }
                catch(IOException e)
                {
                    System.out.println("Error in Sending file block : " + e.toString());
                }
                catch(InterruptedException e){}
                finally
                {
                    while(true)
                    {
                        try
                        {
                            if(fileOut!=null)
                            {
                                fileOut.close();
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
                            if(fileBuffer!=null)
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
            else
            {
                System.out.println("There was some fault in sending file details ... Please try again...");
            }
        }
        
    }
}
