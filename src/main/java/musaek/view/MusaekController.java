package musaek.view;



import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import musaek.util.Data;
import musaek.model.TrackOfAlbum;

import org.pmw.tinylog.Logger;



public class MusaekController {
    
    
    TrackOfAlbum currentTrack;
    TrackPlayerController player;
    TrackListerController lister;

    
    public TrackPlayerController getPlayer() {
        return player;
    }
    public TrackListerController getLister() {
        return lister;
    }
    
    
    public MusaekController() {
        this.player = new TrackPlayerController();
        this.lister = new TrackListerController(Data.albums);
    }

    
    public void setPlayer(TrackPlayerController player) {
        this.player = player;
    }
    
    
    public GridPane makeScene() {
        
        GridPane gridPane = new GridPane();
        
        Node playerNode = player.makeNode();
        Node listerNode = lister.makeNode();
        
        gridPane.addRow(0, playerNode,listerNode);
        
        
        lister.SelectedTrackProperty().addListener( listener -> {
            if (player.pc.mPlayer != null) player.pc.mPlayer.stop();
            player.currentTrackProperty().setValue(lister.getSelectedTrack());
            player.pc.mPlayer.play();
            Logger.info("Currently playing: {}", player.getCurrentTrack());
        });
        
        
        return gridPane;
    }
    
    
}
