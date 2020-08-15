package org.usfirst.frc.team5740.util;
/**
 * This class is for creating custom cells and linking data to them for java fx 
 * @author Nicholas Blackburn
 * @
 */
import javafx.scene.control.ListCell;

public class PathListCell extends ListCell<String>{
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string,empty);
        if(string != null)
        {
            Data data = new Data();
            data.setInfo(string);
            setGraphic(data.getBox());
        }
    }
}