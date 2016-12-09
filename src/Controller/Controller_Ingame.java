package Controller;

import Drawing.Draw_Ingame;
import Elements.Grid;
import Global.Controller;
import Global.Game;
import Global.GridCheck;
import Global.InGameLogic;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;


public class Controller_Ingame extends Controller{
    
    public static InGameLogic   GameLogic;
           
    public static final int     GridSize=50;
    public static final int     GridSayisi=8;
    
    public static final int     GridDrawStartPointX=((Game.GameWindow.getWidth()-(GridSize*GridSayisi))/2)-10;
    public static final int     GridDrawStartPointY=40;
    public static int           GridDrawPointX=GridDrawStartPointX;
    public static int           GridDrawPointY=GridDrawStartPointY;
    
    
    public static final int     TableSize=GridSize*GridSayisi;
    public static Grid[]        GridList=new Grid[GridSayisi*GridSayisi];
    
    public static final String  Grid_Bos_Img=""; 
    public static final String  Grid_Tas_Img="/arc/img/tas.png";
    public static final String  Grid_Dama_Img="/arc/img/tas_dama.png";
    public static final String  Grid_Click_Img="/arc/img/tas_selected.png";
    public static final String  Grid_Click_Dama_Img="/arc/img/tas_selected_dama.png";
    public static final String  Grid_Moveable_Img="/arc/img/tas_moveable.png";
    public static final String  Grid_Mustmove_Img="/arc/img/tas_mustmove.png";
    public static final String  Grid_Tas_Dusman_Img="/arc/img/tas_opponent.png";
    public static final String  Grid_Dama_Dusman_Img="/arc/img/tas_opponent_dama.png";
    
   
    
    private TimerTask ttask=new TimerTask(){public void run(){Game.GameDB.GetGameData();}};
    private Timer tmr=new Timer();
    
    public Controller_Ingame(){        

        super(new Draw_Ingame());
        tmr.scheduleAtFixedRate(ttask, 1000, 5000);
     
        ///Grid Düzeni Oluşturma
        /*
        int gridId=0;
        for(int posY=1; posY<=GridSayisi; posY++)
        {
            for(int posX=1; posX<=GridSayisi; posX++)
            {
       
                
                gridId++;
                GridDrawPointX+=GridSize;
                
            }
            
            GridDrawPointY+=GridSize;
            GridDrawPointX=GridDrawStartPointX;
        }
        */
        
        
        
        
        
    }
      
    
    
    
    //// ONCLICK
    public void onClick(){
       
        if(Game.Room.Start)
            for(int i=0; i<GridList.length; i++)
            {           
               new Thread(new GridCheck(GridList[i])).start();      
            }  
           
    }
    
    
    
    
}
