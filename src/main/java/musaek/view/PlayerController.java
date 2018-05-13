package musaek.view;



import java.io.File;
import java.io.InputStream;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import musaek.model.Track;



public class PlayerController {
    
    
    Track track;
    
    Label trackTitleLabel = new Label("");
    Label trackDurationPassed = new Label("");
    Label trackDurationLeft = new Label("");
    
    
    public PlayerController() {}
    
    public PlayerController(Track track) {
        this.track = track;
        this.trackTitleLabel = new Label(track.getTitle());
        this.trackDurationPassed = new Label("0:00");
        this.trackDurationLeft = new Label(track.getDurationString());
        this.setup();
    }
    
    
    public void setTrack(Track track) {
        this.track = track;
        this.trackTitleLabel.setText(track.getTitle());
        this.trackDurationPassed.setText("0:00");
        this.trackDurationLeft.setText(track.getDurationString());
        this.setup();
    }
    
    
    Slider timeSlider = new Slider();
    MediaPlayer mPlayer = null;
    
    
    private void setup() {
        
        
        mPlayer = new MediaPlayer(new Media(new File(track.getPath()).toURI().toString()));
        
        timeSlider.setMin(0);
        timeSlider.setMax(track.getDuration());
        timeSlider.setPrefSize(380, 3);   
        timeSlider.setStyle("-fx-background-color: transparent ");
        
        
        InvalidationListener timeSliderListener = new InvalidationListener() {
            @Override
            public void invalidated(Observable listener) {
                timeSlider.setValue(mPlayer.getCurrentTime().toMillis());
            }
        };
        mPlayer.currentTimeProperty().addListener(timeSliderListener);
        timeSlider.setOnMousePressed(event -> {
            mPlayer.currentTimeProperty().removeListener(timeSliderListener);
        });
        timeSlider.setOnMouseReleased(event -> {
            mPlayer.seek(Duration.millis(timeSlider.getValue()));
            mPlayer.currentTimeProperty().addListener(timeSliderListener);
        });
        
        
        mPlayer.currentTimeProperty().addListener(listener -> {
            int currentTime = (int) mPlayer.getCurrentTime().toSeconds();
            int passedMinutes = currentTime / 60;
            int passedSeconds = currentTime % 60;
            this.trackDurationPassed.setText(Integer.toString(passedMinutes) + ":" + (passedSeconds < 10? "0" + passedSeconds : passedSeconds));
            
            int leftTime = (int) (mPlayer.getStopTime().toSeconds() - currentTime);
            int leftMinutes = leftTime / 60;
            int leftSeconds = leftTime % 60;
            this.trackDurationLeft.setText(Integer.toString(leftMinutes) + ":" + (leftSeconds < 10? "0" + leftSeconds : leftSeconds));
        });
        
        
    }
    
    
    public VBox makeNode() {
        
        
        ColorAdjust buttonEffect = new ColorAdjust(0, -0.8, 1, 0);
        
        
        trackTitleLabel.setFont(new Font("Century Gothic", 25));
        trackTitleLabel.setTextFill(Color.WHITE);
        
        trackDurationPassed.setFont(new Font("Century Gothic", 15));
        trackDurationPassed.setTextFill(Color.WHITE);
        
        trackDurationLeft.setFont(new Font("Century Gothic", 15));
        trackDurationLeft.setTextFill(Color.WHITE);
        
        
        InputStream stream;
        stream = this.getClass().getResourceAsStream("/icons/play.png");
        ImageView playIcon = new ImageView(new Image(stream));
        stream = this.getClass().getResourceAsStream("/icons/pause.png");
        ImageView pausIcon = new ImageView(new Image(stream));
        stream = this.getClass().getResourceAsStream("/icons/forward.png");
        ImageView nextIcon = new ImageView(new Image(stream));
        stream = this.getClass().getResourceAsStream("/icons/backward.png");
        ImageView backIcon = new ImageView(new Image(stream));
        stream = this.getClass().getResourceAsStream("/icons/repeat.png");
        ImageView reptIcon = new ImageView(new Image(stream));
        stream = this.getClass().getResourceAsStream("/icons/unrepeat.png");
        ImageView unreptIcon=new ImageView(new Image(stream));
        stream = this.getClass().getResourceAsStream("/icons/mute.png");
        ImageView muteIcon = new ImageView(new Image(stream));
        stream = this.getClass().getResourceAsStream("/icons/unmute.png");
        ImageView unmuteIcon=new ImageView(new Image(stream));
        
                
        Button playPauseButton = new Button();
        playPauseButton.setGraphic(pausIcon);
        playPauseButton.setStyle("-fx-background-color: transparent");
        playPauseButton.setEffect(buttonEffect);
                
        Button backButton = new Button();
        backButton.setGraphic(backIcon);
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setEffect(buttonEffect);
                
        Button nextButton = new Button();
        nextButton.setGraphic(nextIcon);
        nextButton.setStyle("-fx-background-color: transparent");
        nextButton.setEffect(buttonEffect);    
        
        ToggleButton toggleRepeatButton = new ToggleButton();
        toggleRepeatButton.setGraphic(unreptIcon);
        toggleRepeatButton.setStyle("-fx-background-color: transparent");
        toggleRepeatButton.setEffect(buttonEffect);
                
        ToggleButton toggleMuteButton = new ToggleButton();
        toggleMuteButton.setGraphic(unmuteIcon);
        toggleMuteButton.setStyle("-fx-background-color: transparent");
        toggleMuteButton.setEffect(buttonEffect);
        
        
        playPauseButton.setOnAction(event -> {
            Status status = mPlayer.getStatus();
            if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED) {
                mPlayer.play();
                playPauseButton.setGraphic(pausIcon);
            } 
            else {
                mPlayer.pause();
                playPauseButton.setGraphic(playIcon);
            }
        });
        
        backButton.setOnAction(event -> {
            mPlayer.seek(Duration.seconds(mPlayer.getCurrentTime().toSeconds()-10));
        });
        
        nextButton.setOnAction(event -> {
            mPlayer.seek(Duration.seconds(mPlayer.getCurrentTime().toSeconds()+10));
        });
        
        toggleMuteButton.selectedProperty().addListener(listener -> {
            if (toggleMuteButton.isSelected()){
                mPlayer.setMute(true);
                toggleMuteButton.setGraphic(muteIcon);
            }
            else {
                mPlayer.setMute(false);
                toggleMuteButton.setGraphic(unmuteIcon);
            }
        });
        
        toggleRepeatButton.selectedProperty().addListener(listener -> {
            if (toggleRepeatButton.isSelected()){
                mPlayer.setCycleCount(mPlayer.INDEFINITE);
                toggleRepeatButton.setGraphic(reptIcon);
            }
            else {
                mPlayer.setCycleCount(1);
                toggleRepeatButton.setGraphic(unreptIcon);
            }
        });
        
        
        HBox buttonBox = new HBox(10,backButton,playPauseButton,nextButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        HBox timeBox = new HBox(10,toggleMuteButton,trackDurationPassed,timeSlider,trackDurationLeft,toggleRepeatButton);
        timeBox.setAlignment(Pos.CENTER);
        
        VBox vBox = new VBox(10, trackTitleLabel,timeBox,buttonBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: linear-gradient(to top, rgba(0,0,0,1), rgba(255,255,255,0))");
        vBox.setPadding(new Insets(30,10,10,20));
        
        
        return vBox;
    }
    
}
