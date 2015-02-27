/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classlib;

import buddyconnect.Home;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author SJPVAPCPCPP
 */

public class Common 
{
    static String nip;
    static String city;
    static String state;
    static String country;
    static String pincode;
    static String shortCountryCode;
    
    
    public static String generateAccessCode()  // generate random access code
    {
            Integer number = 0;
            Random r = new Random();
            number = ((int)(r.nextInt(1000)) + (int)(r.nextInt(2000)) + (int)(r.nextInt(1500)) + (int)(r.nextInt(5000)));
            DatabaseData.AccessCode = number;
            Home.lblAccess.setText(Integer.toString(DatabaseData.AccessCode));
            return number.toString();
    }
    
    public static String getNetworkIp() // return network ip address
    {
        try 
        {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            String ip = in.readLine();
            in.close();
            return ip;
        }catch(Exception e){}
        return null;
      }
    
    public static String getMacAddress() // return mac address
    {
            InetAddress ip;
            try 
            {
		ip = InetAddress.getLocalHost();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                byte[] mac = network.getHardwareAddress();
                StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		}
		return sb.toString();
            }catch(Exception e){}
        return null;
        }
    
    public static String getCurrentDateTime() // return date nd time in yyyy/MM/dd HH:mm:ss format
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dt = new Date();
        String strDate = sdf.format(dt);
        return strDate;
        }
    
    public static String getLocalAddress() // return loacl adddress of pc
    {
            try {
            return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) {}
        return null;
        }
    
    public static void getLocations()
    {
      URL url;
      BufferedReader in;
      
        try 
        {
            url = new URL("http://checkip.amazonaws.com");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String ip = in.readLine();
            nip = ip; //network ip is set
            in.close();  
            
            url = new URL("http://api.ipinfodb.com/v3/ip-city/?key=1fdf558a9508e909bf40690e972ac372f7a4d56c80619ebc5b1b63b3886082ec&ip="+ip);
            in = null;
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String info = in.readLine();
            in.close();
    
            getSubLocation(info);
        }
        catch (Exception ex)
        {
        } 
    }
    
    public static void getSubLocation(String info)
    {
        String ipInfo = info.substring(4,info.length()-1);
        StringTokenizer str = new StringTokenizer(ipInfo);
        int i =0;
        while(str.hasMoreTokens())
        {
            switch(i)
            {
                case 1:
                    shortCountryCode = str.nextToken(";");
                    break;
                case 2:
                    country = str.nextToken(";");
                    break;
                case 3:
                    state = str.nextToken(";");
                    break;
                case 4:
                    city = str.nextToken(";");
                    break;
                default:
                    String tp2 = str.nextToken(";");
                    break;
            }
        i++;
        }
    }
    
     public static String getCountry()
    {
        return country;
    }
     
    public static String getCountryCode()
    {
        return shortCountryCode;
    }
    
    public static String getCity()
    {
        return city;
    }
     
    public static void hideForm(JFrame frm)
    {
        frm.removeAll();
        frm.dispose();
    }
    
    public static String changepathtoString(String path) //convert from string path to file path
    {
      String str = path;
      str = str.substring(3, str.length()-1);
      str = str.replace(":\\", ":");
      str = str.replace(", ", "\\");
      str = str.trim();
      return str;
    }
    
    public static void setInternetLabel(JLabel lblInternet)
    {
        lblInternet.setOpaque(true);
        Color c = new Color(51, 51, 51);
        lblInternet.setBackground(c);
    }
      
    public static void setIcon(JFrame frame)
    {
        try 
        {
            frame.setIconImage(ImageIO.read(new File("collection\\bc.ico")));
        }
        catch (IOException ex) 
        {
        }
    }
    
    public static void setCenterGravity(JFrame frame)
    {
         Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
         int h = ((int)d.height/2);
         int w = ((int)d.width/2);
         frame.setBounds(w-350,h-300,JFrame.WIDTH,JFrame.HEIGHT);
    }
    
    public static void setClosingEvent(final JFrame frm,final UserSession session,final String val) 
    {
        frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frm.addWindowListener(new java.awt.event.WindowAdapter() 
        {
            @Override
             public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(val.equals("Request"))
                {
                    if (JOptionPane.showConfirmDialog(frm,"Closing The " + val + " Screen, All request will be canceled \n Do you want to Continue ?", "Closing the " + val + " Form",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        Common.hideForm(frm);
                        DatabaseData.updateSessionStatusFALSE(session.getUser());
                    } 
                }
                else
                {
                    if (JOptionPane.showConfirmDialog(frm,"Do you want to close The " + val + " Form ?", "Closing the " + val + " Screen ",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        Common.hideForm(frm);
                        Home h = new Home(session.getUser());
                        h.start();
                    }
                }
        }
        });
    }
      
    public static void closeIncomeThread()
    {
        Home.income.interrupt(); // wheneever chat button is clicked incoming thrad is closes
    }
      
    public static void closeBuddyListThread()
    {
        Home.buddies.interrupt();
    }
}