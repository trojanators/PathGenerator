package UserInterface;

import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.junit.Test;
import org.mockito.internal.matchers.Find;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * This class is for testing the PathDataPage
 * @author Nicholas Blackburn
 */
public class PathGenerationPageTest extends ApplicationTest {

    private FXMLLoader loader = new FXMLLoader();

    // Starts JavaFX Gui
    @Override
    public void start(Stage stage) throws Exception {
        loader.setLocation(getClass().getResource("/PathData.fxml"));
        Parent root_path = loader.load();
        Scene scene = new Scene(root_path, 1376, 857);
        stage.setTitle("PathGenerator By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This is the Text Box Test Section 
     * This only Test's Text Box Inputs
     */
    @Test
    public void should_enter_text_waypoint_y_max(){
        clickOn("#waypoint_y_input").type(KeyCode.NUMPAD5, KeyCode.NUMPAD5, KeyCode.PERIOD,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2);
        verifyThat("#waypoint_y_input", LabeledMatchers.hasText("55.2222"));
    }

    @Test
    public void should_enter_text_waypoint_x_max(){
        clickOn("#waypoint_x_input").type(KeyCode.NUMPAD5, KeyCode.NUMPAD5, KeyCode.PERIOD,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2);
        verifyThat("#waypoint_x_input", LabeledMatchers.hasText("55.2222"));
    }

    @Test
    public void should_enter_text_waypoint_theta_max(){
        clickOn("#waypoint_theta_input").type(KeyCode.NUMPAD5, KeyCode.NUMPAD5, KeyCode.PERIOD,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2);
        verifyThat("#waypoint_theta_input", LabeledMatchers.hasText("55.2222"));
    }

    @Test
    public void should_enter_text_waypoint_acc_max(){
        clickOn("#waypoint_acc_input").type(KeyCode.NUMPAD5, KeyCode.NUMPAD5, KeyCode.PERIOD,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2);
        verifyThat("#waypoint_acc_input", LabeledMatchers.hasText("55.2222"));
    }

    @Test
    public void should_enter_text_waypoint_jerk_max(){
        clickOn("#waypoint_jerk_input").type(KeyCode.NUMPAD5, KeyCode.NUMPAD5, KeyCode.PERIOD,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2);
        verifyThat("#waypoint_jerk_input", LabeledMatchers.hasText("55.2222"));
    }

    @Test
    public void should_enter_text_waypoint_velocity_max(){
        clickOn("#waypoint_velocity_input").type(KeyCode.NUMPAD5, KeyCode.NUMPAD5, KeyCode.PERIOD,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2);
        verifyThat("#waypoint_velocity_input", LabeledMatchers.hasText("55.2222"));
    }

    @Test
    public void should_enter_text_waypoint_dt_max(){
        clickOn("#waypoint_velocity_input").type(KeyCode.NUMPAD5, KeyCode.NUMPAD5, KeyCode.PERIOD,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2,KeyCode.NUMPAD2);
        verifyThat("#waypoint_velocity_input", LabeledMatchers.hasText("55.2222"));
    }


    // Checks to see if the Window contains the button and clicks it
    @Test
    public void should_click_on_new_waypoint() {
        clickOn("#new_waypoint");
        verifyThat("#new_waypoint", LabeledMatchers.hasText("add Waypoint"));
    }

    @Test
    public void should_click_on_remove_waypoint() {
        // when:
        clickOn("#remove_path");
        verifyThat("#remove_path", LabeledMatchers.hasText("remove WayPoint"));
    }

    @Test
    public void should_click_on_generate_path(){
        clickOn("#generate_path");
        verifyThat("#generate_path",LabeledMatchers.hasText("Test (Fill random"));
    }

    @Test
    public void should_click_on_invert_y(){
        clickOn("#invert_y");
        verifyThat("#invert_y", LabeledMatchers.hasText("Invert Y"));

    }

    @Test
    public void should_click_on_enable_pi(){
        clickOn("#pi_enable");
        verifyThat("#pi_enable", LabeledMatchers.hasText("enable pi theta"));
    }

    @Test
    public void should_click_on_enable_neg_pi(){
        clickOn("#neg_pi");
        verifyThat("#neg_pi", LabeledMatchers.hasText("enable neg pi theta"));
    }
    
    @Test
    public void should_click_on_exit(){
        clickOn("#exit");
        verifyThat("#exit", LabeledMatchers.hasText("exit"));
    }
}
