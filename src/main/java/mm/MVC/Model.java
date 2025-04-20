package mm.MVC;

import mm.MVC.util.Observable;
import mm.blueprints.GameDef;

public class Model extends Observable {

    private GameDef gameDef;

    public Model() {
        this.gameDef = new GameDef();
    }

    public GameDef getGameDef() {
        return this.gameDef;
    }

    public void setFPS(int fps) {
        this.gameDef.setFPS(fps);
        notifyObservers();
    }

    public void setDifficulty(GameDef.Difficulty difficulty) {
        this.gameDef.setDifficulty(difficulty);
        notifyObservers();
    }



}
