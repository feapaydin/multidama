package Elements;

import Global.Game;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JTextField;

public class TextField extends JTextField {
    
    public TextField(int posX, int posY){
        
        setSize(165,25);  
        setLocation(posX,posY);
        setBackground(new Color(39,39,14));
        //setBackground(Color.RED);
        setBorder(null);
        setForeground(Color.WHITE);
        
        
        repaint();
        
    }
    
    
    
    
    
}
