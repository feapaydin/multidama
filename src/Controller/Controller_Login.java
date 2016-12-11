package Controller;

import Global.Game;
import Global.Controller;
import Drawing.Draw_Login;
import Elements.Button;
import Elements.Password;
import Elements.Player;
import Elements.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Controller_Login extends Controller{
    
    public static TextField tfAd;
    public static Password  tfSifre;
    public static Button    btnGiris;
    
    public static String    islemDurum="";
    
    public Controller_Login(){
        
        super(new Draw_Login());

        tfAd=new TextField((Game.GameWindow.window_width/2)-82,262,165,25);
        tfSifre=new Password((Game.GameWindow.window_width/2)-82,323,165,25);
        btnGiris=new Button("Giriş",(Game.GameWindow.window_width/2)-60,380,111,22);
        
                
        Game.GameWindow.add(tfAd); 
        Game.GameWindow.add(tfSifre); 
        Game.GameWindow.add(btnGiris); 
        
        tfAd.repaint();
        tfSifre.repaint();
        btnGiris.repaint();
        
        btnGiris.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String kAd=tfAd.getText();
                String kSifre=tfSifre.getText();
                
                                
                if(Game.GamePlayer==null)
                {
                    if(Game.GameDB.IsConnected)
                    {
                        try {

                            PreparedStatement ps=Game.GameDB.Connection.prepareStatement("SELECT * FROM game_players WHERE p_ad=? AND p_sifre=?");
                            ps.setString(1, kAd);
                            ps.setString(2, kSifre);
                            ResultSet rs=ps.executeQuery();

                            if(rs.next())
                            {
                                ///Giriş başarılı
                                Game.GamePlayer=new Player(rs.getString("p_ad"),rs.getInt("p_id"));
                                
                                System.out.println("Giriş yapıldı: "+Game.GamePlayer.name);
                                islemDurum="Hoş geldiniz, "+Game.GamePlayer.name+".";
                                
                                Game.GameDB.UpdateLastOnline();
                                
                                btnGiris.setVisible(false);
                                
                                Game.GameController=new Controller_Lobby();
                                
                            }
                            else
                            {
                                islemDurum="Kullanıcı bulunamadı.";
                            }

                        } catch (SQLException ex) {
                            islemDurum="Teknik bir hata oluştu.";
                        }


                    }
                    else
                    {
                        islemDurum="Bağlantı hatası.";
                    }
                }
                else
                {
                    islemDurum="Zaten giriş yaptınız, "+Game.GamePlayer.name+".";
                }
                
                tfAd.setText("");
                tfSifre.setText("");
                Game.UpdateFrame();
                
            }
            
        });
        
        
       
    }
    
    
    public void onClick() {
        
    }
    
}
