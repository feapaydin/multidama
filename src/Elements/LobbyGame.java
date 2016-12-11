package Elements;

import Controller.Controller_Lobby;
import static Controller.Controller_Lobby.GameList;
import Global.Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class LobbyGame extends JButton{
    
    public int ID;
    public String gameName;
    public int OS;
    public String sifre;
    
    public LobbyGame(String ad, int id, int oyuncuSayisi, int posY, String pass){
        this.ID=id;
        this.gameName=ad;
        this.OS=oyuncuSayisi;
        this.sifre=pass;
        
        
        setForeground(Color.RED);
        setText("  "+ad+" ["+oyuncuSayisi+"/2]");
        
        setLocation(130,posY);
        setVisible(true);
        
        setSize(400,25);
        setBorder(null);
        setFocusable(false);
        
        
        setHorizontalAlignment(SwingConstants.LEFT);
        
        setColor();
            
        setBackground(new Color(44,10,1));
        
        
        Game.GameWindow.add(this);
        
        LobbyGame rooom=this;
        this.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e) {
                Controller_Lobby.selected=rooom;
                
                for(int i=0; i<GameList.size(); i++)
                {
                    LobbyGame room=(LobbyGame)GameList.get(i);
                    room.setColor();
                }            
               
            }
        
        });
        
        
    }
    
    public void setColor(){
        
            
        if(Controller_Lobby.selected!=this)
        {
            setForeground(new Color(160,131,64));
        }
        else
        {
            setForeground(Color.GREEN);                    
        }
            
        
            
    }
    
    
}
