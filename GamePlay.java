// Mingyu Liu
// July 2019

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePlay {

	Group root = new Group();
	protected Stage primaryStage;
	Text curPlayer;

	private static int[][] board = new int[15][15];
	private Boolean isUpdated = false;
	private int current = 1;  // 1 represents BLACK and 2 represents WHITE
	private Boolean bWins = false;
	private Boolean wWins = false;

	public void set() {

		// Initialize the game board to be empty.
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				board[i][j] = 0;
			}
		}

		Scene sceneP = new Scene(root, 910, 660);
		Image bg = null;
		try {
			bg = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/BG.jpg"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sceneP.setFill(new ImagePattern(bg));

		Stage newWindow = new Stage();
		newWindow.setScene(sceneP);
		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.initOwner(primaryStage);

		newWindow.show();
		for(int col=0; col<15; col++) {
			Line node = new Line(300+col*40, 50, 300+col*40, 610);
			root.getChildren().add(node);
		}
		for(int row=0; row<15; row++) {
			Line node = new Line(300, 50+row*40, 860, 50+row*40);
			root.getChildren().add(node);
		}

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {

				if(!isUpdated) {

					// Update the game board. 
					root.getChildren().remove(curPlayer);
					curPlayer = new Text("PLAYER "+current);
					curPlayer.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 18));
					curPlayer.setFill(Color.BLACK);
					curPlayer.setLayoutX(170);
					curPlayer.setLayoutY(285);
					root.getChildren().add(curPlayer);

					Image y = null;
					try {
						if(current==1)
							y = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/Black.jpg"));
						else
							y = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/White.jpg"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageView y1 = new ImageView(y);
					y1.setX(192); 
					y1.setY(300);
					y1.setFitHeight(35); 
					y1.setFitWidth(35);
					root.getChildren().add(y1);

					update();
				}

				// When the black player wins the game:
				if(bWins) {

					this.stop();
					Stage newDead = new Stage();
					newDead.initModality(Modality.APPLICATION_MODAL);
					newDead.initOwner(primaryStage);
					Text deadIns = new Text("                Player 1 Wins!");
					deadIns.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 18));
					deadIns.setFill(Color.BLACK);
					deadIns.setLayoutY(42);

					Image x = null;
					try {
						x = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/Black.jpg"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageView x1 = new ImageView(x);
					x1.setX(52); 
					x1.setY(20);
					x1.setFitHeight(35); 
					x1.setFitWidth(35);

					Button button = new Button("OK!");
					button.setLayoutX(130);
					button.setLayoutY(72);

					EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() { 
						@Override 
						public void handle(MouseEvent e) { 
							newDead.close();
							newWindow.close();
						}
					};
					button.addEventFilter(MouseEvent.MOUSE_CLICKED, click); 

					Pane dialogBox = new Pane();
					dialogBox.getChildren().addAll(button, deadIns, x1); 
					dialogBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

					Scene dialogScene = new Scene(dialogBox, 300, 120, Color.WHITE);
					newDead.setScene(dialogScene);
					newDead.show();

				}
				
				// When the white player wins the game:
				if(wWins) {

					this.stop();
					Stage newDead = new Stage();
					newDead.initModality(Modality.APPLICATION_MODAL);
					newDead.initOwner(primaryStage);
					Text deadIns = new Text("                Player 2 Wins!");
					deadIns.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 18));
					deadIns.setFill(Color.BLACK);
					deadIns.setLayoutY(42);

					Image x = null;
					try {
						x = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/White.jpg"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageView x1 = new ImageView(x);
					x1.setX(52); 
					x1.setY(20);
					x1.setFitHeight(35); 
					x1.setFitWidth(35);

					Button button = new Button("OK!");
					button.setLayoutX(130);
					button.setLayoutY(72);

					EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() { 
						@Override 
						public void handle(MouseEvent e) { 
							newDead.close();
							newWindow.close();
						}
					};
					button.addEventFilter(MouseEvent.MOUSE_CLICKED, click); 

					Pane dialogBox = new Pane();
					dialogBox.getChildren().addAll(button, deadIns, x1); 
					dialogBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

					Scene dialogScene = new Scene(dialogBox, 300, 120, Color.WHITE);
					newDead.setScene(dialogScene);
					newDead.show();

				}

			}
		};
		timer.start();

		// When the mouse clicks on the game board, the location of the click
		// will be collected and the board would be updated accordingly. 
		sceneP.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double xPos = me.getX();
				double yPos = me.getY();

				int xInd = (int)(xPos-280)/40;
				int yInd = (int)(yPos-30)/40;

				if(xInd>=0 && xInd<=14 && yInd>=0 && yInd<=14 && board[yInd][xInd]==0) {

					if(current==1) {
						board[yInd][xInd] = 1;
						current  =2;
						bWins = bWins(yInd, xInd);
					}
					else {
						board[yInd][xInd] = 2;
						current = 1;
						wWins = wWins(yInd, xInd);
					}
					isUpdated = false;

				}

			}
		});
	}

	// Update by displaying the new board. 
	public void update() {
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				Image p = null;
				try {
					if(board[i][j]==1)
						p = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/Black.jpg"));
					else if(board[i][j]==2)
						p = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/Gomoku/src/application/White.jpg"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(board[i][j]==1 || board[i][j]==2) {
					ImageView p1 = new ImageView(p);
					p1.setX(j*40+280); 
					p1.setY(i*40+30);
					p1.setFitHeight(40); 
					p1.setFitWidth(40);
					root.getChildren().add(p1);
					p = null;
				}
			}
		}
		isUpdated = true;
	}

	// bWins and wWins test if any of the players win the game. 
	public Boolean bWins(int xPos, int yPos) {
		int num = Math.max(Math.max(numVer(xPos, yPos, 1), numHor(xPos, yPos, 1)), Math.max(leftDia(xPos, yPos, 1), rigtDia(xPos, yPos, 1)));

		if(num>=5)
			return true;
		return false;
	}

	public Boolean wWins(int xPos, int yPos) {
		int num = Math.max(Math.max(numVer(xPos, yPos, 2), numHor(xPos, yPos, 2)), Math.max(leftDia(xPos, yPos, 2), rigtDia(xPos, yPos, 2)));

		if(num>=5)
			return true;
		return false;
	}

	// The method finds the number of tiles of the same color
	// connected adjacently in the vertical direction. (|)
	public int numVer(int xPos, int yPos, int a){

		int numSame = 1;
		int start[] = {xPos, yPos};
		int aX, aY;
		Stack stack = new Stack(start);

		Boolean ifChecked[][] = new Boolean[15][15];

		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				ifChecked[i][j] = false;
			}
		}

		ifChecked[xPos][yPos] = true;

		while(stack.size()>0) {
			start = (int[]) stack.pop();
			aX = start[0];
			aY = start[1];

			if(aX>0) {
				if(board[aX-1][aY]==a && ifChecked[aX-1][aY]==false) { // The one on the top
					numSame++;
					int temp[] = {aX-1, aY};
					stack.push(temp);
					ifChecked[aX-1][aY] = true;
				}
			}
			if(aX<board.length-1) {
				if(board[aX+1][aY]==a && ifChecked[aX+1][aY]==false) { // The one on the bottom
					numSame++;
					int temp[] = {aX+1, aY};
					stack.push(temp);
					ifChecked[aX+1][aY] = true;
				}
			}
		}
		start = null;
		stack = null;
		return numSame;
	}

	// The method finds the number of tiles of the same color
	// connected adjacently in the horizontal direction. (-)
	public int numHor(int xPos, int yPos, int a){

		int numSame = 1;
		int start[] = {xPos, yPos};
		int aX, aY;
		Stack stack = new Stack(start);

		Boolean ifChecked[][] = new Boolean[15][15];

		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				ifChecked[i][j] = false;
			}
		}

		ifChecked[xPos][yPos] = true;

		while(stack.size()>0) {
			start = (int[]) stack.pop();
			aX = start[0];
			aY = start[1];

			if(aY>0) {
				if(board[aX][aY-1]==a && ifChecked[aX][aY-1]==false) { // The one on the left
					numSame++;
					int temp[] = {aX, aY-1};
					stack.push(temp);
					ifChecked[aX][aY-1] = true;
				}
			}
			if(aY<board.length-1) {
				if(board[aX][aY+1]==a && ifChecked[aX][aY+1]==false) { // The one on the right
					numSame++;
					int temp[] = {aX, aY+1};
					stack.push(temp);
					ifChecked[aX][aY+1] = true;
				}
			}
		}
		start = null;
		stack = null;
		return numSame;
	}

	// The method finds the number of tiles of the same color
	// connected adjacently in the / direction. 
	public int leftDia(int xPos, int yPos, int a){

		int numSame = 1;
		int start[] = {xPos, yPos};
		int aX, aY;
		Stack stack = new Stack(start);

		Boolean ifChecked[][] = new Boolean[15][15];

		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				ifChecked[i][j] = false;
			}
		}

		ifChecked[xPos][yPos] = true;

		while(stack.size()>0) {
			start = (int[]) stack.pop();
			aX = start[0];
			aY = start[1];

			if(aX>0 && aY>0) {
				if(board[aX-1][aY-1]==a && ifChecked[aX-1][aY-1]==false) { // The one on the upper left corner
					numSame++;
					int temp[] = {aX-1, aY-1};
					stack.push(temp);
					ifChecked[aX-1][aY-1] = true;
				}
			}
			if(aX<board.length-1 && aY<board.length-1) {
				if(board[aX+1][aY+1]==a && ifChecked[aX+1][aY+1]==false) { // The one on the bottom right corner
					numSame++;
					int temp[] = {aX+1, aY+1};
					stack.push(temp);
					ifChecked[aX+1][aY+1] = true;
				}
			}
		}
		start = null;
		stack = null;
		return numSame;
	}

	// The method finds the number of tiles of the same color
	// connected adjacently in the \ direction. 
	public int rigtDia(int xPos, int yPos, int a){

		int numSame = 1;
		int start[] = {xPos, yPos};
		int aX, aY;
		Stack stack = new Stack(start);

		Boolean ifChecked[][] = new Boolean[15][15];

		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				ifChecked[i][j] = false;
			}
		}

		ifChecked[xPos][yPos] = true;

		while(stack.size()>0) {
			start = (int[]) stack.pop();
			aX = start[0];
			aY = start[1];

			if(aX>0 && aY<board.length-1) {
				if(board[aX-1][aY+1]==a && ifChecked[aX-1][aY+1]==false) { // The one on the upper right corner
					numSame++;
					int temp[] = {aX-1, aY+1};
					stack.push(temp);
					ifChecked[aX-1][aY+1] = true;
				}
			}
			if(aX<board.length-1 && aY>0) {
				if(board[aX+1][aY-1]==a && ifChecked[aX+1][aY-1]==false) { // The one on the bottom left corner
					numSame++;
					int temp[] = {aX+1, aY-1};
					stack.push(temp);
					ifChecked[aX+1][aY-1] = true;
				}
			}
		}
		start = null;
		stack = null;
		return numSame;
	}
}
