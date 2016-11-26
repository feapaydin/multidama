package Controller;

import Drawing.Draw_Ingame;
import Elements.Grid;
import Global.Controller;
import Global.Game;
import java.awt.Color;


public class Controller_Ingame extends Controller{
           
    public static final int     GridSize=50;
    public static final int     GridDrawStartPointX=10;
    public static final int     GridDrawStartPointY=10;
    public static int           GridDrawPointX=GridDrawStartPointX;
    public static int           GridDrawPointY=GridDrawStartPointY;
    
    public static final int     GridSayisi=8;
    public static final int     TableSize=GridSize*GridSayisi;
    public static Grid[]        GridList=new Grid[GridSayisi*GridSayisi];
    
    
    public Controller_Ingame(){
    
        super(new Draw_Ingame());
        
        
        ///Grid Düzeni Oluşturma
        int gridId=0;
        for(int posY=1; posY<=GridSayisi; posY++)
        {
            for(int posX=1; posX<=GridSayisi; posX++)
            {
                
                Color gridColor=gridId%2==(posY%2)?Color.BLACK:Color.WHITE;
                GridList[gridId++]=new Grid(gridId,posX,posY,GridDrawPointX,GridDrawPointY,gridColor);
                GridDrawPointX+=GridSize;
                
            }
            
            GridDrawPointY+=GridSize;
            GridDrawPointX=GridDrawStartPointX;
        }       
        
    }
      
    
    
    
    //// ONCLICK
    public void onClick(){
       
        for(int i=0; i<GridList.length; i++)
        {           
           if(checkClick(GridList[i]))
           {
               Grid tiklanan=GridList[i];
               
               ////
               
               tiklanan.bgColor=Color.BLUE;
               Game.UpdateFrame();
               
               ////      
               
               break;
           }          
        }
         
    }    
    
    
    
    
    //Tıklanan koordinatların hangi gride denk geldiğini ölçer
    public static boolean checkClick(Grid g){        
        if
        (
            Game.Mouse.x>g.drawCoordX && Game.Mouse.x<g.drawCoordX+GridSize 
                && 
            Game.Mouse.y>g.drawCoordY && Game.Mouse.y<g.drawCoordY+GridSize
        )
            return true;
        else
            return false;
    }
    
    
    
}
