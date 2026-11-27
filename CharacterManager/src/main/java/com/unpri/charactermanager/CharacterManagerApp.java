package com.unpri.charactermanager;

import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.view.CharacterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterManagerApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                CharacterManagerApp.class.getResource("/com/unpri/charactermanager/view/CharacterView.fxml")
        );
        Scene scene = new Scene(loader.load(), 900, 650);

        CharacterController controller = loader.getController();
        controller.setManager(new CharacterManager());

        stage.setTitle("Character Manager v0.7");
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
