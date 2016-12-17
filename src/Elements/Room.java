package Elements;

import Global.Game;


public class Room {
    
    public int                   ID;
    public String                GameName;
    public String                GamePassword;
    public int                   PlayTurn;
    public Player                Opponent=null;
    public int                   Winner=-1;
    public int                   LastMoved=-1;
    public int                   LastMovedFrom=-1;
    
    public boolean               Start=false;
    
    
    public void startGame(){        
        
        this.Start=true;
        
    }
    
}
