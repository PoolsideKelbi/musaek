package musaek.util;



import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import musaek.model.Album;
import musaek.model.Track;

import org.pmw.tinylog.Logger;



/**
 * Utility class that is used for loading the data to the program.
 * 
 * @author Kelbi
 */
public class Data {
    
    
    /**
     * Static List that contains the <code>Album</code> objects loaded into
     * the program.
     */
    public static List<Album> albums = new ArrayList<Album>();
    
    
    /**
     * Static method that loads the data to the program.
     * 
     * @param pathList a list of <code>Path</code> objects containing the path
     * to the MP3 files
     * 
     * @throws NullPointerException if the list of paths is empty
     */
    public static void loadData(List<Path> pathList) throws NullPointerException{
        if (pathList.isEmpty()) throw new NullPointerException();
        Set<Album> albumSet = new HashSet<>();
        
        for (Path path : pathList) {
            
            MetadataExtractor extractor = new MetadataExtractor(path.toString());
            Track track = extractor.getTrack();
            Logger.trace("Following Track has been initialized: {}", track);
            Album albumOfTrack = extractor.getAlbum();
            
            albumSet.add(albumOfTrack);
            
            for (Album album : albumSet) {
                if (track.getAlbumTitle().equals(album.getTitle())) {
                    album.getTracks().add(track);
                }
            }
            
        }
        
        albums.addAll(albumSet);
        albums.sort( (a1,a2) -> a1.getTitle().compareTo(a2.getTitle()));
    }
    
    
}
