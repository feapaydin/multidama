package Global;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;



public class Display extends JFrame{
    
    
    
    Display(){
        
        ///Pencere Ayarları
        this.setVisible(true);
        this.setSize(800,600);
        this.setTitle("Multiplayer Dama by. Furkan Enes Apaydın");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);        
        
        
        ///Mouse Listener
        MouseController MC=new MouseController();
                
        this.addMouseListener(Game.Mouse=MC);
        
       
        
        
    }   
   
    
    
}
