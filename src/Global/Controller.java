package Global;

public abstract class Controller {

    public static Draw DrawController;
    
    public Controller(Draw DC){

        DrawController=DC;
        Game.GameWindow.add(DC);
        
    }
    
    
    
    ///Abstract
    public abstract void onClick();

    
}
