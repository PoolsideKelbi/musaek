package musaek.model;



import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;



/**
 * Class that contains details about a music album. Details include title,
 * artist, cover art and available tracks of the album.
 * 
 * @author Kelbi
 */
public class Album {
    
   
    private String title;
    private String artist;
    private Image coverArt;
    private List<Track> tracks = new ArrayList<>();

   
    /**
     * Default constructor that initializes <code>Album</code> object. It sets 
     * the default title, artist and cover art of the album.
     */
    public Album() {
        this.setDefaultTitle();
        this.setDefaultArtist();
        this.setDefaultCoverArt();
    }
    
    /**
     * Constructor that initializes <code>Album</code> object.
     * 
     * @param title a <code>String</code> object 
     * containing the title of the album
     * @param artist a <code>String</code> object 
     * containing the artist of the album
     * @param coverArt an <code>Image</code> object 
     * containing the cover art of the album
     * 
     * @throws NullPointerException if any of the parameters is a null object 
     */
    public Album(String title, String artist, Image coverArt) throws NullPointerException {
        if (title == null) throw new NullPointerException("Title of the Album is not available.");
        if (artist == null) throw new NullPointerException("Artist of the Album is not available.");
        if (coverArt == null) throw new NullPointerException("Cover Art of the Album is not available.");
        
        this.title = title;
        this.artist = artist;
        this.coverArt = coverArt;
    }

    
    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public Image getCoverArt() {
        return coverArt;
    }
    public List<Track> getTracks() {
        return tracks;
    }

    public void setTitle(String title) throws NullPointerException {
        if (title == null) throw new NullPointerException("Title of the Album is not available.");
        this.title = title;
    }
    public void setArtist(String artist) throws NullPointerException {
        if (artist == null) throw new NullPointerException("Artist of the Album is not available.");
        this.artist = artist;
    }
    public void setCoverArt(Image coverArt) throws NullPointerException {
        if (coverArt == null) throw new NullPointerException("Cover Art of the Album is not available");
        this.coverArt = coverArt;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    

    @Override
    public String toString() {
        return String.format("Album: |title - %-40s| artist -%-20s|" ,title ,artist);
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.title);
        hash = 73 * hash + Objects.hashCode(this.artist);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        return true;
    }
    
    
    /**
     * Method that sets the title of the album 
     * to a default <code>String</code> object.
     */
    public void setDefaultTitle() {
        this.title = "Unknown Album";
    }
    /**
     * Method that sets the artist of the album 
     * to a default <code>String</code> object.
     */
    public void setDefaultArtist() {
        this.artist = "Unknown Artist";
    }
    /**
     * Method that sets the cover art of the album 
     * to a default <code>Image</code> object.
     */
    public void setDefaultCoverArt() {
        InputStream stream = this.getClass().getResourceAsStream("/images/defaultArt.jpg");
        this.coverArt = new Image(stream);
    }
    
    
    /**
     * Method that sets the cover art of the album.
     * 
     * @param coverArt a <code>ByteArrayInputStream</code> object containing
     * the byte value of the corresponding image
     * 
     * @throws NullPointerException if the parameter is a null object
     */
    public void setCoverArtWithByteArray(ByteArrayInputStream coverArt) throws NullPointerException {
        if (coverArt == null) throw new NullPointerException("Cover Art (byte array) of the Album is not available.");
        this.coverArt = new Image(coverArt);
    }
    
    
}
