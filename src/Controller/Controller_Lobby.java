package Controller;

import Drawing.Draw_Lobby;
import Elements.Button;
import Elements.LobbyGame;
import Elements.Password;
import Global.Controller;
import Global.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Controller_Lobby extends Controller{

    public static Password  pwSifre;
    public static Button    btnBaglan;
    public static Button    btnYarat;
    public static Button    btnYenile;
    
    public static LobbyGame selected=null;

    public static ArrayList<LobbyGame> GameList=new ArrayList<LobbyGame>();
    
    public Controller_Lobby(){
        
        super(new Draw_Lobby());
        Game.UpdateFrame();
        
        if(Game.GamePlayer!=null)
        {
            pwSifre=new Password(565,350,100,20);
            btnBaglan=new Button("Oyuna Bağlan",560,390,112,24);
            btnYarat=new Button("Oyun Oluştur",560,250,112,24);
            btnYenile=new Button("Yenile",560,280,112,24);
            
            btnYarat.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e) {
                   Game.GameController=new Controller_CreateRoom();
                   Game.UpdateFrame();
                }
            
            });
            
            
            btnBaglan.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e) {
                    if(selected!=null)
                    {
                        String sifre=pwSifre.getText();

                        if(sifre.length()!=0)
                        {
                            if(selected.sifre.equals(sifre))
                            {
                                Game.GameDB.RoomInit(selected.ID,selected.sifre);
                                
                            }
                            else
                            {
                               JOptionPane.showMessageDialog(null, "Şifre hatalı.");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Şifre girin.");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Oyun seçin.");
                    }
                    
                }
                
            });

            
            btnYenile.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e) {
                    Game.GameController=new Controller_Lobby();
                    Game.UpdateFrame();
                }
                
            });
            
            
            Game.GameWindow.add(pwSifre);
            Game.GameWindow.add(btnBaglan);
            Game.GameWindow.add(btnYarat);
            Game.GameWindow.add(btnYenile);
           
            if(Game.GameDB.IsConnected)
            {
                ResultSet rs;
                try {
                    
                    rs = Game.GameDB.Connection.createStatement().executeQuery("SELECT * FROM game_rooms");
                
                    int konum=250;
                    while(rs.next())
                    {
                        int OS=0;
                        if(rs.getInt("r_p1")!=-1)
                            OS++;
                        if(rs.getInt("r_p2")!=-1)
                            OS++;
                        
                        GameList.add(new LobbyGame(rs.getString("r_ad"),rs.getInt("r_id"),OS,konum,rs.getString("r_pass")));                        
                        konum+=30;        
                        
                    }
                    
                                    
                
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "Bağlantı sorunu oluştu.");
                   System.exit(0);
                }          
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Bağlantı bulunamadı.");
                System.exit(0);
            }
            
            
        }
        else
        {
            Game.GameController=new Controller_Login();
        }
        
    }
    
    
    public void onClick() {
       
    }
    
    
    
}
