package classlib;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class PortDetails 
{
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static String DB_URL = "jdbc:mysql://208.91.198.197:3306/buddyconnect";
    //String DB_URL = "jdbc:mysql://localhost:9090/test";

    static String USER = "serverB";
    static String PASS = "Admin@123";
    static Connection conn = null;
    static Statement stmt = null;
    
    
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
            System.exit(1);
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
            //JOptionPane.showMessageDialog(null,"Error in getuserPort : " + e.toString());
            System.exit(1);
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
            String sql = "UPDATE session_details SET access_permission ='" + access + "' , complete_status='" + status + "' WHERE userId='" + userId + "' AND buddyId='" + buddyId + "' AND complete_status='No'";
            stmt.executeUpdate(sql);
            sql = "UPDATE session_details SET access_permission ='" + access + "' , complete_status='" + status + "' WHERE userId='" + buddyId + "' AND buddyId='" + userId + "' AND complete_status='No'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null, "Error in update Access ");
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
     
   
    public static int getOnlineUsers()
    {
        int cnt = 0;
        String query = "select count(userid) as cnt from user_authentication where online_status='Online'";
        try
        {
            connectDb();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                cnt = rs.getInt("cnt");
                //System.out.println(cnt);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in getOnlineUsers: " + e.toString());
        }
        finally
        {
            closeConnection();
        }
        return cnt;
    }
    
    public static boolean insertPort(String user,String buddy,int port,int oper, String access)
    {
        try
        {
            connectDb();
            String sql = "INSERT INTO session_details VALUES (NULL,'" + user + "','" + buddy + "','" + port + "','" + getCurrentDateTime() + "','" + access + "'," + oper + ",'No')";
            stmt.executeUpdate(sql);
        }
        catch(Exception e)
        {
            System.out.println("Error : " + e.toString());
        }
        finally
        {
            closeConnection();
        }
        return true;
    }
    
    public static String getCurrentDateTime()
    {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return format.format(date);
    }
    
    public static String getCurrentDateTimeFile()
    {
        DateFormat format = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
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