package Global;

import java.awt.Graphics;
import javax.swing.JPanel;

public abstract class Draw extends JPanel{
      
    public Draw(){
        setLocation(0,0);
        setSize(Game.GameWindow.window_width,Game.GameWindow.window_height);
        setVisible(true);
        repaint();          
    }
    
    public abstract void paint(Graphics g);
    
}
