package Controller;

import Drawing.Draw_Ingame;
import Elements.Grid;
import Global.Controller;
import Global.Game;
import Global.GridCheck;
import Global.InGameLogic;
import java.awt.Color;


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
    
    private short[][] tasCoords=new short[GridSayisi][GridSayisi];
    
    
    public Controller_Ingame(){        
        
        super(new Draw_Ingame());
        
        ///Taşların bulunacağı koordinatlar
        //aşağısı mysql e bağlanacak
        tasCoords[1][0]=1;
        tasCoords[1][1]=1;
        tasCoords[1][2]=1;
        tasCoords[1][3]=1;

        tasCoords[1][5]=1;
        tasCoords[1][6]=1;
        tasCoords[1][7]=1;
        
        tasCoords[2][0]=1;
        tasCoords[2][1]=1;
        tasCoords[2][2]=1;
        tasCoords[2][3]=1;
        tasCoords[2][4]=1;
        tasCoords[2][5]=1;
        tasCoords[2][6]=1;
        tasCoords[2][7]=1;
        
        tasCoords[5][0]=1;
        tasCoords[5][1]=1;
        tasCoords[5][2]=1;
        //tasCoords[5][3]=1;
        tasCoords[5][4]=2;
        //tasCoords[5][5]=1;
        tasCoords[5][6]=1;
        tasCoords[5][7]=1;
        
        tasCoords[6][0]=1;
        tasCoords[6][1]=1;
        tasCoords[6][2]=1;
        tasCoords[6][3]=1;
        tasCoords[6][4]=1;
        tasCoords[6][5]=1;
        tasCoords[6][6]=1;
        tasCoords[6][7]=1;
        
        
        
        
        ///Grid Düzeni Oluşturma
        int gridId=0;
        for(int posY=1; posY<=GridSayisi; posY++)
        {
            for(int posX=1; posX<=GridSayisi; posX++)
            {
                
                Color gridColor=gridId%2==(posY%2)?Color.DARK_GRAY:Color.WHITE;
                
                GridList[gridId]=new Grid(gridId,posX,posY,GridDrawPointX,GridDrawPointY,gridColor);
                
                
                if(tasCoords[posY-1][posX-1]!=0)
                    GridList[gridId].durum=tasCoords[posY-1][posX-1];   
                
                if((posY-1==2 && posX-1==4))                
                    GridList[gridId].owner=Game.Opponent;
                else
                    GridList[gridId].owner=Game.GamePlayer;
                
                
                
                gridId++;
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
           new Thread(new GridCheck(GridList[i])).start();      
        }
        
        
         
    }    
    
    
    
    
}
