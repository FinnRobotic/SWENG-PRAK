package mm.MVC.start;


import mm.MVC.util.Observable;
import mm.utilities.GameDef;

// To be implemented
public class StartModel extends Observable {

    private GameDef gameDef;

    public StartModel() {
        this.gameDef = new GameDef();
    }

    public GameDef getGameDef() {
        return this.gameDef;
    }


}
