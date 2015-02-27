package threads;

import buddyconnectserver.ServerScreen;
import classlib.PortDetails;

public class OnlineUserThread extends Thread
{
    @Override
    public void run()
    {
        int cnt;
        while(!this.isInterrupted())
        {
            cnt = PortDetails.getOnlineUsers();
            ServerScreen.setOnlineUsers(cnt);
            try
            {
                this.sleep(3000);
            }
            catch(InterruptedException e)
            {
            }
        }
        ServerScreen.setOnlineUsers(0);
        
    }
}
