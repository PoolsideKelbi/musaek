package musaek.view;



import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import musaek.model.TrackOfAlbum;



public class TrackPlayerController {
    
    
    AlbumCoverArtController acac;
    PlayerController pc;
    
    private final ObjectProperty<TrackOfAlbum> currentTrackProperty = new SimpleObjectProperty<>();

    
    public TrackPlayerController() {
        this.acac = new AlbumCoverArtController();
        this.pc = new PlayerController();
    }
    
    public TrackPlayerController(TrackOfAlbum currentTrack) {
        this.currentTrackProperty.set(currentTrack);
        this.acac = new AlbumCoverArtController(currentTrack.getAlbum());
        this.pc = new PlayerController(currentTrack.getTrack());
    }
    
    
    public void setTrackOfAlbum(TrackOfAlbum currentTrack) {
        this.currentTrackProperty.setValue(currentTrack);
        this.acac.setAlbum(currentTrack.getAlbum());
        this.pc.setTrack(currentTrack.getTrack());
    }
    
    
    public TrackOfAlbum getCurrentTrack() {
        return currentTrackProperty.get();
    }

    public void setCurrentTrack(TrackOfAlbum value) {
        currentTrackProperty.set(value);
    }

    public ObjectProperty currentTrackProperty() {
        return currentTrackProperty;
    }
    
    
    public StackPane makeNode() {
        
        BorderPane bpane = new BorderPane();
        bpane.setBottom(pc.makeNode());
        bpane.setPickOnBounds(false);
        bpane.setTranslateY(90);
        
        StackPane pane = new StackPane(acac.makeNode(),bpane);
        
        
        currentTrackProperty.addListener( listener -> {
            if (currentTrackProperty.get() == null) bpane.visibleProperty().setValue(Boolean.FALSE);
            setTrackOfAlbum(currentTrackProperty.get());
        });
        
        
        pane.setOnMouseEntered( event -> {
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(bpane.translateYProperty(), 90)),
                new KeyFrame(Duration.millis(200),
                    new KeyValue(bpane.translateYProperty(), 0)));
            timeline.play();
        });
        pane.setOnMouseExited( event -> {
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(bpane.translateYProperty(), 0)),
                new KeyFrame(Duration.millis(200),
                    new KeyValue(bpane.translateYProperty(), 90)));
            timeline.play();
        });
        
        
        return pane;
    }
    
}
