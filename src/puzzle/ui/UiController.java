package puzzle.ui;

import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author Loborg
 */
public class UiController extends Puzzle implements EventHandler {

    @Override
    public void handle(Event event) {     
        if (event.getEventType() == ActionEvent.ACTION){
            placeImageToStageInRandomOrder();
        }
    }   
}
