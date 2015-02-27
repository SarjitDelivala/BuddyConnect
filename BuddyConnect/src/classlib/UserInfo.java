/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author SJPVAPCPCPP
 */
public class UserInfo implements Serializable
{    
    String userId;
    String userPass;
    String userStatus;
    int userCode;
    Random code = null;
    
    // --------------- methods for setting user detail ----------------- //
    public void setUserId(String uid){
       userId = uid;
    }
    
    public void setUserPassword(String ups){
       userPass = ups;
    }
    
    public void setUserStatus(Boolean result){
        if(result == true) { userStatus = "Online"; }
        else { userStatus = "last online on ..."; }
    }
    
    public void generateRandomCode(){
        code = new Random();
        String uCode = String.valueOf(userCode);
        while(uCode.length()<=0 || uCode.length()>6){
            userCode = code.nextInt();
            uCode = String.valueOf(userCode);
        }
    }
    
    // --------------- methods for getting user detail ----------------- //
    public String getUserId(){
        return userId;
    }
    
    public String getUserPassword(){
        return userPass;
    }
    
    public String getUserStatus(){
        return userStatus;
    }
    
    public int getUserAccesscode(){
        return userCode;
    }
}