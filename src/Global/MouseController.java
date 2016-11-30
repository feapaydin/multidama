package Global;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseController implements MouseListener{
    
    public int LeftPadding=0;
    public int TopPadding=0;
    
    public int x;
    public int y;
    
    MouseController(){       
        
    }
    
    public void recalculatePadding(){
        this.LeftPadding=Game.GameWindow.getWidth()-Game.GameWindow.getContentPane().getWidth()-3;
        this.TopPadding=Game.GameWindow.getHeight()-Game.GameWindow.getContentPane().getHeight()-3;
    }
    
    //Frame boyutlarını mouse koordinatlarından çıkar
    public void recalculateMouse(MouseEvent e){
        recalculatePadding();
        this.x=e.getX()-LeftPadding;
        this.y=e.getY()-TopPadding;
    }
    
     
    

    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
        recalculateMouse(e);       
        Game.GameController.onClick();
        //System.out.println("clicked "+Game.Mouse.x+","+Game.Mouse.y+" : "+e.getX()+","+e.getY()+" : "+LeftPadding+","+TopPadding);
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
