/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

import java.io.Serializable;

/**
 *
 * @author SARJIT
 */
public class UserSession implements Serializable
{
    int oper;
    String user;
    String buddy;
    String userName;
    String lName;
    String fName;
    String buddyLName;
    String buddyFName;
    
    public void setLastName(String name)
    {
        lName = name;
    }
  
    public void setFirstName(String name)
    {
        fName = name;
    }
    
    public void setBuddyLastName(String name)
    {
        buddyLName = name;
    }
  
    public void setBuddyFirstName(String name)
    {
        buddyFName = name;
    }
    
    public void setOper(int oper)
    {
        this.oper = oper;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public void setBuddy(String buddy)
    {
        this.buddy = buddy;
    }
    
    public void setUserName(String name)
    {
        userName = name;
    }
    
    public int getOper()
    {
        return oper;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public String getBuddy()
    {
        return buddy;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public String getLastName()
    {
        return lName;
    }
    
    public String getFirstName()
    {
        return fName;
    }
    
    public String getBuddyLastName()
    {
        return buddyLName;
    }
    
    public String getBuddyFirstName()
    {
        return buddyFName;
    }
    
}
