package musaek.view;



import java.io.InputStream;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import musaek.model.Album;


//CHECKSTYLE:
public class AlbumCoverArtController {
    
    
    Album album;

    InputStream stream = this.getClass().getResourceAsStream("/images/M.png");
    
    ImageView imageView = new ImageView(new Image(stream));
    Label albumTitleLabel = new Label("MUSAEK");
    Label albumArtistLabel = new Label("Ali Kalbiyev");
    
    
    public AlbumCoverArtController() {}
    
    public AlbumCoverArtController(Album album) {
        this.album = album;
        this.albumTitleLabel = new Label(album.getTitle());
        this.albumArtistLabel = new Label(album.getArtist());
        this.imageView = new ImageView(album.getCoverArt());        
    }
    
    
    public void setAlbum(Album album) {
        this.album = album;
        this.albumTitleLabel.setText(album.getTitle());
        this.albumArtistLabel.setText(album.getArtist());
        this.imageView.setImage(album.getCoverArt());
    }  
    
            
    public Node makeNode() {
        
        
        ColorAdjust darken = new ColorAdjust();
        BoxBlur blur = new BoxBlur(0,0,0);
        blur.setInput(darken);
        
        
        imageView.setFitHeight(610); imageView.setFitWidth(610);
        imageView.setEffect(blur);
        
        albumTitleLabel.setFont(new Font("Century Gothic", 25));
        albumTitleLabel.setTextFill(Color.WHITE);
        albumTitleLabel.setOpacity(0);
        
        albumArtistLabel.setFont(new Font("Century Gothic", 25));
        albumArtistLabel.setTextFill(Color.WHITE);
        albumArtistLabel.setOpacity(0);
        
        Label by = new Label("by");
        by.setFont(new Font("Century Gothic", 15));
        by.setTextFill(Color.WHITE);
        by.setOpacity(0);
        
        
        imageView.setOnMouseEntered( event -> {
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                    new KeyValue(imageView.scaleXProperty(), 1),
                    new KeyValue(imageView.scaleYProperty(), 1),
                    new KeyValue(darken.brightnessProperty(), 0),
                    new KeyValue(blur.heightProperty(), 0),
                    new KeyValue(blur.widthProperty(), 0),
                    new KeyValue(blur.iterationsProperty(), 0),
                    new KeyValue(albumTitleLabel.opacityProperty(), 0),
                    new KeyValue(albumArtistLabel.opacityProperty(), 0),
                    new KeyValue(by.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(200),
                    new KeyValue(imageView.scaleXProperty(), 1.05),
                    new KeyValue(imageView.scaleYProperty(), 1.05),
                    new KeyValue(darken.brightnessProperty(), -0.5),
                    new KeyValue(blur.heightProperty(), 10),
                    new KeyValue(blur.widthProperty(), 10),
                    new KeyValue(blur.iterationsProperty(), 10),
                    new KeyValue(albumTitleLabel.opacityProperty(), 1),
                    new KeyValue(albumArtistLabel.opacityProperty(), 1),
                    new KeyValue(by.opacityProperty(), 1)));
            timeline.play();                   
        });
        imageView.setOnMouseExited( event -> {   
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                    new KeyValue(imageView.scaleXProperty(), 1.05),
                    new KeyValue(imageView.scaleYProperty(), 1.05),
                    new KeyValue(darken.brightnessProperty(), -0.5),
                    new KeyValue(blur.heightProperty(), 10),
                    new KeyValue(blur.widthProperty(), 10),
                    new KeyValue(blur.iterationsProperty(), 3),
                    new KeyValue(albumTitleLabel.opacityProperty(), 1),
                    new KeyValue(albumArtistLabel.opacityProperty(), 1),
                    new KeyValue(by.opacityProperty(), 1)),
                new KeyFrame(Duration.millis(200),
                    new KeyValue(imageView.scaleXProperty(), 1),
                    new KeyValue(imageView.scaleYProperty(), 1),
                    new KeyValue(darken.brightnessProperty(), 0),
                    new KeyValue(blur.heightProperty(), 0),
                    new KeyValue(blur.widthProperty(), 0),
                    new KeyValue(blur.iterationsProperty(), 0),
                    new KeyValue(albumTitleLabel.opacityProperty(), 0),
                    new KeyValue(albumArtistLabel.opacityProperty(), 0),
                    new KeyValue(by.opacityProperty(), 0)));
            timeline.play();  
        });
        
        
        VBox detailsPane = new VBox(20,albumTitleLabel,by,albumArtistLabel);
        detailsPane.setAlignment(Pos.CENTER);
        detailsPane.setMouseTransparent(true);
        
        StackPane stackPane = new StackPane(imageView,detailsPane);
        
        
        return stackPane;
    }
}
