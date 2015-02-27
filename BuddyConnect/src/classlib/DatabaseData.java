/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author SJPVAPCPCPP
 */

public class DatabaseData 
{
   final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   final static String DB_URl = "jdbc:mysql://208.91.198.197:3306/buddyconnect";
   final static String user = "buddyszz";
   final static String pass = "Admin@123";
         static Statement stmt = null;
         static Connection conn = null;
  public static int AccessCode = 0; 
         static Buddy buddy[];
        
        public static void getConnect() // cooncet with database 
        { 
            try 
            {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URl, user, pass);
                stmt = conn.createStatement();
            } catch(Exception e) 
            {
                
            }
        }
        
        public static boolean authnticateUser(String uid,String pass) // authnticate user with id and pass fro login to system
        {
        try
        {
            getConnect();
            String sql = "SELECT * from user_authentication where userid = '" + uid + "' and password = '" + pass + "'";
            ResultSet rs = stmt.executeQuery(sql);
              if(rs.next())
              {
                  return true;
              }
        }catch(Exception e) {
            return false;
        }
        finally
        {
            closeConnection();
        }
        return false;
       }
        
         public static boolean checkUserExist(String uid)
        {
            boolean status = false;
            try
            {
                getConnect();
                String sql = "SELECT * from user_details where userid = '" + uid + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next())
                {
                      status = true;
                }
            }catch(Exception e) {
               status = false;
            }
            finally
            {
                closeConnection();
            } 
            return status;
        }

         
        public static String getFname(String uid)
        {
            String fName = "";
            getConnect();
            String sql = "SELECT fname from user_details where userid = '" + uid + "'";
            ResultSet rs;
            try 
            {
                rs = stmt.executeQuery(sql);
                if(rs.next())
                {
                    fName = rs.getString("fname");
                }
            } catch (SQLException ex) {}
            finally { closeConnection(); }
            return fName;
        }
   
        public static String getLname(String uid)
        {
            String lName = "";
            getConnect();
            String sql = "SELECT lname from user_details where userid = '" + uid + "'";
            ResultSet rs;
            try 
            {
                rs = stmt.executeQuery(sql);
                if(rs.next())
                {
                    lName = rs.getString("fname");
                }
            } catch (SQLException ex) {}
            finally { closeConnection(); }
            return lName;
        }
          
        public static void updateSessionStatusTRUE(String uid,String bid) // set access_permission true for connecting
        {
             getConnect();
             String query = "UPDATE session_details SET access_permission='Yes' WHERE buddyId='" + uid + "' AND userId='" + bid + "' AND access_permission='Pending'";
             try { 
                stmt.executeUpdate(query);
             }
             catch(SQLException e) {System.out.println(e.toString());}
             finally {  closeConnection(); }
        }
        
        public static void updateUserLocation(String uid,String loc)
        {
            getConnect();
            String query = "UPDATE `user_location` SET loc='" + loc + "' WHERE user_id='" + uid + "'";
            try{
                stmt.executeUpdate(query);
            }
            catch(SQLException e){}
            finally { closeConnection(); }
        }
        
        public static void updateSessionStatusFALSE(String uid) // set acce_permission flase for cancel all request
        {
             getConnect();
             String query = "UPDATE session_details SET access_permission='No',complete_status='Yes' WHERE buddyId = '" + uid + "' AND access_permission='Pending'";
             try { 
                stmt.executeUpdate(query);
             }
             catch(SQLException e) {System.out.println(e.toString());}
             finally {  closeConnection(); }
        }
        
        public static void updateUserStatusOffline(String uid) //put user online status offline or last seen at this this this date
        {
            getConnect();
             String query = "UPDATE user_authentication SET online_status = 'Last seen at " + Common.getCurrentDateTime() + "' WHERE userid = '" + uid + "'";
             try { 
                stmt.executeUpdate(query);
             }
             catch(SQLException e) {}
             finally {  closeConnection(); }
        }
        
        public static void updateUserStatusBusy(String uid) //put user status busy
        {
            getConnect();
             String query = "UPDATE user_authentication SET user_status='Busy' WHERE userid='" + uid + "'";
             try { 
                stmt.executeUpdate(query);
             }
             catch(SQLException e) {}
             finally {  closeConnection(); }
        }
        
        public static void updateUserStatusFree(String uid) // put user status free
        {
            getConnect();
            String query = "UPDATE user_authentication SET user_status='Free' WHERE userid='" + uid + "'";
            try{
                stmt.executeQuery(query);
            }
            catch(SQLException e) {}
            finally{ closeConnection();}
        }
        
        public static boolean authnticateBuddyAceessCode(String bid,String code) // authnticate user by buddy id and buddy code1
        {
            getConnect();
                String query = "SELECT * FROM user_authentication WHERE userid = '" + bid + "' and current_accesscode = '" + code + "'";
                ResultSet rs = null;
                try
                {
                    rs = stmt.executeQuery(query);
                    if(rs.next()) {
                        return true;
                    }
                }
                catch(SQLException e) { }
                finally {  closeConnection(); }
                return false;
        }
        
        public static boolean checkUserStatus(String userId) // check user status whether he/she is free or not
        {
            boolean status = false;
            getConnect();
            String query = "SELECT * FROM user_authentication WHERE userid = '" + userId + "' and online_status='Online' and user_status='Free'";
            ResultSet rs = null;
            try
            {
            rs = stmt.executeQuery(query);
                if(rs.next()){
                    status = true;
                }
            }catch(SQLException e) { }
            finally { closeConnection(); }
            return status;
        }
               
        public static int getNumRows(String query) //return num of rows that are in table based on query
        {
            int count = 0;
            getConnect();
            ResultSet rs;
            try
            {
                rs = stmt.executeQuery(query);
                while(rs.next()){
                count = rs.getInt(1);
                }
                rs.close();
            }catch(Exception e){}
            finally { closeConnection(); }
            return count;
        }
        
        public static void closeConnection() // used for closing connection from database
        {
            if(stmt!=null)
            {
                while(true)
                {
                    try
                    {
                        stmt.close();
                        break;
                    }
                    catch(Exception e)
                    {}
                }
            }
        
            if(conn!=null)
            {
                while(true)
                {
                    try
                    {
                        conn.close();
                        break;
                    }
                    catch(Exception e)
                    {

                    }
                }
            }
     }
     
        
        public static ArrayList<Object> getFriendList(String userId) //return friend list based on user id
        {
        int index = 0;
        ArrayList<Object> values = new ArrayList<Object>(10);
        String query = "SELECT count(userid) FROM user_authentication WHERE userid IN (SELECT buddyid FROM friend_list WHERE userid = '" + userId + "' AND request_access='Allowed') AND online_status='Online'";
        int row = getNumRows(query);
        buddy = new Buddy[row];
        String query2 = "SELECT * FROM user_details WHERE userid IN (SELECT userid FROM user_authentication WHERE userid IN (SELECT buddyid FROM friend_list WHERE userid = '" + userId + "' AND request_access='Allowed')AND online_status='Online' )";
            
            ResultSet rs;
           
               index = 0;
                try 
                {
                    getConnect();
                    rs = stmt.executeQuery(query2);
                    while(rs.next())
                    {
                       buddy[index] = new Buddy();
                       buddy[index].setFName(rs.getString("fname"));
                       buddy[index].setLName(rs.getString("lname"));
                       buddy[index].setID(rs.getString("userid"));
                       buddy[index].setfnlName(rs.getString("fname"),rs.getString("lname"));
                       values.add(buddy[index].getfnlName());
                    index++;
                   }
                  
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    //System.out.println("Error  in getFeindlist : " + ex.toString());
                    
                }
                finally {
                    closeConnection();
                }
           
             return values; 
        }
        
        public static Buddy[] getBuddyObjectList() // return buddy list
        {
            return buddy;
        }
    
        public static void updateDataAuthnetication(String userId) // user status will be free , online _ status will be online and current accesscoe is generated randomly
        {
            getConnect();
            String query = "UPDATE user_authentication SET user_status='Free',online_status='Online',current_accesscode='" + Common.generateAccessCode() +"' WHERE userid ='" + userId + "'";
            try 
            {
            stmt.executeUpdate(query);
            }catch(SQLException e) {
             
             e.printStackTrace();
            }
            finally {
                closeConnection();
            }
        }   
       
     /*   public static void updateAccessCode(String userId)
        {
            getConnect();
            String query = "UPDATE user_authentication SET user_status='Free',online_status='Online',current_accesscode='" + Common.generateAccessCode() +"' WHERE userid ='" + userId + "'";
            try 
            {
                stmt.executeUpdate(query);
            }catch(SQLException e) 
            {
                e.printStackTrace();
            }
            finally 
            {
                closeConnection();
            }
        }
        */
        
        public static void insertDataUserlog(String userId) // insert data in user_log table while user starts his/her system
        {
            try 
            {
                String newDate = Common.getCurrentDateTime();
                String ip_local = Common.getLocalAddress();
                String ip_network = Common.getNetworkIp();
                String mac_id = Common.getMacAddress();
    
                getConnect();
                String query="INSERT INTO user_log VALUES ('" + userId+ "', '" + newDate + "','" + ip_local + "','" + ip_network + "','" + mac_id + "')";
                try
                {
                    stmt.executeUpdate(query);
                }catch(SQLException e) {
                
                }finally{ closeConnection(); }
            }
            catch (Exception ex) 
            {
                
            }
    }
}