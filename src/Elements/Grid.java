
package Elements;

import java.awt.Color;

public class Grid {
    
    public int ID;
    public int posX;
    public int posY;
    public short durum=0;  
    public Color bgColor;
    /*
    *   DURUM:
    *   0 - Boş
    *   1 - Dolu
    *   111 - Birinci oyuncunun taşı var
    *   121 - İkinci oyuncunun taşı var
    *   112 - Birinci oyuncunun dama taşı var
    *   122 - İkinci oyuncunun dama taşı var
    *
    */
    
    public int drawCoordX;
    public int drawCoordY;
    
    
    
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
