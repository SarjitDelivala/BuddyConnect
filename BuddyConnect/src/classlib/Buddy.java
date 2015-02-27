/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

public class Buddy extends Object{
    String userFName;
    String userLName;
    String userID;
    String fnlName;
    String userStatus;
    String operation;
    String fnlNameOperation;
    int oper;
    
    public void setOperation(String oper)
    {
       operation = oper; 
    }
    
    public void setOperation(int oper)
    {
       this.oper = oper; 
    }
   
    public void setFLO(String fName,String lName,String oper)
    {
        fnlNameOperation = fName + " " + lName + " ( " + oper + " ) ";
    }
    
    public void setFName(String fName)
    {
        userFName = fName;
    }
    public void setLName(String lName)
    {
        userLName = lName;
    }
    public void setID(String id)
    {
        userID = id;
    }
    public void setStatus(String status)
    {
        userStatus = status;
    }
    
    public void setfnlName(String fName,String lName)
    {
        fnlName = fName + " " + lName;
    }

    public String getOperation()
    {
        return operation;
    }
    
    public String getFLO()
    {
        return fnlNameOperation;
    }
    
    public String getfnlName()
    {
        return fnlName;
    }
    
    public String getFname()
    {
        return userFName;
    }
    
    public String getLname()
    {
        return userLName;
    }
    
    public String getID()
    {
        return userID;
    }
    
    public String getStatus()
    {
        return userStatus;
    }
    
    public int getOper()
    {
        return oper;
    }
    
}