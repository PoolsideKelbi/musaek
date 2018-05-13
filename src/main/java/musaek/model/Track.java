package musaek.model;



import java.util.Objects;



/**
 * Class that contains details about a music track. Details include title,
 * number, duration, path and the corresponding album title of the track.
 * 
 * @author Kelbi
 */
public class Track {
    
    
    private String title;
    private String number;
    private long duration;
    private String path;
    private String albumTitle;
    
    
    /**
     * Default constructor that initializes <code>Track</code> object. It sets
     * the default title and number of the track.
     */
    public Track() {
        this.setDefaultTitle();
        this.setDefaultNumber();
    }

    /**
     * Constructor that initializes <code>Track</code> object.
     * 
     * @param title a <code>String</code> object
     * containing the title of the track
     * @param number a <code>String</code> object
     * containing the number of the track
     * @param path a <code>String</code> object
     * containing the path to the track
     * @param albumTitle a <code>String</code> object
     * containing the title of the album containing the track
     * 
     * @throws NullPointerException if any of the parameters is a null object
     */
    public Track(String title, String number, String path, String albumTitle) throws NullPointerException {
        if (title == null) throw new NullPointerException("Title of the Track is not available.");
        if (number == null) throw new NullPointerException("Number of the Track is not available.");
        
        this.title = title;
        this.number = number;
        this.path = path;
        this.albumTitle = albumTitle;
    }

    
    public String getTitle() {
        return title;
    }
    public String getNumber() {
        return number;
    }
    public long getDuration() {
        return duration;
    }
    public String getPath() {
        return path;
    }
    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setTitle(String title) throws NullPointerException {
        if (title == null) throw new NullPointerException("Title of the Track is not available.");
        this.title = title;
    }
    public void setNumber(String number) throws NullPointerException {
        if (number == null) throw new NullPointerException("Number of the Track is not available.");
        this.number = (number.length() == 1? "0"+number : number);
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public void setPath(String path)  {
        this.path = path;
    }
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }
    
    
    @Override
    public String toString() {
        return String.format("Track: | title - %-30s | number - %-5s | duration - %-5s | path - %s ", title, number, getDurationString(), path);
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.path);
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
        final Track other = (Track) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        return true;
    }
    

    /**
     * Method that sets the title of the track
     * to a default <code>String</code> object.
     */
    public void setDefaultTitle() {
        this.title = "Unknown Track";
    }
    /**
     * Method that sets the number of the track
     * to a default <code>String</code> object.
     */
    public void setDefaultNumber() {
        this.number = "00";
    }
    
    /**
     * Method that gets the duration in a [mm:ss] format. 
     * 
     * @return a <code>String</code> object containing the duration in a [mm:ss]
     * format
     */
    public String getDurationString() {
        long minutes = duration / 60000;
        long seconds = Math.round((duration % 60000)/1000);
        return Long.toString(minutes) + ":" + (seconds < 10? "0" + seconds : seconds);
    }
    
    
}
