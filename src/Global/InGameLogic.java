package Global;

import Controller.Controller_Ingame;
import Elements.*;
import java.util.ArrayList;

public class InGameLogic {

    public static Grid islenen;
    public static ArrayList<Grid> moveable=new ArrayList<Grid>(); 
    public static ArrayList<Grid> mustmove=new ArrayList<Grid>();

    public static void clickedOnGrid(Grid g){
        
        Game.GameDB.getTurn();
        
        
        if(g.durum!=0)
        {            
            if(g.owner.ID==Game.GamePlayer.ID)
                if(Game.Room.PlayTurn==Game.GamePlayer.ID)
                {
                    if(g!=islenen)
                    {                        
                        islenen=g;            
                        checkMoveArea(g);
                    }
                    else
                    {
                        islenen=null;
                        moveable.clear();
                        mustmove.clear();
                    }
                    g.yenecek=null;
                }
        }
        else{
        
            if(moveable.contains(g))
            {
                moveable.clear();
                mustmove.clear();
                
                g.durum=islenen.durum;
                g.owner=islenen.owner;
                islenen.durum=0;
                islenen.owner=null;
                
                
                //Dama ol
                if(g.owner.host)
                {
                    if(g.posY==1)                    
                        if(g.durum==1)
                            g.durum=2;
                    
                }
                else
                {
                    if(g.posY==Controller_Ingame.GridSayisi)
                        if(g.durum==1)
                            g.durum=2;
                }
                
                Game.GameDB.UpdateGrid(islenen);
                Game.GameDB.UpdateGrid(g);                
                Game.GameDB.setTurn(Game.Room.Opponent.ID);
                islenen=null;
                
            }
            
            
            /////
            
            if(mustmove.contains(g))
            {
                moveable.clear();
                mustmove.clear();
                
                g.durum=islenen.durum;
                g.owner=islenen.owner;
                islenen.durum=0;
                
                
                ///Taş ye
                if(g.yenecek!=null && g.yenecek!=g)
                {                    
                    g.yenecek.durum=0;
                    g.yenecek.owner=null;  
                    Game.GameDB.UpdateGrid(g.yenecek);
                    Game.Room.Opponent.tasSayisi-=1;    
                    Game.GameDB.UpdateTasSayisi();
                } 
                
                //Dama ol
                if(g.owner.host)
                {
                    if(g.posY==1)                    
                        if(g.durum==1)
                            g.durum=2;                    
                }
                else
                {
                    if(g.posY==Controller_Ingame.GridSayisi)
                        if(g.durum==1)
                            g.durum=2;
                }
                                
                Game.GameDB.UpdateGrid(islenen);
                Game.GameDB.UpdateGrid(g);
                
                islenen=null;
            }
        
        }
        
        Game.GameDB.CheckWinner();        
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
        
        isMoveable(g,"left",0,distance,false,null);
        isMoveable(g,"right",0,distance,false,null);
        isMoveable(g,g.owner.host?"up":"down",0,distance,false,null);
        if(g.durum==2)
            isMoveable(g,!g.owner.host?"up":"down",0,distance,false,null);
        
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
    
    
    
    public static void isMoveable(Grid g, String yon, int mevcutSira, int maxSira, boolean mustEaten, Grid ynck)
    {
        if(g.durum==0)
        {
            if(!mustEaten)
                moveable.add(g);
            else           
            {
                if(ynck!=null)
                    g.yenecek=ynck;
                
                mustmove.add(g);                
            }
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

                            if(g.owner==Game.Room.Opponent)
                            {                        
                                mustEaten=true;
                                ynck=g;                                
                            }
                            else if(g.durum!=0 && g.owner==Game.GamePlayer)
                                return;
                            else if(islenen.durum!=2)
                                return;
                            else if(nextGrid.durum!=0 && mustEaten==true)
                                return;
                            
                        
                        
                    }

                    if(nextGrid.ID!=g.ID)
                        isMoveable(nextGrid,yon,mevcutSira+1,maxSira,mustEaten,ynck);                   
                }
            }
        }
    }
    


    
    
    
    
    
}
