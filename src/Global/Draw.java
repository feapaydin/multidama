package Global;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public abstract class Draw extends JPanel{
      
    public Draw(){        
        repaint();          
    }
    
    public abstract void paint(Graphics g);
    
    
    
}
