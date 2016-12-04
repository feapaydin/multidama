
package Elements;

import java.awt.Color;
import java.util.ArrayList;

public class Grid {
    
    public Player owner=null;
    public int ID;
    public int posX;
    public int posY;
    public short durum=0;    
    public Grid yenecek=null;
    /*
    *   DURUM:
    *   0 - Boş
    *   1 - Dolu
    *   2 - Dama
    *
    */
    
    public int drawCoordX;
    public int drawCoordY;
    public Color bgColor;    
  
    
        
    public Grid(int id, int xx, int yy, int dcX, int dcY, Color bgc){
        this.ID=id;
        this.posX=xx;
        this.posY=yy;
        this.setDrawPos(dcX,dcY);
        this.bgColor=bgc;        
    }
    
    public void setDrawPos(int xx, int yy){
        this.drawCoordX=xx;
        this.drawCoordY=yy;
    }
    
}
