package musaek.view;



import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import musaek.model.Album;
import musaek.model.Track;
import musaek.model.TrackOfAlbum;

import org.pmw.tinylog.Logger;



public class  TrackListerController {
    
     private final ObjectProperty<TrackOfAlbum> selectedTrackProperty = new SimpleObjectProperty<>();

        public TrackOfAlbum getSelectedTrack() {
            return selectedTrackProperty.get();
        }

        public void setSelectedTrack(TrackOfAlbum value) {
            selectedTrackProperty.set(value);
        }

        public ObjectProperty SelectedTrackProperty() {
            return selectedTrackProperty;
        }
    
    List<Album> albumList; 
   
    
    public TrackListerController(List<Album> albumList) {
        this.albumList = albumList;
    }
    
    
    public ScrollPane makeNode() {
        
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(25);
        vBox.setPadding(new Insets(24));
        
        
        
        for(Album album : albumList){
            ImageView albumCoverArt = new ImageView(album.getCoverArt());
            albumCoverArt.setFitHeight(250);albumCoverArt.setFitWidth(250);
            
            
            ListView trackList = new ListView();
            for(Track track : album.getTracks()){
                Label trackTitle = new Label(( track.getNumber() + ". " + track.getTitle()));
                trackTitle.setFont(new Font("Century Gothic", 12));
                trackList.getItems().add(trackTitle);
                
                trackTitle.setOnMouseClicked( event -> {
                    selectedTrackProperty.setValue(new TrackOfAlbum(track,album));
                });
            }
            trackList.setPrefSize(250,album.getTracks().size()*22);
            
            Accordion accordion = new Accordion(new TitledPane("", trackList));
            
            VBox albumVBox = new VBox(albumCoverArt,accordion);
            vBox.getChildren().add(albumVBox);
            Logger.trace("Following Album has been added to the scene: {}", album); 
        }
        
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        return scrollPane;
    }
    
}
