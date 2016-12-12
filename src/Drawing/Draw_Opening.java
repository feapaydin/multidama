package Drawing;

import Global.Draw;
import Global.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Draw_Opening extends Draw{
    
    public Draw_Opening(){
        repaint();
    }

    
    public void paint(Graphics g) {
       
        BufferedImage img;
        try {               
            img=ImageIO.read(getClass().getResourceAsStream("/arc/img/forms/form_opening.jpg"));
            g.drawImage(img,0,0,Game.GameWindow.content_width,Game.GameWindow.content_height,null);
        } catch (IOException ex) {}
        
    }
    
}
