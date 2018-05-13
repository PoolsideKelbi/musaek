package musaek.model;


/**
 * Class that contains a <code>Track</code> object and the corresponding
 * <code>Album</code> album object. This class is used for passing the
 * necessary data to the view.
 * 
 * @author Kelbi
 */
public class TrackOfAlbum {
    
    
    private Track track = new Track();
    private Album album = new Album();

    
    /**
     * Constructor that initializes <code>TrackOfAlbum</code> object.
     * 
     * @param track a <code>Track</code> object
     * containing the details about the track
     * @param album a <code>Album</code> object
     * containing the details about the album
     */
    public TrackOfAlbum(Track track, Album album) {
        this.album = album;
        this.track = track;
    }
    
    
    public Track getTrack() {
        return track;
    }
    public Album getAlbum() {
        return album;
    }

    
    /**
     * Default constructor that initializes <code>TrackOfAlbum</code> object.
     */
    public TrackOfAlbum() {
    }

    
    @Override
    public String toString() {
        return String.format("Currently playing: Track - %-30s Album - %-40s", getTrack().getTitle(), getAlbum().getTitle());
    }
    
    
}
