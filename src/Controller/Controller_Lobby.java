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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    
                    
                    
                    
                    
                    ////Boş odaları sil
                    ResultSet bosRs=Game.GameDB.Connection.createStatement().executeQuery("SELECT * FROM game_rooms INNER JOIN game_players ON game_players.p_id=game_rooms.r_p1 WHERE r_p1!=-1");
                    while(bosRs.next())
                    {
                        
                        String suan_str=Game.GameDB.getCurrentDate();
                        String lastOnline_str=bosRs.getString("p_lastOnline");
                        
                        try {
                            
                            Date suan=Game.GameDB.df.parse(suan_str);
                            Date lastOnline=Game.GameDB.df.parse(lastOnline_str);
                            
                            long diff = suan.getTime() - lastOnline.getTime();
                            
                            long dakikaFark=TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
                            if(dakikaFark>=15)
                            {
                                Game.GameDB.deleteRoom(bosRs.getInt("r_id"));
                                System.out.println(bosRs.getString("r_ad")+" Silindi.");
                            }
                            
                            
                        } catch (ParseException ex) {
                            System.out.println("Date error.");
                        }
                        
                        
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
