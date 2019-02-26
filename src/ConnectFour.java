import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class ConnectFour extends Application {
    private int moveNumber = 0;

    public void start(Stage primaryStage) {
        Pane myPane = new Pane();
        myPane.setPrefWidth(390);
        myPane.setPrefHeight(350);

        //make this better by putting the x,y and radius in terms of the size of the pane
        for (int j = 0; j < 6 ; j++) {
            for (int i = 0; i < 7; i++) {
                Circle circ = new Circle(30 + 55 * i, 30 + 55 * j, 25, Color.WHITE);
                circ.setStroke(Color.BLACK);
                myPane.getChildren().add(circ);

                circ.setOnMouseClicked(e ->{
                if (moveNumber % 2 == 0) circ.setFill(Color.BLUE);
                else circ.setFill(Color.RED);
                moveNumber++;
            });

            }
        }



        Scene scene = new Scene(myPane);
        primaryStage.setTitle("Connect Four"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }



}
