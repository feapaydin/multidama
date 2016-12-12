package Controller;

import Drawing.Draw_Ingame;
import Elements.Button;
import Elements.Grid;
import Global.Controller;
import Global.Game;
import Global.GridCheck;
import Global.InGameLogic;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


public class Controller_Ingame extends Controller{
    
    public static InGameLogic   GameLogic;
           
    public static final int     GridSize=50;
    public static final int     GridSayisi=8;
    
    public static final int     GridDrawStartPointX=((Game.GameWindow.getWidth()-(GridSize*GridSayisi))/2);
    public static final int     GridDrawStartPointY=146;
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
    
   
    
    public TimerTask ttask=new TimerTask(){public void run(){if(Game.GamePlayer.spectator==false && Game.Room!=null) Game.GameDB.GetGameData();}};
    public static Timer tmr;
    
    public static Button btnPeset;
    public static Button btnLobiyedon;
    
    public Controller_Ingame(){        

        super(new Draw_Ingame());

        tmr=new Timer();
        tmr.scheduleAtFixedRate(ttask, 1000, 5000);
        
        btnPeset=new Button("Pes Et",Game.GameWindow.window_width-150-30,450,150,25);
        btnLobiyedon=new Button("Lobiye Dön",Game.GameWindow.window_width-150-30,485,150,25);
        Game.GameWindow.add(btnPeset);
        Game.GameWindow.add(btnLobiyedon);
        
        btnPeset.repaint();
        btnLobiyedon.repaint();
        
        btnPeset.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e) {
                if(Game.Room.Opponent!=null)
                    Game.GameDB.pesEt();                
                else
                    JOptionPane.showMessageDialog(null,"Rakip bağlanmadan pes edemezsiniz.");
            }
            
        });
        
        btnLobiyedon.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e) {
                Game.ResetToMenu();
            }
        
        });
             

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
