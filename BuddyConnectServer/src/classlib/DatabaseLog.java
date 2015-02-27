/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



/**
 *
 * @author SARJIT
 */
public class DatabaseLog 
{
    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static String DB_URL = "jdbc:mysql://208.91.198.197:3306/buddyconnect";
    //String DB_URL = "jdbc:mysql://localhost:9090/test";

    static String USER = "serverB";
    static String PASS = "Admin@123";
    static Connection conn = null;
    static Statement stmt = null;
    public static void connectDb() throws Exception
    {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
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
    
    public static void logChat(String msg,String user,String buddy)
    {
        String date = PortDetails.getCurrentDateTime();
        String sql;
        sql = "INSERT INTO chat_log values('" + user + "','" + buddy + "','" + msg + "','" + date + "')";
        try
        {
            connectDb();
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
    }
    
    public static void logRDC(String user,String buddy,String url)
    {
        String sql;
        
        sql = "INSERT INTO rdc_log values('" + user + "','" + buddy + "','" + PortDetails.getCurrentDate() + "','" + url + "','" + PortDetails.getCurrentTime() + "')";
        try
        {
            connectDb();
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
    }
    
    public static void logFile(String user,String buddy,String url)
    {
        String sql;
        
        sql = "INSERT INTO file_log values('" + user + "','" + buddy + "','" + url + "','" + PortDetails.getCurrentDateTime() + "')";
        try
        {
            connectDb();
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
    }    
}
