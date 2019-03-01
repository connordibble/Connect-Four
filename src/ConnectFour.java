import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ConnectFour extends Application {
    private int moveNumber = 0;//keeps track of whose turn it is
    private ArrayList<ArrayList<Circle>> circArray = new ArrayList<>(); //use this to change the grid

    public void start(Stage primaryStage) {
        GridPane myPane = new GridPane(); //create a grid pane for the game
        myPane.setPrefWidth(400);
        myPane.setPrefHeight(350);
        myPane.setPadding(new Insets(20,20,20,20));
        myPane.setVgap(10);
        myPane.setHgap(10);


        //create the connect four grid
        for (int j = 0; j < 6 ; j++) {
            ArrayList<Circle> circRow = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                Circle circ = new Circle(30, Color.WHITE);
                circ.setStroke(Color.BLACK);
                myPane.add(circ,i,j);
                circ.setOnMouseClicked(e -> circleLogic(circ));
                circRow.add(circ); //Create rows for the circle array list
            }
            this.circArray.add(circRow); //add circle objects to the Array List
        }
        System.out.println(myPane);

        Scene scene = new Scene(myPane);
        primaryStage.setTitle("Connect Four"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    private void circleLogic (Circle circ){
        //determine which color to make the circle upon click
        if (moveNumber % 2 == 0) circ.setFill(Color.BLUE);
        else circ.setFill(Color.RED);
        moveNumber++;

        //check all cases for wins using methods below
        checkVert();
        checkHorizontal();
        checkDiagonalRight();
        checkDiagonalLeft();
    }

    private void checkVert(){
        int winNumberVertRed = 0;
        int winNumberVertBlue = 0;

        //check all vertical possibilities for a winner
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++){
                if (this.circArray.get(j).get(i).getFill() == Color.BLUE ) winNumberVertBlue++;
                else winNumberVertBlue = 0;

                if (this.circArray.get(j).get(i).getFill() == Color.RED ) winNumberVertRed++;
                else winNumberVertRed = 0;

                //TDL Break it off when a winner is found
                if (winNumberVertRed == 4 || winNumberVertBlue == 4){

                        animation(this.circArray.get(j).get(i));
                        animation(this.circArray.get(j - 1).get(i));
                        animation(this.circArray.get(j - 2).get(i));
                        animation(this.circArray.get(j - 3).get(i));
                        winNumberVertBlue = 0;
                        winNumberVertRed = 0;
                    }
                }
            }
        }


    private void checkHorizontal(){
        int winNumberHorizontalBlue = 0;
        int winNumberHorizontalRed = 0;

        //check all horizontal possibilities for a winner
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++){
                if (this.circArray.get(i).get(j).getFill() == Color.BLUE ) winNumberHorizontalBlue++;
                else winNumberHorizontalBlue = 0;

                if (this.circArray.get(i).get(j).getFill() == Color.RED ) winNumberHorizontalRed++;
                else winNumberHorizontalRed = 0;


                if (winNumberHorizontalBlue == 4 || winNumberHorizontalRed == 4){

                        animation(this.circArray.get(i).get(j));
                        animation(this.circArray.get(i).get(j - 1));
                        animation(this.circArray.get(i).get(j - 2));
                        animation(this.circArray.get(i).get(j - 3));
                        winNumberHorizontalRed = 0;
                        winNumberHorizontalBlue = 0;
                    }
                }
            }
        }


    private void checkDiagonalRight(){
        int winNumberDiagRightRed = 0;
        int winNumberDiagRightBlue = 0;

        //check diagonal possibilities going up and down from right
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (this.circArray.get(i + k).get(j + k).getFill() == Color.BLUE) winNumberDiagRightBlue++;

                    if (this.circArray.get(i + k).get(j + k).getFill() == Color.RED) winNumberDiagRightRed++;

                    if (winNumberDiagRightBlue == 4 || winNumberDiagRightRed == 4){
                        animation(this.circArray.get(i + k).get(j + k));
                        animation(this.circArray.get(i + k - 1).get(j + k - 1));
                        animation(this.circArray.get(i + k - 2).get(j + k - 2));
                        animation(this.circArray.get(i + k - 3).get(j + k - 3));
                        winNumberDiagRightRed = 0;
                        winNumberDiagRightBlue = 0;
                    }
                }
            }
        }
    }


    private void checkDiagonalLeft(){
        int winNumberDiagLeftRed = 0;
        int winNumberDiagLeftBlue = 0;

        //check diagonal possibilities going up and down from left
        for (int i = 0; i < 3 ; i++) {
            for (int j = 4; j < 7; j++) {
                for (int k = 0; k < 4; k++) {
                    if (this.circArray.get(i + k).get(j - k).getFill() == Color.BLUE) winNumberDiagLeftBlue++;

                    if (this.circArray.get(i + k).get(j - k).getFill() == Color.RED) winNumberDiagLeftRed++;

                    if (winNumberDiagLeftBlue == 4 || winNumberDiagLeftRed == 4){
                        animation(this.circArray.get(i + k).get(j - k));
                        animation(this.circArray.get(i + k - 1).get(j - k + 1));
                        animation(this.circArray.get(i + k - 2).get(j - k + 2));
                        animation(this.circArray.get(i + k - 3).get(j - k + 3));
                        winNumberDiagLeftRed = 0;
                        winNumberDiagLeftBlue = 0;
                    }
                }
            }
        }
    }

    //make animation here for when winning move is made
    private void animation(Circle circ){
        EventHandler<ActionEvent> eventHandler = e -> {
            circ.setFill(Color.color(Math.random(),Math.random(),Math.random())); // Set a new clock time
        };

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(500),eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }

}






