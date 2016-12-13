package Drawing;

import Controller.Controller_CreateRoom;
import Global.Game;
import Global.Draw;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Draw_CreateRoom extends Draw{
    
    public Draw_CreateRoom(){        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/Arc/font/georgia.ttf")));
        } catch (FontFormatException ex) {} catch (IOException ex) {}
        
        
        repaint();    
        
    }

    
    
    public void paint(Graphics g) {
        
        
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.GameWindow.window_width,Game.GameWindow.window_height);
        
              
        
        loadFormBackground("/Arc/img/forms/form_createroom.jpg",g);
        
        
        Font f=new Font("Georgia",Font.TRUETYPE_FONT,11);
        Color c=new Color(21,35,12);
        ortaliYazi("by. Furkan Enes Apaydın",Game.GameWindow.window_height-55,f,c,g);
        ortaliYazi("İstanbul Gelişim Üniversitesi - 2016",Game.GameWindow.window_height-40,f,c,g);
        
       
        ortaliYazi(Controller_CreateRoom.islemDurum,Game.GameWindow.window_height-155,f,Color.RED,g);
        
        
        Controller_CreateRoom.btnGeridon.repaint();
        Controller_CreateRoom.btnOlustur.repaint();
        
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
