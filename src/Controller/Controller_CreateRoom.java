package Controller;

import Drawing.Draw_CreateRoom;
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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controller_CreateRoom extends Controller{
    
    public static TextField tfAd;
    public static Password  tfSifre;
    public static Button    btnOlustur;
    public static Button    btnGeridon;
    
    public static String    islemDurum="";
    
    public Controller_CreateRoom(){
        
        super(new Draw_CreateRoom());

        islemDurum="";
        
        tfAd=new TextField((Game.GameWindow.window_width/2)-82,262,165,25);
        tfSifre=new Password((Game.GameWindow.window_width/2)-82,323,165,25);
        btnOlustur=new Button("Oluştur",(Game.GameWindow.window_width/2)-60,380,111,22);
        btnGeridon=new Button("Lobiye Dön",(Game.GameWindow.window_width/2)-60,470,111,22);
        
                
        Game.GameWindow.add(tfAd); 
        Game.GameWindow.add(tfSifre); 
        Game.GameWindow.add(btnOlustur); 
        Game.GameWindow.add(btnGeridon); 
        
        tfAd.repaint();
        tfSifre.repaint();
        btnOlustur.repaint();
        btnGeridon.repaint();
        
        btnGeridon.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e) {
                Game.GameController=new Controller_Lobby();
                Game.UpdateFrame();
            }        
        
        });
        
        
        
        btnOlustur.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String oAd=tfAd.getText();
                String oSifre=tfSifre.getText();
                
                                
                
                if(Game.GameDB.IsConnected)
                {

                    if(oAd.length()>=5 && oSifre.length()>=4 && oAd.length()<=25 && oSifre.length()<=12)
                    {
                    
                        try {
                            
                            ResultSet gls=Game.GameDB.Connection.createStatement().executeQuery("SELECT COUNT(*) FROM game_rooms");
                                if(gls.next())
                                {
                                    int odaSayisi=gls.getInt("COUNT(*)");
                                    if(odaSayisi<5)
                                    {

                                        PreparedStatement ps=Game.GameDB.Connection.prepareStatement("INSERT INTO game_rooms(r_ad,r_pass,r_p1,r_turn) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                                        ps.setString(1,oAd);
                                        ps.setString(2,oSifre);
                                        ps.setInt(3,Game.GamePlayer.ID);
                                        ps.setInt(4,Game.GamePlayer.ID);

                                        int ekleme=ps.executeUpdate();
                                        if(ekleme>0)
                                        {
                                            ResultSet gk=ps.getGeneratedKeys();
                                            if(gk.next())
                                            {
                                                int odaId=gk.getInt(1);

                                                ///GRIDS

                                                int gridId=0;
                                                for(int posY=1; posY<=Controller_Ingame.GridSayisi; posY++)
                                                {
                                                    for(int posX=1; posX<=Controller_Ingame.GridSayisi; posX++)
                                                    {

                                                        int owner=0;
                                                        int durum=0;

                                                        if(posY==6 || posY==7)
                                                        {
                                                            owner=Game.GamePlayer.ID;
                                                            durum=1;
                                                        }

                                                        Game.GameDB.addGrid(odaId, owner, durum, posX, posY, gridId);

                                                        gridId++;
                                                    }                                        
                                                }


                                                ///END GRIDS


                                                Game.GameDB.RoomInit(odaId, oSifre);

                                            }
                                            else
                                            {
                                                islemDurum="Oda oluşturulamadı. [2]";
                                            }

                                        }
                                        else
                                        {
                                            islemDurum="Oda oluşturulamadı. [1]";
                                        }
                                
                                
                                
                                    }
                                    else
                                    {
                                        islemDurum="Server Dolu. Mevcut oyunların bitmesini bekleyin.";
                                    }
                                
                            } //gls boş
                        
                        } catch (SQLException ex) {
                            System.out.println("Oda oluşturma sql error");
                        }
                    
                    }
                    else
                    {
                        islemDurum="Veriler Geçersiz. Oda adı en az 5 en fazla 25 karakter olabilir. Şifre en az 5 en fazla 12 karakter olabilir.";
                    }


                }
                else
                {
                    islemDurum="Bağlantı hatası.";
                }
                
                
                
                tfAd.setText("");
                tfSifre.setText("");
                Game.UpdateFrame();
                
            }
            
        });
        
        
        Game.UpdateFrame();
    }
    
    
    public void onClick() {
        
    }
    
}
