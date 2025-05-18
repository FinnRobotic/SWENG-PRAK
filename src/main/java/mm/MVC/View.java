package mm.MVC;

import javafx.scene.layout.Pane;
import mm.MVC.util.Observer;


/**
 * Abstract base class for all views in the MVC architecture.
 * Implements the Observer interface to receive updates from the model.
 */
public abstract class View implements Observer {

    private Pane root;


    /**
     * Returns the root Pane of this view.
     * This is the main container node for all UI components of the view.
     *
     * @return The root Pane of the view.
     */
    public Pane getRoot() {
        return this.root;
    }


    /**
     * Sets the root Pane of this view.
     *
     * @param root The Pane to set as the root of the view.
     */
    public void setRoot(Pane root) {
        this.root = root;
    }

}
