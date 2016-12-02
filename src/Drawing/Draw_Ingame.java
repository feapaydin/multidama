package Drawing;

import Global.Game;
import Global.Draw;
import java.awt.Graphics;
import Elements.Grid;
import Controller.Controller_Ingame;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Draw_Ingame extends Draw {
    
    private BufferedImage img;

    public void paint(Graphics g){
        
        ///Masa Çizimi
        g.drawRect(Controller_Ingame.GridDrawStartPointX-1,Controller_Ingame.GridDrawStartPointY-1,Controller_Ingame.TableSize+1,Controller_Ingame.TableSize+1);
        
        
        ///Development Build
        g.setColor(Color.BLACK);
        g.drawString("Development Build", 5, 15);
        
        ///Grid Çizimi
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
            
           
            
        }
        
        
        
        
    }//End paint
    
    
    
    
}
