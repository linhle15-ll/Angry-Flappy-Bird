package angryflappybird;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Defines {
    
	// dimension of the GUI application
    final int APP_HEIGHT = 600;
    final int APP_WIDTH = 600;
    final int SCENE_HEIGHT = 570;
    final int SCENE_WIDTH = 400;

    // coefficients related to the blob
    final int BLOB_WIDTH = 90;
    final int BLOB_HEIGHT = 90;
    final int BLOB_POS_X = 70;
    final int BLOB_POS_Y = 200;
    final int BLOB_DROP_TIME = 300000000;  	// the elapsed time threshold before the blob starts dropping
    final int BLOB_DROP_VEL = 300;    		// the blob drop velocity
    final int BLOB_FLY_VEL = -40;
    final int BLOB_IMG_LEN = 4;
    final int BLOB_IMG_PERIOD = 5;
    
    // coefficients related to the floors
    final int FLOOR_WIDTH = 400;
    final int FLOOR_HEIGHT = 100;
    final int FLOOR_COUNT = 2;
    
    // coefficients related to the pipes - Linh Ngoc Le
    final int PIPE_WIDTH = 70;
    final int UPPER_PIPE_HEIGHT = 210; //*********************************
    final int LOWER_PIPE_HEIGHT = 210;
    final int PIPE_HEIGHT = 210; 
    final int PIPE_MIN_HEIGHT = 100;
    final int PIPES_GAP = BLOB_HEIGHT + 6;
    final int PIPE_MAX_HEIGHT = PIPE_HEIGHT - PIPES_GAP - PIPE_MIN_HEIGHT;
    final int PIPE_COUNT = 2;
    
    // velocity at which pipes move - Linh Ngoc Le
    final int PIPE_VEL_EASY = 5;
    final int PIPE_VEL_MEDIUM = 10;
    final int PIPE_VEL_HARD = 20;
    
                               
    // coefficients related to raibow candy - Linh Ngoc Le
    final int RAINBOW_CANDY_WIDTH = 70;
    final int RAINBOW_CANDY_HEIGHT = 70;
    
    // coefficients related to normal candy - Linh Ngoc Le
    final int NORMAL_CANDY_WIDTH = 70;
    final int NORMAL_CANDY_HEIGHT = 70;
    
    // coefficients related to dragon - Linh Ngoc Le
    final int DRAGON_HEIGHT = 70;
    final int DRAGON_WIDTH = 70;
    
    // coefficients related to time
    final int SCENE_SHIFT_TIME = 5;
    final double SCENE_SHIFT_INCR = -0.4;
    final double NANOSEC_TO_SEC = 1.0 / 1000000000.0;
    final double TRANSITION_TIME = 0.1;
    final int TRANSITION_CYCLE = 2;
    
    
    // coefficients related to media display
    final String STAGE_TITLE = "Angry Flappy Bird - George Version";
	private final String IMAGE_DIR = "../resources/images/";
    final String[] IMAGE_FILES = {"day_background", "night_background", "blob0", "blob1", "blob2", "blob3", "floor", "lower_pipe", "upper_pipe", "rainbow_candy", "normal_candy", "dragon"}; // More sprite here 

    final HashMap<String, ImageView> IMVIEW = new HashMap<String, ImageView>();
    final HashMap<String, Image> IMAGE = new HashMap<String, Image>();
    
    //nodes on the scene graph - start game, level selection, instruction box
    Button startButton;
    ChoiceBox<String> levelSelection;
    VBox instruction;
    
    // constructor
	Defines() {
		
		// initialize images
		for(int i=0; i<IMAGE_FILES.length; i++) {
			Image img;
			if (i == 6) {
				img = new Image(pathImage(IMAGE_FILES[i]), FLOOR_WIDTH, FLOOR_HEIGHT, false, false);
			}
			else if (i == 2 || i == 3 || i == 4 || i == 5){
				img = new Image(pathImage(IMAGE_FILES[i]), BLOB_WIDTH, BLOB_HEIGHT, false, false);
			}
			else if (i == 0 | i == 1) {
				img = new Image(pathImage(IMAGE_FILES[i]), SCENE_WIDTH, SCENE_HEIGHT, false, false);
			}
			else if (i == 7) {
			    img = new Image(pathImage(IMAGE_FILES[i]), PIPE_WIDTH, LOWER_PIPE_HEIGHT, false, false);
			}
			else if (i == 8) {
			    img = new Image(pathImage(IMAGE_FILES[i]), PIPE_WIDTH, UPPER_PIPE_HEIGHT, false, false);
            }
			else if (i == 9) {
			    img = new Image(pathImage(IMAGE_FILES[i]), RAINBOW_CANDY_WIDTH, RAINBOW_CANDY_HEIGHT, false, false);
			}
			else if (i == 10) {
			    img = new Image(pathImage(IMAGE_FILES[i]), NORMAL_CANDY_WIDTH, NORMAL_CANDY_HEIGHT, false, false);
			}
			else {
			    img = new Image(pathImage(IMAGE_FILES[i], DRAGON_WIDTH, DRAGON_HEIGHT, false, false));
			}
    		IMAGE.put(IMAGE_FILES[i],img);
    	}
		
		// initialize image views
		for(int i = 0; i < IMAGE_FILES.length; i++) {
    		ImageView imgView = new ImageView(IMAGE.get(IMAGE_FILES[i]));
    		IMVIEW.put(IMAGE_FILES[i],imgView);
    	}
		
		// initialize scene nodes - Linh Ngoc Le
		startButton = new Button("Start game!");
		levelSelection = new ChoiceBox<String>(FXCollections.observableArrayList(
		        "Easy", "Medium", "Hard")
		);
		
        // Instruction - Linh Ngoc Le
		instruction = new VBox(!0);
//		row 1: Bonus point (normal candy)
		VBox row1 = new VBox(10);
		Label labelBonusPoints = new Label("Bonus points");
		row1.getChildren().addAll(IMVIEW.get(IMAGE_FILES[9]), labelBonusPoints);
		
//		row 2: Snooze (rainbow candy)
		VBox row2 = new VBox(10);
        Label labelSnooze = new Label("Let you snooze");
        row2.getChildren().addAll(IMVIEW.get(IMAGE_FILES[8]), labelSnooze);
		
//      row3: Avoid dragons
        VBox row3 = new VBox(10);
        Label labelDragon = new Label("Avoid dragons");
        row3.getChildren().addAll(IMVIEW.get(IMAGE_FILES[10]),labelDragon);
		
        instruction.getChildren().addAll(row1, row2, row3);
	}
	
	/**
	 * @param filepath
	 * @return fullpath
	 */
	public String pathImage(String filepath) {
    	String fullpath = getClass().getResource(IMAGE_DIR+filepath+".png").toExternalForm();
    	return fullpath;
    }
	
	/**
	 * @param filepath
	 * @param width
	 * @param height
	 * @return resized image
	 */
	public Image resizeImage(String filepath, int width, int height) {
    	IMAGE.put(filepath, new Image(pathImage(filepath), width, height, false, false));
    	return IMAGE.get(filepath);
    }
	
//	COEFFICIENTS FOR DIFFERENT LEVELS 
}