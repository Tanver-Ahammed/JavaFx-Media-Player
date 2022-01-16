package media;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMediaPlayer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("Tanver Player");

        scene.setOnMouseClicked(doubleClicked -> {
            if (!stage.isFullScreen() && doubleClicked.getClickCount() == 2) {
                stage.setFullScreen(true);
            } else if (doubleClicked.getClickCount() == 2) {
                stage.setFullScreen(false);
            }
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (!stage.isFullScreen() && keyEvent.getCode() == KeyCode.F)
                stage.setFullScreen(true);
            else if (keyEvent.getCode() == KeyCode.F)
                stage.setFullScreen(false);
        });

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
