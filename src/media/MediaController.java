package media;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaController implements Initializable {


    private MediaPlayer mediaPlayer;

    private String filePath = null;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider slider;

    @FXML
    private Slider seekSlider;


    @FXML
    public void handleButtonAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        this.filePath = file.toURI().toString();

        if (this.filePath != null) {
            Media media = new Media(filePath);
            this.mediaPlayer = new MediaPlayer(media);
            this.mediaView.setMediaPlayer(this.mediaPlayer);

            DoubleProperty width = this.mediaView.fitWidthProperty();
            DoubleProperty height = this.mediaView.fitHeightProperty();

            width.bind(Bindings.selectDouble(this.mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(this.mediaView.sceneProperty(), "height"));

            this.slider.setValue(this.mediaPlayer.getVolume() * 100);
            this.slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue() / 100);
                }
            });

            this.mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                    seekSlider.setValue(t1.toSeconds());
                }
            });

            this.seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });

            this.mediaPlayer.play();
        }
    }

    @FXML
    public void playVideo(ActionEvent actionEvent) {
        this.mediaPlayer.play();
        this.mediaPlayer.setRate(1);
    }

    @FXML
    public void pauseVideo(ActionEvent actionEvent) {
        this.mediaPlayer.pause();
    }

    @FXML
    public void stopVideo(ActionEvent actionEvent) {
        this.mediaPlayer.stop();
    }

    @FXML
    public void fasterVideo(ActionEvent actionEvent) {
        this.mediaPlayer.setRate(1.25);
    }

    @FXML
    public void slowerVideo(ActionEvent actionEvent) {
        this.mediaPlayer.setRate(.5);
    }

    @FXML
    public void exitVideo(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
















}
