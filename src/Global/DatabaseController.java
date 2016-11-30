package Global;


import java.sql.*;
import javax.swing.JOptionPane;


public class DatabaseController {
    
    public String host, dbname, username, pass;
    public static int GameID;
    public static boolean IsConnected=false;      
    
    public Connection Connection;
    public Statement Statement;
    
    
    
    DatabaseController(String h, String d, String u, String p){
        
        this.host=h;
        this.dbname=d;
        this.username=u;
        this.pass=p;
        
        try{
            
            Connection=DriverManager.getConnection("jdbc:mysql://"+host+"/"+dbname,username,pass);            
            Statement=Connection.createStatement();   
            this.IsConnected=true;
            System.out.println("Veritabanı bağlantısı sağlandı.");
            
        }catch(Exception e)
        {            
            Game.GameWindow.setVisible(false);
            JOptionPane.showMessageDialog(null,"Veri tabanı bağlantı hatası.");
            System.exit(0);
        }
        
    }
        
    
}
