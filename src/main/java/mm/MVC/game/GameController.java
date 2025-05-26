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

        view.getResetBtn().setOnAction(e -> {

            view.getModel().resetLevel();
        });

        view.getExitLevelBTN().setOnAction(e -> {
            viewManager.showStartView();
        });

        view.getWonCloseLevelBTN().setOnAction(e -> {
            viewManager.showStartView();
        });

        view.getLostCloseLevelBTN().setOnAction(e -> {
            viewManager.showStartView();
        });

        view.getWonPlayAgainBTN().setOnAction(e -> {
            view.getModel().resetLevel();
        });

        view.getLostPlayAgainBTN().setOnAction(e -> {
            view.getModel().resetLevel();
        });


    }
}
