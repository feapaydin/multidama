package Drawing;

import Global.Game;
import Global.Draw;
import java.awt.Graphics;
import Elements.Grid;
import Controller.Controller_Ingame;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Draw_Ingame extends Draw {
    
    private BufferedImage img;
    
    
    public Draw_Ingame(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/Arc/font/georgia.ttf")));
        } catch (FontFormatException ex) {} catch (IOException ex) {}
        
    }
    

    public void paint(Graphics g){
        if(Game.Room!=null)
        {
            
            
            g.setColor(Color.BLACK);
            g.fillRect(0,0,Game.GameWindow.window_width,Game.GameWindow.window_height);


            loadFormBackground("/Arc/img/forms/form_game.jpg",g);


            //Oda bilgileri
            Font f1=new Font("Georgia",Font.CENTER_BASELINE,14);
            Color c1=new Color(160,131,64);
            ortaliYazi(Game.Room.GameName,137,f1,c1,g);


            //Kullanıcı Bilgileri
            try {
                img=ImageIO.read(getClass().getResourceAsStream("/Arc/img/forms/userBack.jpg"));
                g.drawImage(img,Game.GameWindow.window_width-img.getWidth()-30,300,img.getWidth(),img.getHeight(),null);
                g.drawImage(img,25,300,img.getWidth(),img.getHeight(),null);

            } catch (IOException ex) {}

            g.setColor(new Color(83,69,26));
            Font f=new Font("Georgia",Font.BOLD,16);
            g.setFont(f);

            FontMetrics metric=g.getFontMetrics(f);  
            g.drawString(Game.GamePlayer.name+" ["+Game.GamePlayer.tasSayisi+"]",Game.GameWindow.window_width-30-(img.getWidth()/2)-(metric.stringWidth(Game.GamePlayer.name+" ["+Game.GamePlayer.tasSayisi+"]")/2),350);
            
            if(Game.Room.Opponent!=null)
                g.drawString(Game.Room.Opponent.name+" ["+Game.Room.Opponent.tasSayisi+"]",25+(img.getWidth()/2)-(metric.stringWidth(Game.Room.Opponent.name+" ["+Game.Room.Opponent.tasSayisi+"]")/2),350);
            else
            {
                String yazi="BEKLENİYOR...";
                g.setColor(Color.GREEN);
                g.drawString(yazi,25+(img.getWidth()/2)-(metric.stringWidth(yazi)/2),350);
            }
            
            try {
                img=ImageIO.read(getClass().getResourceAsStream("/Arc/img/forms/siraTac.jpg"));
                if(Game.Room.PlayTurn==Game.GamePlayer.ID)
                {
                    g.drawImage(img,Game.GameWindow.window_width-img.getWidth()-30-47,260,img.getWidth(),img.getHeight(),null);
                    g.setColor(Color.RED);
                    g.drawString("Sıra Sizde...",Game.GameWindow.window_width-150,400);
                }
                else
                    g.drawImage(img,25+47,260,img.getWidth(),img.getHeight(),null);

            } catch (IOException ex) {}



            ///Grid Çizimi
            if(Controller_Ingame.GridList[0]!=null)
            for(int i=0; i<Controller_Ingame.GridList.length; i++)
            {
                
                Grid currentGrid=Controller_Ingame.GridList[i];  

                String yol="";
                g.setColor(currentGrid.bgColor);     

                if(Controller_Ingame.GameLogic.moveable.contains(currentGrid))
                    yol=Controller_Ingame.Grid_Moveable_Img;

                if(Controller_Ingame.GameLogic.mustmove.contains(currentGrid))
                    yol=Controller_Ingame.Grid_Mustmove_Img;

                 if(Controller_Ingame.GameLogic.islenen!=null)
                    if(Controller_Ingame.GameLogic.islenen.ID==currentGrid.ID)
                        yol=currentGrid.durum==1?Controller_Ingame.Grid_Click_Img:Controller_Ingame.Grid_Click_Dama_Img;

                g.fillRect(currentGrid.drawCoordX,currentGrid.drawCoordY,Controller_Ingame.GridSize,Controller_Ingame.GridSize);





                if(yol.length()==0)
                    if(currentGrid.owner!=null)
                        if(currentGrid.owner.ID==Game.GamePlayer.ID)
                            switch(currentGrid.durum)
                            {
                                case 0:
                                    yol=Controller_Ingame.Grid_Bos_Img;
                                    break;
                                case 1:
                                    yol=Controller_Ingame.Grid_Tas_Img;
                                    break;
                                case 2:
                                    yol=Controller_Ingame.Grid_Dama_Img;
                                    break;
                            }
                        else
                            switch(currentGrid.durum)
                            {
                                case 0:
                                    yol=Controller_Ingame.Grid_Bos_Img;
                                    break;
                                case 1:
                                    yol=Controller_Ingame.Grid_Tas_Dusman_Img;
                                    break;
                                case 2:
                                    yol=Controller_Ingame.Grid_Dama_Dusman_Img;
                                    break;
                            }




                try
                {
                    img=ImageIO.read(getClass().getResourceAsStream(yol));               
                    g.drawImage(img,currentGrid.drawCoordX,currentGrid.drawCoordY,Controller_Ingame.GridSize,Controller_Ingame.GridSize,null);
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
                
                /*
                //Grid Data
                g.setColor(Color.BLUE);
                g.setFont(new Font("Arial",Font.PLAIN,11));
                g.drawString(currentGrid.posX+","+currentGrid.posY+" ("+currentGrid.ID+")",currentGrid.drawCoordX+5,currentGrid.drawCoordY+15);
                g.drawString(currentGrid.durum+"",currentGrid.drawCoordX+5,currentGrid.drawCoordY+30);
                if(currentGrid.owner!=null)
                    g.drawString(currentGrid.owner.name+"",currentGrid.drawCoordX+5,currentGrid.drawCoordY+45);
                */
                
            }

            Controller_Ingame.btnPeset.repaint();
            Controller_Ingame.btnLobiyedon.repaint();
        }
        
    }//End paint
    
    
    
    
    
    public void loadFormBackground(String yol,Graphics g){
        BufferedImage img;
        try {               
            img=ImageIO.read(getClass().getResourceAsStream(yol));
            g.drawImage(img,(Game.GameWindow.window_width-img.getWidth())/2,0,img.getWidth(),img.getHeight(),null);
        } catch (IOException ex) {}
    }
    
     public void ortaliYazi(String yazi, int y, Font f, Color c, Graphics g)
    {
        FontMetrics metric=g.getFontMetrics(f);  
        g.setFont(f);
        g.setColor(c);     
        g.drawString(yazi,(Game.GameWindow.window_width-metric.stringWidth(yazi))/2,y);
    }
    
    
    
    
}
