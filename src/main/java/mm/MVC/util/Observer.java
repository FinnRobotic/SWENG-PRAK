package mm.MVC.util;


/**
 * Interface for objects that observe changes in an Observable.
 * Implementing classes must define the update method to handle notifications.
 */
public interface Observer {


    /**
     * Called when the Observable this observer is registered to has changed.
     */
    public void update();

}
