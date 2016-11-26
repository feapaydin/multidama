package Global;

import java.awt.Graphics;
import javax.swing.JPanel;

public abstract class Draw extends JPanel{
      
    public Draw(){        
        repaint();    
    }
    
    public abstract void paint(Graphics g);
    
}
