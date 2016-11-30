package Global;

import static Controller.Controller_Ingame.GameLogic;
import static Controller.Controller_Ingame.GridList;
import static Controller.Controller_Ingame.GridSize;
import Elements.Grid;

public class GridCheck implements Runnable{
    
    private Grid g;
    
    public GridCheck(Grid gg)
    {
        this.g=gg;
    }
    
    public void run(){
        if(checkClick(g))
        {               
            GameLogic.clickedOnGrid(g);           
        }    
    }
    
    private boolean checkClick(Grid g){        
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
