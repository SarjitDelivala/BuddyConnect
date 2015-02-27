/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyconnect;

import classlib.CommonFunction;
import classlib.DatabaseData;
import classlib.UserFile;
import classlib.UserInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import threads.CheckNet;

public class BuddyConnect 
{         
    public static void main(String args[])
    {
        startSystem(); // start the system
    }
    
    public static void startSystem()
    {
        try { 
            //UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch(Exception e) {}
        
        CheckNet check = new CheckNet();
        check.start(); // starting internet checking thread
        
        
        UserFile objectUserFile = new UserFile();
        if(objectUserFile.checkFileExist())
        {       
           if(objectUserFile.checkUserExist()) //if userfile exists then no need to login and this 
           {
               UserInfo loggedUser = assign();
               
               if(DatabaseData.checkUserExist(loggedUser.getUserId()) == true)
               {
                   
               /* whenever user login to system this common functionali ty will be done
                * user status will be free , online _ status will be online and current accesscoe is generated randomly
               */
                   
               Thread t = new CommonFunction(loggedUser.getUserId()); 
               t.start();
               
               // new Home screen object is made
               
               Home home = new Home(loggedUser.getUserId());
               home.start();
               }
               else
               {
                    Login newLoginObject = new Login();
                    newLoginObject.showForm();  
               }
               
           }
           else
           {
                   Login newLoginObject = new Login();
                    newLoginObject.showForm();
           }
        }
        else
        {
            //if userfile doesn't exist then login screen will be appeared
            Login newLoginObject = new Login();
            newLoginObject.showForm();
        }
    }
    
    public static UserInfo assign()
    {
        UserInfo loginObj = null;
        try 
            {
                ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(new File("userfile")));
                loginObj = (UserInfo)oInput.readObject();
                return loginObj;
            }
            catch(Exception e2)
            {
                System.out.print(e2.toString());
            }
        return loginObj;
    }
}
