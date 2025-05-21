package mm.MVC.game;

import mm.gui.ViewManager;


/**
 * Controller class for the GameView.
 *
 * Responsible for setting up event handlers and managing interactions
 * between the GameView and the underlying model/game logic.
 */
public class GameController {


    /**
     * Sets up the given GameView by adding event handlers and connecting
     * it with the ViewManager.
     *
     * @param view the GameView instance to control
     * @param viewManager the ViewManager responsible for view transitions
     */
    public void setView(GameView view, ViewManager viewManager) {

        view.getStartStopBtn().setOnAction(e -> view.getModel().toggleSimRunning());
    }
}
