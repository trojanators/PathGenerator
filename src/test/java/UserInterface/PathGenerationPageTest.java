package UserInterface;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.junit.Test;
import org.mockito.internal.matchers.Find;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
     * tests the new waypoint creation 
     * only test
     */
    @BeforeAll
    public void should_populate_path_for_build_path_Click(){
        clickOn("#waypoint_y_input").type(KeyCode.DIGIT7);
        clickOn("#waypoint_x_input").type(KeyCode.DIGIT6);
        clickOn("#waypoint_theta_input").type(KeyCode.DIGIT5);
        clickOn("#waypoint_acc_input").type(KeyCode.DIGIT4);
        clickOn("#waypoint_jerk_input").type(KeyCode.DIGIT3);
        clickOn("#waypoint_velocity_input").type(KeyCode.DIGIT2);
        clickOn("#waypoint_dt_input").type(KeyCode.DIGIT1);
        clickOn("#path_name").type(KeyCode.E);
        
        WaitForAsyncUtils.waitForFxEvents();

        clickOn("#new_waypoint");
        verifyThat("#new_waypoint", LabeledMatchers.hasText("add Waypoint"));
    }
   
    /**
     * This is the Text Box Test Section 
     * This only Test's Text Box Inputs 
     */

    @Test
    @Order (19)
    public void should_enter_test_Path_File_name(){
        TextField path_name_input = new TextField("#pathfile_path");

        clickOn("#pathfile_path").type(KeyCode.DIGIT6, 2);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("66",
        path_name_input.getText(), "#pathfile_path");
     
    }
    

    @Test
    @Order (18)
    public void should_enter_test_Robot_width_name(){
        TextField path_name_input = new TextField("#robot_wheelbase");

        clickOn("#robot_wheelbase").type(KeyCode.DIGIT5, 2);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("55",
        path_name_input.getText(), "#robot_wheelbase");
     
    }
    

    @Test
    @Order (17)
    public void should_enter_test_path_name(){
        TextField path_name_input = new TextField("#path_name");

        clickOn("#path_name").type(KeyCode.DIGIT1, 2);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("11",
        path_name_input.getText(), "#path_name");
     
    }
    

    @Test
    @Order(16)
    public void should_enter_text_waypoint_y(){
        TextField waypoint_y_input = new TextField("#waypoint_y_input");

        clickOn("#waypoint_y_input").type(KeyCode.DIGIT7);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("7",
        waypoint_y_input.getText(), "#waypoint_y_input");
     
    }

    @Test
    @Order(15)
    public void should_enter_text_waypoint_x(){
        TextField waypoint_x_input = new TextField("#waypoint_x_input");

        clickOn("#waypoint_x_input").type(KeyCode.DIGIT6);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("6",
        waypoint_x_input.getText(), "#waypoint_x_input");
    }

    @Test
    @Order(14)
    public void should_enter_text_waypoint_theta(){
        TextField waypoint_theta_input = new TextField("#waypoint_theta_input");
        clickOn("#waypoint_theta_input").type(KeyCode.DIGIT5);;
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("5",
        waypoint_theta_input.getText(), "#waypoint_theta_input");
    }

    @Test
    @Order(13)
    public void should_enter_text_waypoint_acc(){
        TextField waypoint_acc_input = new TextField("#waypoint_acc_input");
        clickOn("#waypoint_acc_input").type(KeyCode.DIGIT4);;
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("4",
        waypoint_acc_input.getText(), "#waypoint_acc_input");
     
    }

    @Test
    @Order(12)
    public void should_enter_text_waypoint_jerk(){
        TextField waypoint_jerk_input = new TextField("#waypoint_jerk_input");
        clickOn("#waypoint_jerk_input").type(KeyCode.DIGIT3);;
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("3",
        waypoint_jerk_input.getText(), "#waypoint_jerk_input");
    }

    @Test
    @Order(11)
    public void should_enter_text_waypoint_velocity(){
        TextField waypoint_velocity_input = new TextField("#waypoint_velocity_input");
        clickOn("#waypoint_velocity_input").type(KeyCode.DIGIT2);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("2",
        waypoint_velocity_input.getText(), "#waypoint_velocity_input");
    }

    @Test
    @Order(10)
    public void should_enter_text_waypoint_dt(){
        TextField waypoint_dt_input = new TextField("#waypoint_dt_input");
        clickOn("#waypoint_dt_input").type(KeyCode.DIGIT1);
        WaitForAsyncUtils.waitForFxEvents();

        assertEquals("1",
        waypoint_dt_input.getText(), "#waypoint_dt_input");
    }


    /**
     * This section is for testing the Buttons / Check Boxes 
     * this only Tests the clicking of the buttons and checks if the names are the same
     */

    @Test
    @Order(8)
    public void should_click_on_remove_waypoint() {
        // when:
        clickOn("#remove_path");
        verifyThat("#remove_path", LabeledMatchers.hasText("remove WayPoint"));
    }

    @Test
    @Order(7)
    public void should_click_on_generate_path(){
        clickOn("#generate_path");
        verifyThat("#generate_path",LabeledMatchers.hasText("Test (Fill random)"));
    }

    @Test
    @Order(6)
    public void should_click_on_invert_y(){
        clickOn("#invert_y");
        verifyThat("#invert_y", LabeledMatchers.hasText("Invert Y"));

    }

    @Test
    @Order(5)
    public void should_click_on_enable_pi(){
        clickOn("#pi_enable");
        verifyThat("#pi_enable", LabeledMatchers.hasText("enable pi theta"));
    }

    @Test
    @Order(4)
    public void should_click_on_enable_neg_pi(){
        clickOn("#neg_pi");
        verifyThat("#neg_pi", LabeledMatchers.hasText("enable neg pi theta"));
    }
    
   @AfterAll
    public void should_click_on_exit(){
        verifyThat("#exit", LabeledMatchers.hasText("exit"));
    }
}
