// Mingyu Liu
// July 2019

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		// Interface when the game is started. 
		Text text = new Text(52, 180, "Gomoku");
		text.setFont(Font.font("Zapfino", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 48));
		text.setFill(Color.WHITE);

		Rectangle rec1 = new Rectangle(90, 360, 160, 40);  // First button, "HOW TO PLAY"
		rec1.setArcWidth(30); 
		rec1.setArcHeight(30); 
		rec1.setFill(Color.WHITE);
		Text ins1 = new Text(106, 387, "HOW TO PLAY");
		ins1.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 18));
		ins1.setFill(Color.BLACK);

		Rectangle rec2 = new Rectangle(90, 410, 160, 40);  // Second button, "NEW GAME"
		rec2.setArcWidth(30); 
		rec2.setArcHeight(30); 
		rec2.setFill(Color.WHITE);
		Text ins2 = new Text(120, 437, "NEW GAME");
		ins2.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 18));
		ins2.setFill(Color.BLACK);

		Group rootOpen = new Group(text, rec1, rec2, ins1, ins2);

		Scene scene = new Scene(rootOpen ,600, 600, Color.LIGHTBLUE);
		Image bg = null;
		try {
			bg = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/Background.jpg"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scene.setFill(new ImagePattern(bg));
		primaryStage.setTitle("Gomoku");
		primaryStage.setScene(scene);
		primaryStage.show();

		// When the first button (HOW TO PLAY) is clicked, a new
		// window showing the instructions will come out.
		EventHandler<MouseEvent> click1 = new EventHandler<MouseEvent>() { 

			public void handle(MouseEvent e) { 

				Text howToPlay = new Text(300, 200, ""
						+ "     Gomoku is an abstract strategy board game and is also "
						+ "\ncalled \"Five in a Row\". The goal of the game is to order "
						+ "\nunbroken row of five signs horizontally, vertically, or "
						+ "\ndiagonally. Two players take turns to play by clicking with "
						+ "\nmouse on any empty field of the board. Whoever gets five "
						+ "\ntiles in a row first wins the game.");

				howToPlay.setFont(Font.font("American Typewritter", FontWeight.LIGHT, FontPosture.REGULAR, 18));
				howToPlay.setFill(Color.MINTCREAM);

				StackPane instructions = new StackPane();
				instructions.getChildren().add(howToPlay);

				Scene sceneI = new Scene(instructions, 600, 300);
				Image bg = null;
				try {
					bg = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/Ins Back.jpg"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sceneI.setFill(new ImagePattern(bg));

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setTitle("Instructions");
				newWindow.setScene(sceneI);
				newWindow.initModality(Modality.WINDOW_MODAL);
				newWindow.initOwner(primaryStage);

				newWindow.show();
			} 
		};  
		ins1.addEventFilter(MouseEvent.MOUSE_CLICKED, click1); 

		// When "NEW GAME" is clicked, a new window will come out,
		// allowing the user to play the game. 
		EventHandler<MouseEvent> click2 = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				
				GamePlay newGame = new GamePlay();
				newGame.set();

			} 
		};  
		ins2.addEventFilter(MouseEvent.MOUSE_CLICKED, click2); 

	}

	public static void main(String[] args) {
		launch(args);
	}

}
