package Elements;


public class Player {
    
    public String name;
    public boolean host;
    public boolean playTurn;
    public int ID;
    
    public Player(String n){
        // Eğer bağlı olduğu oyunda player1 yok ise host olacak
        // Eğer host ise playturn 1 olacak
        // Aşağıdaki satırlar silinecek.
        this.playTurn=true;
        this.host=true;
        this.ID=1;
        this.name=n;
    }
    
}
