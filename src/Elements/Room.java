package Elements;

import Global.Game;


public class Room {
    
    public int                   ID;
    public String                GameName;
    public String                GamePassword;
    public int                   PlayTurn;
    public Player                Opponent;
    public int                   Winner=-1;
    
    public boolean               Start=false;
    
    
    public void startGame(){        
        
        this.Start=true;
        
    }
    
}
