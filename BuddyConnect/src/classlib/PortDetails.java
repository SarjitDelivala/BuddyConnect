package classlib;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PortDetails 
{
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static String DB_URL = "jdbc:mysql://208.91.198.197:3306/buddyconnect";
    //String DB_URL = "jdbc:mysql://localhost:9090/test";

    static String USER = "clientB2";
    static String PASS = "Admin@123";
    static Connection conn = null;
    static Statement stmt = null;
    static Buddy buddy[];
    
    public static void connectDb()
    {
        try
        {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        }catch(Exception e){}
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
                {
                    
                }
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
    
    public static String getBuddyLocation(String buddyId)
    {
        connectDb();
        try
        {
            String sql = "SELECT loc FROM user_location WHERE userid = '" + buddyId + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                return rs.getString("loc");
            }
        }
        catch(Exception e)
        {
        }
        return "";
    }
    
    public static String setUserName(String userId)
    {
        connectDb();
        try
        {
            String sql = "SELECT fname FROM user_details WHERE userid = '" + userId + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                return rs.getString("fname");
            }
        }
        catch(Exception e)
        {
            
        }
        return "";
    }
     
    public static String getServerIP()
    {
        String ip="";
        try
        {
            Thread.sleep(500);
            connectDb();
            String sql = "SELECT ipaddr FROM serverIP";
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                ip = rs.getString("ipaddr");
            }
        }
        catch(Exception e)
        {
            ip="";
        }
        finally
        {
            closeConnection();
        }
        return ip;
    }    
    
    public static void setServerIP(String ip)
    {
        try
        {
            connectDb();
            String sql="UPDATE serverIP SET ipaddr='" + ip + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            System.out.println("Server IP Set Error : " + e.toString());
        }
        finally{
            closeConnection();
        }
        
    }
    
    public static int getUserPort(String userId,String buddyId)
    {
        int port=0;
        try
        {
            Thread.sleep(500);
            connectDb();
            String sql = "SELECT * FROM session_details WHERE userId='" + userId +"' AND buddyId = '" + buddyId + "' AND complete_status = 'No'";
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                port = rs.getInt("serverport");
            }
        }
        catch(Exception e)
        {
            port =0;
        }
        finally
        {
            closeConnection();
        }
        return port;
    }
    
    public static void updateAccess(String userId,String buddyId,String access,String status)
    {
        try
        {
            connectDb();
            String sql = "UPDATE session_details SET access_permission ='" + access + "' , complete_status='" + status + "' WHERE userId='" + userId + "' AND buddyId='" + buddyId + "'";
            stmt.executeUpdate(sql);
            sql = "UPDATE session_details SET access_permission ='" + access + "' , complete_status='" + status + "' WHERE userId='" + buddyId + "' AND buddyId='" + userId + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
        }
        finally
        {
            closeConnection();
        }
    }
    
    public static int getNumRows(String query) //return num of rows that are in table based on query
    {
            int count = 0;
            connectDb();
            ResultSet rs;
            try
            {
                rs = stmt.executeQuery(query);
                while(rs.next()){
                count ++;
                }
                rs.close();
            }catch(Exception e){}
            finally { closeConnection(); }
            return count;
        }
     
    public static ArrayList<Object> getList(String user)
    {
        ArrayList<Object> list = new ArrayList<Object>(10);
        String query = "SELECT `user_details`.`fname`,`operation_master`.`oid`,`user_details`.`lname`,`user_details`.`userid`,`operation_master`.`operation` FROM `user_details` INNER JOIN  `session_details` ON  `user_details`.`userid` =  `session_details`.`userid` INNER JOIN  `operation_master` ON  `operation_master`.`oid` =  `session_details`.`operation` WHERE  `session_details`.`buddyid` =  '" + user + "' AND  `session_details`.`access_permission` =  'Pending'";
        String query2 = "SELECT count(*) FROM `user_details` INNER JOIN  `session_details` ON  `user_details`.`userid` =  `session_details`.`userid` INNER JOIN  `operation_master` ON  `operation_master`.`oid` =  `session_details`.`operation` WHERE  `session_details`.`buddyid` =  '" + user + "' AND  `session_details`.`access_permission` =  'Pending'";
        int row = getNumRows(query2);
        
        buddy = new Buddy[row];
        int index=0;
         boolean flag = false;
                ResultSet rs;
                try 
                {
                    index = 0;
                    connectDb();
                    rs = stmt.executeQuery(query);
                    while(rs.next())
                    {
                        buddy[index] = new Buddy();
                        buddy[index].setFName(rs.getString("fname"));
                        buddy[index].setLName(rs.getString("lname"));
                        buddy[index].setID(rs.getString("userid"));
                        buddy[index].setfnlName(rs.getString("fname"),rs.getString("lname"));
                        buddy[index].setFLO(rs.getString("fname"),rs.getString("lname"),rs.getString("operation"));
                        buddy[index].setOperation(rs.getString("operation"));
                        buddy[index].setOperation(rs.getInt("oid"));
                        list.add(buddy[index].getFLO());
                        index++;
                    }
                    flag=true;
                }
                catch (Exception ex) {
                    flag = false;
                }
                finally {
                    closeConnection();
                }
           
            return list;
    }
    
    public static Buddy[] getBuddyObjectList() // return buddy list
    {
            return buddy;
    }
    
    
    
    public static String getCurrentDateTime()
    {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return format.format(date);
    }
    
    public static String getCurrentDate()
    {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new java.util.Date();
        return format.format(date);
    }
    
    public static String getCurrentTime()
    {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return format.format(date);
    }
}