package mm.MVC;

import javafx.scene.Parent;
import mm.MVC.util.Observer;

public abstract class View implements Observer {

    private Parent root;

    private Model model;

    public Parent getRoot() {
        return this.root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Model getModel() {

        return this.model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
        update();
    }
}
