package Drawing;

import Controller.Controller_Login;
import Global.Game;
import Global.Draw;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Draw_Login extends Draw{
    
    public Draw_Login(){        
        repaint();    
    }

    
    
    public void paint(Graphics g) {
        
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.GameWindow.window_width,Game.GameWindow.window_height);
        
        g.setColor(Color.WHITE);
        g.drawString("Development Build",5,15);
        
        
        loadFormBackground("/arc/img/forms/form_login.jpg",g);
        
        
        Font f=new Font("Georgia",Font.ROMAN_BASELINE,11);
        Color c=new Color(21,35,12);
        ortaliYazi("by. Furkan Enes Apaydın",Game.GameWindow.content_height-28,f,c,g);
        ortaliYazi("İstanbul Gelişim Üniversitesi - 2016",Game.GameWindow.content_height-10,f,c,g);
        
       
        ortaliYazi(Controller_Login.islemDurum,Game.GameWindow.content_height-130,f,Color.RED,g);
        
        
    }
    
    
    
    
    public void ortaliYazi(String yazi, int y, Font f, Color c, Graphics g)
    {
        FontMetrics metric=g.getFontMetrics(f);        
        g.setFont(f);        
        g.setColor(c);        
        g.drawString(yazi,(Game.GameWindow.window_width-metric.stringWidth(yazi))/2,y);
         
    }
    
    public void loadFormBackground(String yol,Graphics g){
        BufferedImage img;
        try {               
            img=ImageIO.read(getClass().getResourceAsStream(yol));
            g.drawImage(img,(Game.GameWindow.window_width-img.getWidth())/2,50,img.getWidth(),img.getHeight(),null);
        } catch (IOException ex) {}
    }
    
}
