package Drawing;

import Controller.Controller_Lobby;
import static Controller.Controller_Lobby.GameList;
import Controller.Controller_Login;
import Elements.LobbyGame;
import Global.Draw;
import Global.Game;
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


public class Draw_Lobby extends Draw{
    
    public Draw_Lobby(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/Arc/font/georgia.ttf")));
        } catch (FontFormatException ex) {} catch (IOException ex) {}
        
        
        
        repaint();
    }


    public void paint(Graphics g) {
        
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.GameWindow.window_width,Game.GameWindow.window_height);

        loadFormBackground("/Arc/img/forms/form_lobby.jpg",g);
        
        
        
        Font f1=new Font("Georgia",Font.BOLD,13);
        Color c1=new Color(160,131,64);
        ortaliYazi("Hoşgeldiniz, "+Game.GamePlayer.name+".",188,f1,c1,g,-190);
        
        
        Font f=new Font("Georgia",Font.TRUETYPE_FONT,11);
        Color c=new Color(21,35,12);
        ortaliYazi("by. Furkan Enes Apaydın",Game.GameWindow.window_height-55,f,c,g);
        ortaliYazi("İstanbul Gelişim Üniversitesi - 2016",Game.GameWindow.window_height-40,f,c,g);       
       
        Controller_Lobby.btnBaglan.repaint();
        Controller_Lobby.btnYarat.repaint();
        Controller_Lobby.btnYenile.repaint();
        
        for(int i=0; i<GameList.size(); i++)
        {
            LobbyGame room=(LobbyGame)GameList.get(i);
            room.repaint();
        }
    }
    
    
    
    public void ortaliYazi(String yazi, int y, Font f, Color c, Graphics g)
    {
        
        FontMetrics metric=g.getFontMetrics(f);  
        g.setFont(f);
        g.setColor(c);     
        g.drawString(yazi,(Game.GameWindow.window_width-metric.stringWidth(yazi))/2,y);
    }
    
    public void ortaliYazi(String yazi, int y, Font f, Color c, Graphics g, int fark)
    {
        
        FontMetrics metric=g.getFontMetrics(f);  
        g.setFont(f);
        g.setColor(c);     
        g.drawString(yazi,((Game.GameWindow.window_width-metric.stringWidth(yazi))/2)+fark,y);
    }
    
    public void loadFormBackground(String yol,Graphics g){
        BufferedImage img;
        try {               
            img=ImageIO.read(getClass().getResourceAsStream(yol));
            g.drawImage(img,(Game.GameWindow.window_width-img.getWidth())/2,50,img.getWidth(),img.getHeight(),null);
        } catch (IOException ex) {}
    }
    
    
    
}
