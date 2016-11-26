package Global;

import Controller.Controller_Ingame;
import Elements.*;

public class InGameLogic {

    public static Grid islenen;
    
    public static void clickedOnGrid(Grid g){
        
        if(g.durum!=0)
        {
            if(g.owner.ID==Game.GamePlayer.ID)
                if(Game.GamePlayer.playTurn)
                    islenen=g;
        }
        
        Game.UpdateFrame();
    
    }

    
}
