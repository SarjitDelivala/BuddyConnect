/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classlib;

import buddyconnect.Home;

/**
 *
 * @author SJPVAPCPCPP
 */

public class BuddyList extends Thread{
    
    String userId;
    
    public BuddyList()
    {
      
    }
    
    public BuddyList(String userId)
    {
        this.userId = userId;
    }
    
    @Override
    public void run(){
           while(!this.isInterrupted())
            {
                Home.setBuddyList(DatabaseData.getFriendList(userId));
                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException interrupt)
                {

                }
            }
    }
    
}
