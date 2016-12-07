package Elements;


public class Player {
    
    public String name;
    public boolean host;
    public boolean spectator=false;
    public int ID;
    public int tasSayisi=16;
    
    public Player(String n,int ids){
        // Eğer bağlı olduğu oyunda player1 yok ise host olacak
        // Eğer host ise playturn 1 olacak
        this.ID=ids;
        this.name=n;
    }
    
}
