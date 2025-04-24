package mm.MVC.start;


import mm.gui.ViewManager;

// To be implemented
public class StartController {


    public void setView(StartView view, ViewManager viewManager) {

        view.getStartButton().setOnAction(actionEvent -> {viewManager.showGameView();});

    }
}
