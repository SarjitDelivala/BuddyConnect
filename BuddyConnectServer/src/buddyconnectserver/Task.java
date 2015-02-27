package buddyconnectserver;

import javax.swing.SwingWorker;

/**
 *
 * @author SARJIT
 */
public class Task extends SwingWorker<Void, Void>
{
    static int progress;
    public static void setValue(int val)
    {
        progress = val;
    }
    
    @Override
    public Void doInBackground()
    {
        while(true)
        {
            ServerScreen.jProgressBar1.setValue(progress);
            if(progress==100)
            {
                break;
            }
        }
        
        return null;
    }
    
    @Override
    public void done()
    {
        ServerScreen.setText();
    }
}
