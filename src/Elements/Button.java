package Elements;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Button extends JButton{
 
    public Button(String metin,int posX, int posY, int width, int height){
        
        setSize(width,height);
        setLocation(posX,posY);
        
        Font f=new Font("Georgia",Font.PLAIN,11);
        setFont(f);
        
        setText(metin);
        
        setFocusable(false);
        
        setBorder(new LineBorder(new Color(79,61,36)));
                
        setForeground(new Color(160,131,64));
        setBackground(new Color(44,10,1));
        
    }
    
}
