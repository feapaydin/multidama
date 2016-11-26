package Drawing;

import Global.Game;
import Global.Draw;
import java.awt.Color;
import java.awt.Graphics;
import Elements.Grid;
import Controller.Controller_Ingame;


public class Draw_Ingame extends Draw{
    

    public void paint(Graphics g){
        
        ///Masa Çizimi
        g.drawRect(Controller_Ingame.GridDrawStartPointX-1,Controller_Ingame.GridDrawStartPointY-1,Controller_Ingame.TableSize+1,Controller_Ingame.TableSize+1);
        
        ///Grid Çizimi
        for(int i=0; i<Controller_Ingame.GridList.length; i++)
        {
            Grid currentGrid=Controller_Ingame.GridList[i];
            
            g.setColor(currentGrid.bgColor);
            g.fillRect(currentGrid.drawCoordX,currentGrid.drawCoordY,Controller_Ingame.GridSize,Controller_Ingame.GridSize);
            
            g.setColor(Color.RED);
            g.drawString(Integer.toString(currentGrid.ID), currentGrid.drawCoordX+5, currentGrid.drawCoordY+15);
            g.drawString(currentGrid.posX+","+currentGrid.posY, currentGrid.drawCoordX+5, currentGrid.drawCoordY+30);
            
            //BURAYA SWITCH ILE TAS RESMI GELECEK
            
        }
        
        
        
        
    }//End paint
    
    
    
    
}
