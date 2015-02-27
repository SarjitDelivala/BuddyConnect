/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

/**
 *
 * @author SJPVAPCPCPP
 */
public class CommonFunction extends Thread
{
    String userId;
    
    public CommonFunction(String uid)
    {
        userId = uid;
    }
    
    @Override
    public void run()
    {
          DatabaseData.updateDataAuthnetication(userId);
          DatabaseData.insertDataUserlog(userId);
    }
}
