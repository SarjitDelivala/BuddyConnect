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
public class TransferDetails implements Serializable{
    
    String name;
    long size;
    String url;
    
    
    public void setDetails(String name,long size,String url)
    {
        this.name=name;
        this.size=size;
        this.url = url;
    }
    
    public String getName()
    {
        return name;
    }
    
    public long getSize()
    {
        return size;
    }
    
    public String getURL()
    {
        return url;
    }
    
}
