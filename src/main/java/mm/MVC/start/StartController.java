package mm.MVC.start;


import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mm.gui.ViewManager;
import mm.utilities.GameDef;
import mm.utilities.Level;


import java.io.IOException;

import static mm.utilities.HelperUI.addDraggableResizableRotatableBox;
import static mm.utilities.JSON.JSONLevelIO.loadLevelFromDirectory;
import static mm.utilities.JSON.JSONLevelIO.loadLevelFromFile;

// To be implemented
public class StartController {


    public void setView(StartView view, ViewManager viewManager) {

       setStart(view, viewManager);

       setLevelBuilder(view, viewManager);


        view.getSaveSettingsButton().setOnAction(e -> {
            GameDef gameDef = view.getModel().getGameDef();
            gameDef.difficulty = view.getDifficultyBox().getValue();
            gameDef.FPS = (int) view.getFpsSlider().getValue();

            System.out.println("Settings saved: Difficulty = " + gameDef.difficulty + ", FPS = " + gameDef.FPS);
            view.getModel().toggleSettings();
        });



    }

    private void setStart(StartView view, ViewManager viewManager) {
        view.getStartButton().setOnAction(actionEvent -> {view.getModel().toggleLevelOverlay();});
        view.getSettingsButton().setOnAction(e -> view.getModel().toggleSettings());
        view.getCancelButton().setOnAction(actionEvent -> {view.getModel().toggleLevelOverlay();});
        view.getLevelBuilderBTN().setOnAction(e -> view.getModel().startBuilder());
        view.getMediumLevelButton().setOnAction(e -> loadMediumLevel(view, viewManager));
        view.getCustomLevelButton().setOnAction(e -> {
            loadCustomLevel(view, viewManager);
        });
    }

    public void loadMediumLevel(StartView view,ViewManager viewManager) {
        String filePath = "src/main/resources/level/medium.json";
        try {
            Level level = loadLevelFromFile(filePath);

            // Ausgabe der geladenen Level-Daten
            System.out.println("Level Name: " + level.getName());
            System.out.println("Difficulty: " + level.getDifficulty());
            viewManager.showGameView(level,view.getModel().getGameDef());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Laden des Levels.");
        }
    }

    public void loadCustomLevel(StartView view, ViewManager viewManager) {
        try {
            Level level = loadLevelFromDirectory(view.getRoot().getScene().getWindow());
            System.out.println("Level Name: " + level.getName());
            System.out.println("Difficulty: " + level.getDifficulty());
            viewManager.showGameView(level,view.getModel().getGameDef());

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setLevelBuilder(StartView view, ViewManager viewManager) {
        view.getBuilderExitBTN().setOnAction(e -> {
            view.getModel().saveAndCloseBuilder(view);
            view.resetBuilderUI();
        });
        view.getBuilderStage().getScene().setOnKeyPressed(e -> {checkBoxInputs(view, e);});
        view.getPlaceBox().setOnAction(e -> {
            view.getModel().getBuilderLevel().addObject(addDraggableResizableRotatableBox(view.getBuilder()));
        });
    }

    public void checkBoxInputs(StartView view, KeyEvent e) {

        Boolean gravityXBoxSelected = view.getGravityXInput().isFocused();
        Boolean gravityYBoxSelected = view.getGravityYInput().isFocused();
        Boolean textFieldSelected = view.getNameInput().isFocused();

        if (e.getCode() == KeyCode.ENTER) {
            if (gravityXBoxSelected) {
                try {
                    String inputText = view.getGravityXInput().getText().replace(",", ".");
                    float gravityX = Float.parseFloat(inputText);
                    view.getModel().getBuilderLevel().setGravityX(gravityX);
                    System.out.println("GravityX selected: " + gravityX);
                } catch (NumberFormatException ex) {
                    System.out.println("Ungültige Eingabe: Keine gültige Kommazahl");
                    System.out.println("GravityBox X");
                }
            }

            if (gravityYBoxSelected) {
                try {
                    String inputText = view.getGravityYInput().getText().replace(",", ".");
                    float gravityY = Float.parseFloat(inputText);
                    view.getModel().getBuilderLevel().setGravityY(gravityY);
                    System.out.println("GravityY selected: " + gravityY);
                } catch (NumberFormatException ex) {
                    System.out.println("Ungültige Eingabe: Keine gültige Kommazahl");
                    System.out.println("GravityBox Y");
                }
            }
            if (textFieldSelected) {

                view.getModel().getBuilderLevel().setName(view.getNameInput().getText());
                System.out.println("Name selected: " + view.getNameInput().getText());
            }
        }

    }

}





