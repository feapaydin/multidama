package Elements;


public class Player {
    
    public String name;
    public boolean host;
    public int ID;
    public int tasSayisi=16;
    
    public Player(String n,int ids){
        // Eğer bağlı olduğu oyunda player1 yok ise host olacak
        // Eğer host ise playturn 1 olacak
        // Aşağıdaki satırlar silinecek.
        this.host=true;
        this.ID=ids;
        this.name=n;
    }
    
}
