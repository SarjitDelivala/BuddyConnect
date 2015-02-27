/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import classlib.RDCDetails;
import java.awt.event.InputEvent;

/**
 *
 * @author SARJIT
 */
public class RDCEventThread extends Thread
{
    int keyCode,mouseBtn,mouseClicks,mouseOper,mouseX,mouseY;
    boolean shift,alt,ctrl;

    public RDCEventThread(int keyCode, int mouseBtn, int mouseClicks, int mouseOper, int mouseX, int mouseY, boolean shift, boolean alt, boolean ctrl) 
    {
        this.keyCode = keyCode;
        this.mouseBtn = mouseBtn;
        this.mouseClicks = mouseClicks;
        this.mouseOper = mouseOper;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.shift = shift;
        this.alt = alt;
        this.ctrl = ctrl;
    }
    
    @Override
    public void run()
    {
        RDCDetails rdc = new RDCDetails();
        rdc.setAlt(alt);
        rdc.setCtrl(ctrl);
        rdc.setShift(shift);
        rdc.setKeyCode(keyCode);
        rdc.setMouseY(mouseY);
        rdc.setMouseX(mouseX);
        rdc.setMouseOper(mouseOper);
        rdc.setMouseClick(mouseClicks);
        switch(mouseBtn)
        {
            case 1:
            {
                rdc.setMouseBtn(InputEvent.BUTTON1_MASK);
                break;
            }
            case 2:
            {
                rdc.setMouseBtn(InputEvent.BUTTON2_MASK);
                break;
            }
            case 3:
            {
                rdc.setMouseBtn(InputEvent.BUTTON3_MASK);
                break;
            }
            default:
            {
                rdc.setMouseBtn(-1);
                break;
            }
        }
        RDCServerSendThread.setObject(rdc);
    }
}
