package Global;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseController implements MouseListener{
    
    public int LeftPadding=0;
    public int TopPadding=0;
    
    public int x;
    public int y;
    
    MouseController(int leftPadding, int topPadding){       
        this.LeftPadding=leftPadding;
        this.TopPadding=topPadding;
    }
    
    //Frame boyutlarını mouse koordinatlarından çıkar
    public void recalculateMouse(MouseEvent e){
        x=e.getX()-LeftPadding;
        y=e.getY()-TopPadding;
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
       recalculateMouse(e);        
       
       Game.GameController.onClick();
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
