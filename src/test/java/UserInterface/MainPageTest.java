package UserInterface;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.junit.Test;
import org.mockito.internal.matchers.Find;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * This class is for tesing the Main page
 * @author Nicholas BlackBurn
 */
public class MainPageTest extends ApplicationTest {

    private FXMLLoader loader = new FXMLLoader();
  
    
    // Starts JavaFX Gui
    @Override
    public void start(Stage stage) throws Exception {
       
        loader.setLocation(getClass().getResource("/Main.fxml"));
        Parent root = loader.load(); 
        Scene scene = new Scene(root, 640, 400);
        stage.setTitle("PathGenerator By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
    }

    // Checks to see if the Window contains the button and clicks it 
    @Test 
    public void should_contain_button() {
        clickOn("#start");
        verifyThat("#start",  LabeledMatchers.hasText("Start Creating Paths"));
    }
 // Checks to see if the Window contains the button and clicks it 
    @Test public void should_click_on_button() {
       // then:
        verifyThat("#exit",  LabeledMatchers.hasText("Exit Path Generator"));
    }
}
