package Global;

import Controller.Controller_Ingame;
import Elements.*;

public class InGameLogic {

    public static Grid islenen;
    
    public static void clickedOnGrid(Grid g){
        
        if(g.durum!=0)
        {
            if(g.owner.ID==Game.GamePlayer.ID)
                if(Game.PlayTurn==Game.GamePlayer.ID)
                    islenen=g;
        }
        
        Game.UpdateFrame();
    
    }

    
}
