package Global;


import Controller.Controller_Ingame;
import Controller.Controller_Lobby;
import Elements.*;
import java.awt.Color;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseController {
    
    public String host, dbname, username, pass;
    public static int GameID;
    public static boolean IsConnected=false;      
    
    public Connection Connection;    
    
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    
    
    DatabaseController(String h, String d, String u, String p){
        
        this.host=h;
        this.dbname=d;
        this.username=u;
        this.pass=p;
        
        try{
            
            Connection=DriverManager.getConnection("jdbc:mysql://"+host+"/"+dbname,username,pass);             
            IsConnected=true;
            System.out.println("Veritabanı bağlantısı sağlandı.");
            
            
        }catch(Exception e)
        {            
            Game.GameWindow.setVisible(false);
            JOptionPane.showMessageDialog(null,"Veri tabanı bağlantı hatası.");
            System.exit(0);
        }
        
    }
    
    
    ////Data İşlemleri
    
    
    //Odaya Bağlanma
    public void RoomInit(int GameId, String Password){
        if(IsConnected)
        {
            try{
                PreparedStatement s;
                s=Connection.prepareStatement("SELECT * FROM game_rooms WHERE r_id=? AND r_pass=? AND r_p1!=-1");
                s.setInt(1, GameId);
                s.setString(2, Password);                
                ResultSet rs=s.executeQuery();
                if(rs.next())
                {
                    System.out.println("Oda bulundu.");
                    
                    ResultSet p1=Connection.createStatement().executeQuery("SELECT * FROM game_players WHERE p_id="+rs.getInt("r_p1"));
                    
                    if(p1.next())
                    {           
                        System.out.println("P1 bulundu.");
                        
                        Game.Room=new Room();
                        Game.Room.ID=rs.getInt("r_id");
                        Game.Room.GameName=rs.getString("r_ad");
                        Game.Room.GamePassword=Password;
                        Game.Room.PlayTurn=rs.getInt("r_turn");
                        
                        System.out.println("Oda yüklendi: "+Game.Room.GameName);
                        
                        if(p1.getInt("p_id")==Game.GamePlayer.ID)
                        {
                            System.out.println("Oyuncu P1 (Host).");
                            Game.GamePlayer.host=true;
                            
                            if(rs.getInt("r_p2")!=-1)
                            {
                                System.out.println("Oyunun P2 si var.");
                                ResultSet opponent=Connection.createStatement().executeQuery("SELECT * FROM game_players WHERE p_id="+rs.getInt("r_p2"));

                                if(opponent.next())
                                {
                                    System.out.println("Opponent (P2) yüklendi: "+opponent.getString("p_ad"));
                                    Game.Room.Opponent=new Player(opponent.getString("p_ad"),opponent.getInt("p_id"));
                                    Game.Room.startGame();
                                    GetGameData();
                                    GetGridList();
                                }
                                else //Player2 Database'de Yok
                                {
                                   JOptionPane.showMessageDialog(null, "Atanmış ikinci oyuncu bulunamadı.");
                                   Game.ResetToMenu(); 
                                }


                            } 
                            else //Player2 yok
                            {
                                System.out.println("İkinci oyuncu bekleniyor.");
                                //bekle
                            }
                            
                        }
                        else //Oyuncu host değil
                        {
                            
                         
                            if(rs.getInt("r_p2")==-1)
                            {
                                System.out.println("Oyunun P2 si "+Game.GamePlayer.name+" olarak atandı.");
                                Connection.createStatement().executeUpdate("UPDATE game_rooms SET r_p2="+Game.GamePlayer.ID+" WHERE r_id="+rs.getInt("r_id"));                            
                            
                                System.out.println("Opponent (P1) yüklendi: "+p1.getString("p_ad"));
                                Game.Room.Opponent=new Player(p1.getString("p_ad"),p1.getInt("p_id"));
                                Game.Room.startGame();
                                GetGameData();
                                GetGridList();
                            }
                            else if(rs.getInt("r_p2")==Game.GamePlayer.ID)           
                            {
                                
                                System.out.println("Opponent (P1) yüklendi: "+p1.getString("p_ad"));
                                Game.Room.Opponent=new Player(p1.getString("p_ad"),p1.getInt("p_id"));
                                Game.Room.startGame();
                                GetGameData();
                                GetGridList();
                            }
                            else //Spectator
                            {
                                Game.GamePlayer.spectator=true;
                                JOptionPane.showMessageDialog(null, "Oda dolu.");                               
                                Game.ResetToMenu();
                            }
                            
                            
                            
                        }
                        
                        
                    }
                    else //Player1 yok
                    {
                        JOptionPane.showMessageDialog(null, "Oda yüklenirken teknik bir hata oluştu.");
                        Game.ResetToMenu();
                    }
                }
                else // Oda Yok
                {
                    JOptionPane.showMessageDialog(null, "Oyuna bağlanılamadı.\nOda dolu ya da parola hatalı.");
                    Game.ResetToMenu();
                }
                
                
                
                
                
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Oyun yüklenirken bir sorun oluştu.");
                Game.ResetToMenu();
            }
        }
      
    } //End RoomInit()
    
    
    
    ///////////// IN-GAME FUNCS //////////////////////
    
    
    //Oda Gridleri Çekme
    public void GetGridList(){
        if(IsConnected)
        {        
            int GameId=Game.Room.ID;
            try {
                
                ResultSet count=Connection.createStatement().executeQuery("SELECT COUNT(*) FROM game_grids WHERE g_roomId="+GameId);
                if(count.next())
                {
                    if(count.getInt("COUNT(*)")==64)
                    {
                
                        PreparedStatement s = Connection.prepareStatement("SELECT * FROM game_grids WHERE g_roomId=?");
                        s.setInt(1, GameId);

                        ResultSet rsg=s.executeQuery();

                        Controller_Ingame.GridDrawPointX=Controller_Ingame.GridDrawStartPointX;
                        Controller_Ingame.GridDrawPointY=Controller_Ingame.GridDrawStartPointY;                
                        Arrays.fill(Controller_Ingame.GridList,null);
                        
                        int gridId=0;
                        while(rsg.next())
                        {                    
                            gridId=rsg.getInt("g_inGameId");
                            
                            Controller_Ingame.GridDrawPointX=Controller_Ingame.GridDrawStartPointX+(Controller_Ingame.GridSize*(rsg.getInt("g_posX")-1));
                            Controller_Ingame.GridDrawPointY=Controller_Ingame.GridDrawStartPointY+(Controller_Ingame.GridSize*(rsg.getInt("g_posY")-1));

                            Color gridColor=gridId%2==(rsg.getInt("g_posY")%2)?Color.DARK_GRAY:Color.WHITE;
                            Controller_Ingame.GridList[gridId]=new Grid(gridId,rsg.getInt("g_posX"),rsg.getInt("g_posY"),Controller_Ingame.GridDrawPointX,Controller_Ingame.GridDrawPointY,gridColor);

                            if(rsg.getInt("g_owner")!=0 && Game.Room.Opponent!=null)
                            {
                                if(rsg.getInt("g_owner")==Game.GamePlayer.ID)
                                    Controller_Ingame.GridList[gridId].owner=Game.GamePlayer;
                                else if(rsg.getInt("g_owner")==Game.Room.Opponent.ID)
                                    Controller_Ingame.GridList[gridId].owner=Game.Room.Opponent;
                            }

                            Controller_Ingame.GridList[gridId].durum=(short)rsg.getInt("g_durum");


                        }
                        
                                                
                        rsg.close();
                        
                    

                    }
                    else //64 grid yok
                    {
                         JOptionPane.showMessageDialog(null, "Grid listesi eksik.");
                         Game.ResetToMenu();
                    }
                }
                
                count.close();
                
                
            } catch (SQLException ex) {               
                JOptionPane.showMessageDialog(null, "Grid listesi alınırken bir sorun oluştu.");
                Game.ResetToMenu();
            }      
            
            Game.UpdateFrame();            

        }
        
    } //End GetGridList()
    
    
    //Sıra çek
    public int getTurn(){
        if(IsConnected)
        {
            try{        

                ResultSet rt=Connection.createStatement().executeQuery("SELECT r_turn FROM game_rooms WHERE r_id="+Game.Room.ID);
                if(rt.next())          
                {
                    Game.Room.PlayTurn=rt.getInt("r_turn");
                    return rt.getInt("r_turn");  
                }
                else
                    return -1;

            }catch(SQLException e)
            {
                return -1;
            }
        }
        else
            return -1;
    } //end getTurn()
    
    
    //Sıra değiştir
    public boolean setTurn(int playerId){
        if(IsConnected)
        {
            if(Game.Room.PlayTurn==Game.GamePlayer.ID)
            {
                try{
                    String query="UPDATE game_rooms SET r_turn="+playerId+" WHERE r_id="+Game.Room.ID+" AND ";
                    if(Game.GamePlayer.host)
                        query+="r_p1="+Game.GamePlayer.ID+" AND r_p2="+Game.Room.Opponent.ID;
                    else
                        query+="r_p1="+Game.Room.Opponent.ID+" AND r_p2="+Game.GamePlayer.ID;
                    
                    Game.Room.PlayTurn=playerId;
                    
                    if(Connection.createStatement().executeUpdate(query)>0)
                        return true;
                    else
                        return false;
                    
                
                }catch(SQLException e)
                {
                    return false;
                }
            
            } //Sıra oyuncuda değil
            else{
                return false;
            }
            
        }
        else
            return false;
    }//end setTurn()
    
    
    
    //Grid güncelle
    public void UpdateGrid(Grid g)
    {
        if(IsConnected)
        {
            String query="UPDATE game_grids SET ";
            query+="g_durum="+g.durum;
            query+=",g_posX="+g.posX;
            query+=",g_posY="+g.posY;
            if(g.owner!=null)
                query+=",g_owner="+g.owner.ID;
            else
                query+=",g_owner=0";
            
            query+=" WHERE g_roomId="+Game.Room.ID+" AND g_inGameId="+g.ID;
        
            try {
                Connection.createStatement().executeUpdate(query);
            } catch (SQLException ex) {
                System.err.println("Update Hatası [x01]");
            }
        
        }
    }//end UpdateGrid()
    
    
    //Son online update
    public void UpdateLastOnline(){
        if(IsConnected)
        {            
            String query="UPDATE game_players SET p_lastOnline='"+getCurrentDate()+"'";
            query+=" WHERE p_id="+Game.GamePlayer.ID;
            try {
                Connection.createStatement().executeUpdate(query);
            } catch (SQLException ex) {}
            
        }
    }//end UpdateLastOnline()
    
    
    //Taş sayısını servera yükle
    public void UpdateTasSayisi(){
        if(IsConnected)
        {            
            String query="UPDATE game_rooms SET ";
            if(Game.GamePlayer.host)
                query+="r_p2_tas="+Game.Room.Opponent.tasSayisi;
            else
                query+="r_p1_tas="+Game.Room.Opponent.tasSayisi;            
            
            
            query+=" WHERE r_id="+Game.Room.ID;
            
            try {
                Connection.createStatement().executeUpdate(query);
            } catch (SQLException ex) {}
            
        }
    }//end UpdateTasSayisi()
    
    
    //Taş sayılarını servera göre güncelle
    public void getTasSayisi(){
        if(IsConnected)
        {
            String query="SELECT r_p1_tas, r_p2_tas FROM game_rooms WHERE r_id="+Game.Room.ID;
            
            try {
                ResultSet rts=Connection.createStatement().executeQuery(query);
                
                if(rts.next())
                {
                    if(Game.GamePlayer.host)
                    {
                        Game.GamePlayer.tasSayisi=rts.getInt("r_p1_tas");
                        Game.Room.Opponent.tasSayisi=rts.getInt("r_p2_tas");
                    }
                    else
                    {
                        Game.GamePlayer.tasSayisi=rts.getInt("r_p2_tas");
                        Game.Room.Opponent.tasSayisi=rts.getInt("r_p1_tas");
                    }
                }
                
            } catch (SQLException ex) {}
            
            
        }
    }//end getTasSayisi()
    
    
    
    //Data Çek
    public void GetGameData(){
        if(IsConnected)
        {           
            UpdateLastOnline();
            getTurn();
            getTasSayisi();
            
            if(Game.Room.PlayTurn!=Game.GamePlayer.ID)
                GetGridList();
            
            CheckWinner();
            
            
            System.out.println("Game data fetched.");
        }
    }//end GetGameData()
    
    
    //Tarihi string olarak al
    public String getCurrentDate(){
        Date d=new Date();
        return df.format(d);        
    }//end getCurrentDate();
    
    
    
    //Kazanan kontrol
    public void CheckWinner(){
        if(IsConnected)
        {
            if(Game.GamePlayer.tasSayisi==1)
            {
                Game.Room.Winner=Game.GamePlayer.ID;
            }
            else if(Game.Room.Opponent.tasSayisi==1)
            {
                Game.Room.Winner=Game.Room.Opponent.ID;
            }
            
            System.out.println("Winner: "+Game.Room.Winner);
            
            try {
                
                Connection.createStatement().executeUpdate("UPDATE game_rooms SET r_winner="+Game.Room.Winner+" WHERE r_id="+Game.Room.ID);
                //deleteRoom(Game.Room.ID);
                
            } catch (SQLException ex) {}
            
            
        }
    }
    
    
    
        
    
}
