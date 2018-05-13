package musaek.util;



import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;



/**
 * Utility class that is used for obtaining the paths to the available MP3 files.
 * 
 * @author Kelbi
 */
public class FileVisitor extends SimpleFileVisitor<Path> {
    
    
    /**
     * <code>List</code> containing the paths to the available MP3 files.
     */
    public List<Path> paths = new ArrayList();
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes atb) throws IOException {
        
        if(file.toString().endsWith(".mp3")) paths.add(file);
        
        return FileVisitResult.CONTINUE;
    }
    
    
}
