package mm.MVC.game;

import mm.gui.ViewManager;

public class GameController {


    public void setView(GameView view, ViewManager viewManager) {

        view.getStartStopBtn().setOnAction(e -> view.getModel().toggleSimRunning());
    }
}
