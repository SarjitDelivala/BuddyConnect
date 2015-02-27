package classlib;

import java.io.Serializable;

public class RDCDetails implements Serializable
{
    int mouseX;
    int mouseY;
    int keyCode;
    int mouseClick;
    int mouseBtn;
    int mouseOper;
    boolean ctrl,alt,shift;
    
    public void setMouseX(int x)
    {
        this.mouseX = x;
    }
    
    public void setMouseY(int y)
    {
        this.mouseY = y;
    }
    
    public void setKeyCode(int code)
    {
        this.keyCode = code;
    }
    
    public void setMouseClick(int click)
    {
        this.mouseClick = click;
    }
    
    public void setMouseBtn(int btn)
    {
        this.mouseBtn = btn;
    }
    
    public void setMouseOper(int oper)
    {
        this.mouseOper = oper;
    }
    
    public void setCtrl(boolean ctrl)
    {
        this.ctrl = ctrl;
    }
    
    public void setAlt(boolean alt)
    {
        this.alt = alt;
    }
    
    public void setShift(boolean shft)
    {
        this.shift = shft;
    }
    
    public int getMouseX()
    {
        return mouseX;
    }
    
    public int getMouseY()
    {
        return mouseY;
    }
    
    public int getKeyCode()
    {
        return keyCode;
    }
    
    public int getMouseBtn()
    {
        return mouseBtn;
    }
    
    public int getMouseClick()
    {
        return mouseClick;
    }
    
    public int getMouseOper()
    {
        return mouseOper;
    }
    
    public boolean getCtrl()
    {
        return ctrl;
    }
    
    public boolean getAlt()
    {
        return alt;
    }
    
    public boolean getShift()
    {
        return shift;
    }
    
}
