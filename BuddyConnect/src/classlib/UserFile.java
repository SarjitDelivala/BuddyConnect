/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author SJPVAPCPCPP
 */

public class UserFile 
{
    Boolean fileFoundStatus = null;
    Boolean objectFoundStatus = null;
    
        public boolean checkFileExist() // check that userfile exit or not
        {
            try
            {
                File f = new File("userfile");
                if(f.exists())
                {
                    fileFoundStatus=true;
                }
                else
                {
                    fileFoundStatus=false;
                }
            }
            catch(Exception e)
            {
            }
            finally
            {
                return fileFoundStatus;
            }
        }
        
        public boolean checkUserExist() // check that user object exist or not
        {
            try 
            {
                ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(new File("userfile")));
                UserInfo loginObj = (UserInfo)oInput.readObject();
                objectFoundStatus=true;
            }
            catch(Exception e2)
            {
                objectFoundStatus=false;
            }
            finally
            {
                return objectFoundStatus;
            }
        }
        
        public void createAuthnticateFile(String userId,String userPass) // crate authnticatation user file 
        {
            try 
            {
                ObjectOutputStream oOutput = new ObjectOutputStream(new FileOutputStream("userfile"));
                UserInfo data = new UserInfo();
                data.setUserId(userId);
                data.setUserPassword(userPass);
                oOutput.writeObject(data);
                oOutput.close();
            }
            catch(Exception e)
            {}
    }
}