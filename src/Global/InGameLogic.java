package Global;

import Controller.Controller_Ingame;
import Elements.*;
import java.util.ArrayList;

public class InGameLogic {

    public static Grid islenen;
    public static ArrayList<Grid> moveable=new ArrayList<Grid>(); 
    public static ArrayList<Grid> mustmove=new ArrayList<Grid>();
    
    public static void clickedOnGrid(Grid g){
        
        if(g.durum!=0)
        {
            if(g.owner.ID==Game.GamePlayer.ID)
                if(Game.PlayTurn==Game.GamePlayer.ID)
                {
                    islenen=g;            
                    checkMoveArea(g);
                }
        }
        
        Game.UpdateFrame();
    
    }
    
    
    
    
    public static void checkMoveArea(Grid g){
        moveable.clear();
        mustmove.clear();
        
        int distance=2;
        
        if(g.durum==1)
            distance=2;
        else if(g.durum==2)
            distance=Controller_Ingame.GridSayisi;
        
        isMoveable(g,"left",0,distance,false);
        isMoveable(g,"right",0,distance,false);
        isMoveable(g,g.owner.host?"up":"down",0,distance,false);
        if(g.durum==2)
            isMoveable(g,!g.owner.host?"up":"down",0,distance,false);
        
        if(!mustmove.isEmpty())
            moveable.clear();
    }
    
    
    
    public static int calculateNext(String yon){
        int yol=0;
        switch(yon)
        {
            case "left": yol=-1; break;
            case "right": yol=1; break;
            case "up": yol=-Controller_Ingame.GridSayisi; break;
            case "down": yol=Controller_Ingame.GridSayisi; break;
        }
        return yol;
    }
    
    
    
    public static void isMoveable(Grid g, String yon, int mevcutSira, int maxSira, boolean mustEaten)
    {
        if(g.durum==0)
        {
            if(!mustEaten)
                moveable.add(g);
            else            
                mustmove.add(g);
        }
        
        if(true)
        {            
            if(mevcutSira<maxSira)
            {          
                int nextGridId=g.ID+calculateNext(yon);
                                             
                if(nextGridId<Controller_Ingame.GridList.length && nextGridId>=0)
                {          
                    if((yon=="left" && g.posX==1) || (yon=="right" && g.posX==8))
                    {
                        return;
                    }
                    
                    Grid nextGrid=Controller_Ingame.GridList[nextGridId]; 
                    if(mevcutSira!=0)
                    {
                        
                        if(g.owner.ID==Game.Opponent.ID)
                        {                        
                            mustEaten=true;                                
                        }
                        else if(g.durum!=0 && g.owner.ID==Game.GamePlayer.ID)
                            return;
                        else if(islenen.durum!=2)
                            return;
                        
                    }

                    if(nextGrid.ID!=g.ID)
                        isMoveable(nextGrid,yon,mevcutSira+1,maxSira,mustEaten);                   
                }
            }
        }
    }
    


    
    
    
    
    
}
