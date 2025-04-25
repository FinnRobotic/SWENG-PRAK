package mm.MVC;

import javafx.scene.layout.Pane;
import mm.MVC.util.Observer;

public abstract class View implements Observer {

    private Pane root;

    public Pane getRoot() {
        return this.root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

}
