package puzzle.imageHandler;

import java.util.ArrayList;
import javafx.event.EventType;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import puzzle.ui.*;

/**
 * @author Loborg
 */

/*ImageView-t és úgy egyébként JavaFX-es Nodokat Objektumokat nem lehet meghívni csak akkor ha származtatjuk vagy az Apllication osztájból 
vagy abból az Osztályból ami már az Application Osztályból származik.*/

public class ImageHandler extends Puzzle{
    
    public static Dragboard db;
    public static ClipboardContent clipboard, cb;
    
    
    public static String[] path(){
        String[] path = new String[10];       
        for (int i = 1; i < path.length; i++){
            path[i] = "puzzle/resource/0"+i+".jpg";
        }
        return path;
    }
    
    public static ArrayList listOfImagesForPuzzle(){
            String[] path = path();
            UiController controller = new UiController();
            ArrayList<ImageView> imageView = new ArrayList<>();
        for (int i = 1; i < path.length; i++){
            imageView.add(new ImageView(path[i]));           
        }
        
        for (int i = 0; i < imageView.size(); i++){
            ImageView source = (ImageView)imageView.get(i);
            ImageView target = (ImageView)imageView.get(i);
            
                source.setOnDragDetected((MouseEvent event) -> {
                    db = source.startDragAndDrop(TransferMode.MOVE);
                    clipboard = new ClipboardContent();
                    clipboard.putImage(source.getImage());
                    db.setContent(clipboard);
                    
                    event.consume();
                });
                
                target.setOnDragOver((DragEvent event) -> {
                    if (event.getGestureSource() != target 
                            && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });
                
                target.setOnDragEntered((DragEvent event) -> {
                    if (event.getGestureSource() != target 
                            && event.getDragboard().hasImage()){
                        target.setStyle("-fx-effect: innershadow(gaussian, #00a8ff, 40, 0.5, 0, 0)");
                    }
                    event.consume();
                });
                
                target.setOnDragExited((DragEvent event) -> {
                   target.setStyle("");
                   
                   event.consume();
                });

                target.setOnDragDropped((DragEvent event) -> {
                    cb = new ClipboardContent();
                    cb.putImage(target.getImage());
                    
                    boolean suces = false;
                    if (db.hasImage()){
                        target.setImage(db.getImage());
                        
                        suces = true;
                    }
                    event.setDropCompleted(suces);
                    event.consume();
                });
                
                source.setOnDragDone((DragEvent event) -> {
                    if (event.getTransferMode() == TransferMode.MOVE)
                    source.setImage(cb.getImage());
                });
        }
        
        return imageView; 
    }
    
//    public void addEventToImages(){
//        ImageView image_1 = (ImageView) listOfImagesForPuzzle().get(0);
//        image_1.setOnMouseClicked((MouseEvent event)->{
//            
//        });
//    }
}
