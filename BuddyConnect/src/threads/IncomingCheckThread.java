
package threads;

import buddyconnect.Home;
import buddyconnect.Request;
import classlib.PortDetails;
import java.util.ArrayList;

public class IncomingCheckThread extends Thread
{
    ArrayList<Object> list;
    String userId = "";
    Home incomeHome;
    
    public IncomingCheckThread()
    {
        
    }
    
    public IncomingCheckThread(String userId,Home h)
    {
        this.userId = userId;
        incomeHome = h;
    }
    
    @Override
    public void run()
    {
        if(!userId.equals(""))
        {
            while(!this.isInterrupted())
            {
                System.out.println("Incoming");
                list = PortDetails.getList(userId);
                try
                {
                    if(!list.isEmpty())
                    {
                        System.out.println("List size : " + list.size());
                        if(Request.activeStatus == false)
                        {
                            Request obj = new Request(userId,list,incomeHome);
                            obj.start();
                        }
                    }
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    break;
                }
            }
        }
    }
}