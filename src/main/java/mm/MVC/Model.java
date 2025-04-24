package mm.MVC;

import mm.MVC.util.Observable;
import mm.utilities.GameDef;

public class Model extends Observable {

    private GameDef gameDef;

    public Model() {
        this.gameDef = new GameDef();
    }

    public GameDef getGameDef() {
        return this.gameDef;
    }

    public void setGameDef(GameDef gameDef) {
        this.gameDef = gameDef;
    }



}
