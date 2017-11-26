package puzzle.ui;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import puzzle.imageHandler.ImageHandler;
/**
 * @author Loborg
 */
public class Puzzle extends Application {
    public static GridPane root;
    public static Scene scene;
    private int column;
    private int row;
    
    
    
    @Override
    public void start(Stage stage) {
        root = new GridPane();
        scene = new Scene(root);
        
        palcingImageToStage();
        scramblerButton();
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void scramblerButton(){
        UiController controller = new UiController();
        Button scrB = new Button("Keverés");
        scrB.setOnAction(controller);
        root.add(scrB, column, row+1);
    }
    
    public void palcingImageToStage(){  
        for (int i = 0; i <=  ImageHandler.listOfImagesForPuzzle().size()-1; i++){
            column = i;
            row = 0;

            if (column >= 3) {
                row = 1;
                column = i - 3;
            }
            if (column >= 3){
                row = 2;
                column = i - 6;
            }          
            root.add((Node)ImageHandler.listOfImagesForPuzzle().get(i), column, row);
        }
    }
    
    public void placeImageToStageInRandomOrder(){
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(2), 0, 0);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(4), 1, 0);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(6), 2, 0);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(0), 0, 1);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(5), 1, 1);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(7), 2, 1);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(3), 0, 2);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(1), 1, 2);
        root.add((Node)ImageHandler.listOfImagesForPuzzle().get(8), 2, 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
