/*
 * 
 * Multiplayer Dama Oyunu
 * @author: Furkan Enes Apaydın
 * @date: 11.2016
 * İstanbul Gelişim Üniversitesi
 *
 */

package Global;

import Controller.*;
import Drawing.*;
import Elements.Player;



public class Game {
    

    public static Controller            GameController;
    public static Display               GameWindow;
    public static MouseController       Mouse;
    
    public static DatabaseController    GameDB;
    public static Player                GamePlayer;
    public static String                Opponent;
    public static int                   PlayTurn=1;
       
    public static void main(String[] args) {
        
        ///Görüntüyü Oluştur    
        GameWindow  =   new Display();        
        
        new Thread(new Runnable(){
            public void run(){
                GameDB      =   new DatabaseController("127.0.0.1","multidama","root",""); 
            }
        }).start();
        
        new Thread(new Runnable(){
            public void run(){
                GameController= new Controller_Ingame();
            }
        }).start();
        
        
         
        GamePlayer  =   new Player("FEApaydin");  
                
        
    } 
    
    
    //Mevcut DrawController için repaint çağırımı
    public static void UpdateFrame(){
        GameController.DrawController.repaint();
    }    
    
    
}
