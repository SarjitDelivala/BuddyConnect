package buddyconnect;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author SARJIT
 */
public class Task extends SwingWorker<Void, Void>
{
    long progress;
    long max;
    JProgressBar bar;

    public Task(JProgressBar bar,long max) 
    {
        this.bar = bar;
        this.max = max;
    }
    
    
    
    public void setValue(long val)
    {
        progress = val;
    }
    
    @Override
    public Void doInBackground()
    {
        while(true)
        {
            bar.setValue( (int) progress);
            if(progress==max)
            {
                break;
            }
        }
        
        return null;
    }
    
    @Override
    public void done()
    {
        bar.setVisible(false);
    }
}
