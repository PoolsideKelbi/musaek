package musaek.util;



import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import musaek.model.Album;
import musaek.model.Track;
import musaek.model.TrackOfAlbum;



/**
 * Utility class that is used for extracting metadata from MP3 files.
 * 
 * @author Kelbi
 */
public class MetadataExtractor {
    
    
    private Album album;
    private Track track;
    
    
    /**
     * Method that extracts the metadata of an MP3 file and stores the data 
     * in the corresponding fields of <code>Track</code> and <code>Album</code>
     * objects.
     * 
     * @param pathToTrack a <code>String</code> object
     * containing the path to the track
     */
    public MetadataExtractor(String pathToTrack) {
        
         try {
            
            Mp3File mp3File = new Mp3File(pathToTrack);
            ID3v2 id3v2Tag = mp3File.getId3v2Tag();
            
            
            this.album = new Album();
            this.track = new Track();
            
            
            try {
                album.setTitle(id3v2Tag.getAlbum());
            } catch (NullPointerException ex) {
                album.setDefaultTitle();
            }
            try {
                album.setArtist(id3v2Tag.getAlbumArtist());
            } catch (NullPointerException ex) {
                album.setDefaultArtist();
            }
            try {
                album.setCoverArtWithByteArray(new ByteArrayInputStream(id3v2Tag.getAlbumImage()));
            } catch (NullPointerException ex) {
                album.setDefaultCoverArt();
            }
            
            try {
                track.setTitle(id3v2Tag.getTitle());
            } catch (NullPointerException ex) {
                track.setDefaultTitle();
            }
            try {
                track.setNumber(id3v2Tag.getTrack().split("/")[0]);
            } catch (NullPointerException ex) {
                track.setDefaultNumber();
            }
            track.setAlbumTitle(id3v2Tag.getAlbum());
            track.setDuration(mp3File.getLengthInMilliseconds());
            track.setPath(pathToTrack);
        
        } catch (IOException ex) {
            Logger.getLogger(TrackOfAlbum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedTagException ex) {
            Logger.getLogger(TrackOfAlbum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDataException ex) {
            Logger.getLogger(TrackOfAlbum.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    
    public Album getAlbum() {
        return album;
    }
    public Track getTrack() {
        return track;
    }
    
    
}
