package Global;

import java.awt.GridLayout;
import javax.swing.JFrame;


public class Display extends JFrame{
    
    
    Display(){
        
        ///Pencere Ayarları
        this.setVisible(true);
        this.setSize(640,480);
        this.setTitle("Multiplayer Dama by. Furkan Enes Apaydın");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,1,0,0));      

        
        ///Mouse Listener
        MouseController MC=new MouseController(
                this.getWidth()-this.getContentPane().getWidth()-3,
                this.getHeight()-this.getContentPane().getHeight()-3
        );
                
        this.addMouseListener(Game.Mouse=MC);
        
        
    }   
   
    
    
}
