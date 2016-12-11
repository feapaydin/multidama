package Global;

public abstract class Controller {

    public static Draw DrawController=null;
    
    public Controller(Draw DC){
      
        ChangeDraw(DC);
        
    }
    
    public static void ChangeDraw(Draw DC)
    {
        Game.GameWindow.getContentPane().removeAll();
        
        DrawController=DC;
        Game.GameWindow.add(DC);
    }
    
    
    
    ///Abstract
    public abstract void onClick();

    
}
