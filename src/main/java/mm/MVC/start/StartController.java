package mm.MVC.start;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mm.gui.ViewManager;
import mm.utilities.GameDef;
import mm.utilities.JSON.JSONLevelIO;
import mm.utilities.Level;

import java.io.File;
import java.io.IOException;

// To be implemented
public class StartController {


    public void setView(StartView view, ViewManager viewManager) {

        view.getStartButton().setOnAction(actionEvent -> {view.getModel().toggleLevelOverlay();});
        view.getCancelButton().setOnAction(actionEvent -> {view.getModel().toggleLevelOverlay();});

        view.getSettingsButton().setOnAction(e -> view.getModel().toggleSettings());

        view.getLevelBuilderBTN().setOnAction(e -> view.getModel().toggleBuilder());

        view.getSaveSettingsButton().setOnAction(e -> {
            GameDef gameDef = view.getModel().getGameDef();
            gameDef.difficulty = view.getDifficultyBox().getValue();
            gameDef.FPS = (int) view.getFpsSlider().getValue();

            System.out.println("Settings saved: Difficulty = " + gameDef.difficulty + ", FPS = " + gameDef.FPS);
            view.getModel().toggleSettings();
        });






        view.getMediumLevelButton().setOnAction(e -> loadEasyLevel(view, viewManager));





        view.getCustomLevelButton().setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Benutzerdefiniertes Level auswählen");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("JSON Dateien", "*.json")
            );

            // Starte im user-level Ordner, optional
            File initialDir = new File("user-levels");
            if (initialDir.exists()) {
                fileChooser.setInitialDirectory(initialDir);
            }

            File selectedFile = fileChooser.showOpenDialog(view.getRoot().getScene().getWindow());

            if (selectedFile != null) {
                System.out.println("Ausgewähltes Level: " + selectedFile.getAbsolutePath());
                // hier kannst du das File ans Model/Controller übergeben oder speichern
            }
        });
    }

    public void loadEasyLevel(StartView view,ViewManager viewManager) {
        String filePath = "src/main/resources/level/medium.json";
        try {
            Level level = JSONLevelIO.loadLevelFromFile(filePath);

            // Ausgabe der geladenen Level-Daten
            System.out.println("Level Name: " + level.getName());
            System.out.println("Difficulty: " + level.getDifficulty());
            viewManager.showGameView(level,view.getModel().getGameDef());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Laden des Levels.");
        }
    }


}





