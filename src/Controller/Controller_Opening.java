package Controller;

import Drawing.Draw_Opening;
import Global.Controller;
import Global.Game;


public class Controller_Opening extends Controller{
    
    public Controller_Opening(){
        super(new Draw_Opening());
    }

    
    public void onClick() {
       Game.GameController=new Controller_Login();
       Game.UpdateFrame();
    }
    
}
