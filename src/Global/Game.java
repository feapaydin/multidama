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
    
    ///Server-side
    public static String                Database_Host="127.0.0.1";
    public static String                Database_User="root";
    public static String                Database_User_Password="";
    public static String                Database_Name="multidama";
    
    
    public static String                GameName;
    public static String                GamePassword;
    public static int                   PlayTurn=1;
    public static Player                GamePlayer;
    public static Player                Opponent;
    
    
    
    ///Client-side
    public static Controller            GameController;
    public static Display               GameWindow;
    public static MouseController       Mouse;
    public static DatabaseController    GameDB;
    

       
    public static void main(String[] args) {
        
        ///Görüntüyü Oluştur    
        GameWindow  =   new Display();        
        
        new Thread(new Runnable(){
            public void run(){
                GameDB      =   new DatabaseController(Database_Host,Database_Name,Database_User,Database_User_Password); 
            }
        }).start();
        
        new Thread(new Runnable(){
            public void run(){
                GameController= new Controller_Ingame();
            }
        }).start();
        
        
         
        GamePlayer  =   new Player("FEApaydin",1);  
        Opponent  =   new Player("caghan",2);  
                
        
    } 
    
    
    //Mevcut DrawController için repaint çağırımı
    public static void UpdateFrame(){
        GameController.DrawController.repaint();
    }    
    
    
}
