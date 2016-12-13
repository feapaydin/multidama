package Controller;

import Drawing.Draw_Opening;
import Global.Controller;
import Global.Game;


public class Controller_Opening extends Controller{
    
    public Controller_Opening(){
        super(new Draw_Opening());
        
        
        System.out.println("========================");
        System.out.println("=== Multiplayer Dama ===");
        System.out.println("===       By.        ===");
        System.out.println("===   Furkan Enes    ===");
        System.out.println("===     APAYDIN      ===");
        System.out.println("========================");
        System.out.println("========= 2016 =========");
        System.out.println("= İstanbul Gelişim Ünv =");
        System.out.println("========================");
        
    }

    
    public void onClick() {
       Game.GameController=new Controller_Login();
       Game.UpdateFrame();
    }
    
}
