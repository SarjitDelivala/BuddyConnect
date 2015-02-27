
package threads;

import classlib.DatabaseLog;

public class LogThread extends Thread
{
    //UserSession userObj;
    String msg;
    String user;
    String buddy;
    int oper;
    public LogThread() 
    {
    }

    public LogThread(String msg,String user,String buddy,int oper) 
    {
        this.msg = msg;
        this.user = user;
        this.buddy = buddy;
        this.oper = oper;
    }
    
    
    @Override
    public void run()
    {
        if(oper==1)
        {
            DatabaseLog.logChat(msg, user, buddy);
        }
        else if(oper==2)
        {
            DatabaseLog.logRDC(user, buddy, msg);
        }
        else if(oper==3)
        {
            DatabaseLog.logFile(user, buddy, msg);
        }
    }
}
