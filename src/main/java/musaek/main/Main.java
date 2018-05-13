package musaek.main;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import musaek.util.Data;
import musaek.util.FileVisitor;
import musaek.view.MusaekController;

import org.pmw.tinylog.Logger;


/**
 * Class that contains the main method of the application.
 * 
 * @author Kelbi
 */
public class Main extends Application {
    
    
    @Override
    public void init() {
        Logger.info("User has started the application.");
        System.setProperty("prism.lcdtext", "false");
    }
    
      
    @Override
    public void start(Stage primaryStage) {
        
        //Phase 1 - Select a folder and walk it
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Path directory = null;
        FileVisitor visitor = new FileVisitor();
        
        directoryChooser.setTitle("Select A Folder");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        
        try { directory = directoryChooser.showDialog(primaryStage).toPath(); }
        catch (NullPointerException ex) { Logger.error("No directory has been chosen."); System.exit(0); }
        Logger.debug("Directory has been chosen.");
        
        try { Files.walkFileTree(directory, visitor); }
        catch (IOException ex) { Logger.error("Something bad has happened."); System.exit(0); }
        Logger.debug("Directory has been walked.");
        
        //Phase 2 - Load the data
        try { Data.loadData(visitor.paths); } 
        catch (NullPointerException ex) { Logger.error("No MP3 files have been found."); System.exit(0); }
        Logger.debug("MP3 files have been loaded to the application.");
        
        
        //Phase 3 - Load the stuff to the GUI
        MusaekController musaek = new MusaekController();
        Parent root = musaek.makeScene();
        Scene scene = new Scene(root,900,600);
        Logger.debug("Scene has been decorated.");
        
        
        //Phase 4 - Load the stage
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.setTitle("MUSAEK");
        primaryStage.setScene(scene);
        primaryStage.show();
        Logger.debug("Application has been displayed.");
        
    }
    
    
    @Override
    public void stop() { 
        Logger.info("User has closed the application."); 
    }
    
    /**
     * Main method of the application.
     */
    public static void main() {
        launch();
    }
    
    
}
