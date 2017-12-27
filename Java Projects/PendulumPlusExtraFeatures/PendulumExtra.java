
/*
 * Matthew Vitt
 * This program animates a pendulum to swing in an arc
 */



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;



public class PendulumExtra extends Application {

    private double x = 50;
    private double y = 250;
    private int radius = 200;
    private double angle = 1.0;
    private double startAngle = 0.9;
    private double stopAngle = 2.1;
    private double step = 0.01;
    private double cX = 250;
    private double cY = 75;
    private int lowerCircleRad = 30;
    private int rate = 1;
    private boolean flag = false;



    private Circle topCircle;
    private Circle lowerCircle;
    private Line line;
    private BorderPane pane;
    private HBox rbHolder;
    private HBox btHolder;
    private HBox extraNodes;
    private Timeline animation;
    private Scene scene;
    private RadioButton rbRed;
    private RadioButton rbGold;
    private RadioButton rbBlack;
    private ToggleGroup tg;
    private Button btStart;
    private Button btStop;
    private MenuBar menuBar;
    private Menu color, rad, cSize;
    private MenuItem blue, green, purple, orange, increaseRad, decreaseRad, incCir, decCir;



    @Override
    public void start(Stage primaryStage) throws Exception {
        menuBar = new MenuBar();
        color = new Menu("More Colors");
        rad = new Menu("Radius Settings");
        cSize = new Menu("Lower Circle Settings");

        menuBar.getMenus().addAll(color, rad, cSize);

        blue = new MenuItem("Blue");
        green = new MenuItem("Green");
        purple = new MenuItem("Purple");
        orange = new MenuItem("Orange");

        color.getItems().addAll(blue, green, purple, orange);

        increaseRad = new MenuItem("Increase By 50");
        decreaseRad = new MenuItem("Decrease By 50");

        rad.getItems().addAll(increaseRad, decreaseRad);

        incCir = new MenuItem("Increase Circle Size By 25");
        decCir = new MenuItem("decrease Circle Size By 25");

        cSize.getItems().addAll(incCir, decCir);


        line = new Line(250, 25, 250, 350);


        pane = new BorderPane();
        extraNodes = new HBox(50);
        btHolder = new HBox(20);
        rbHolder = new HBox(20);
        rbBlack = new RadioButton("Black");
        rbGold = new RadioButton("Gold");
        rbRed = new RadioButton("Red");
        btStart = new Button("Start");
        btStop = new Button("Stop");
        btHolder.getChildren().addAll(btStart, btStop);
        tg = new ToggleGroup();
        rbBlack.setToggleGroup(tg);
        rbGold.setToggleGroup(tg);
        rbRed.setToggleGroup(tg);
        rbHolder.getChildren().addAll(rbBlack, rbGold, rbRed);
        extraNodes.getChildren().addAll(btHolder, rbHolder);
        topCircle = new Circle(cX, cY, 10);







        lowerCircle = new Circle(x, y, lowerCircleRad);



        line.startXProperty().bind(topCircle.centerXProperty());
        line.startYProperty().bind(topCircle.centerYProperty());
        line.endXProperty().bind(lowerCircle.centerXProperty());
        line.endYProperty().bind(lowerCircle.centerYProperty());




        pane.getChildren().addAll(topCircle, lowerCircle, line);
        pane.setTop(menuBar);
        pane.setBottom(extraNodes);


        scene = new Scene(pane, 500, 700);






        animation = new Timeline(new KeyFrame(Duration.millis(10), e -> moveCircle()));
        animation.setCycleCount(Timeline.INDEFINITE);

        incCir.setOnAction(e->{
            lowerCircleRad = lowerCircleRad + 25;
            lowerCircle.setRadius(lowerCircleRad);
        });

        decCir.setOnAction(e->{
            lowerCircleRad = lowerCircleRad - 25;
            lowerCircle.setRadius(lowerCircleRad);
            if (lowerCircleRad < 1) {
                lowerCircleRad = 2;
                lowerCircle.setRadius(lowerCircleRad);
            }
        });

        increaseRad.setOnAction(e-> radius = radius + 50);

        decreaseRad.setOnAction(e-> radius = radius - 50);

        btStart.setOnMouseClicked(e -> {
            animation.play();
            lowerCircle.requestFocus();
        });

        btStop.setOnMouseClicked(e -> {
            animation.stop();
            lowerCircle.requestFocus();
        });

        blue.setOnAction(e->{
            topCircle.setFill(Color.BLUE);
            line.setStroke(Color.BLUE);
            lowerCircle.setFill(Color.BLUE);
        });

        green.setOnAction(e->{
            topCircle.setFill(Color.GREEN);
            line.setStroke(Color.GREEN);
            lowerCircle.setFill(Color.GREEN);
        });

        purple.setOnAction(e->{
            topCircle.setFill(Color.PURPLE);
            line.setStroke(Color.PURPLE);
            lowerCircle.setFill(Color.PURPLE);
        });

        orange.setOnAction(e->{
            topCircle.setFill(Color.ORANGE);
            line.setStroke(Color.ORANGE);
            lowerCircle.setFill(Color.ORANGE);
        });

        rbBlack.setOnAction(e -> {

            if (rbBlack.isSelected()) {
                topCircle.setFill(Color.BLACK);
                line.setStroke(Color.BLACK);
                lowerCircle.setFill(Color.BLACK);
            }
            lowerCircle.requestFocus();
        });

        rbGold.setOnAction(e -> {
            if (rbGold.isSelected()) {
                topCircle.setFill(Color.GOLD);
                line.setStroke(Color.GOLD);
                lowerCircle.setFill(Color.GOLD);
            }
            lowerCircle.requestFocus();
        });

        rbRed.setOnAction(e -> {
            if (rbRed.isSelected()) {
                topCircle.setFill(Color.RED);
                line.setStroke(Color.RED);
                lowerCircle.setFill(Color.RED);
            }
            lowerCircle.requestFocus();
        });

        lowerCircle.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    rate++;
                    animation.setRate(rate);

                    break;
                case S:
                    animation.setRate(rate);
                    rate--;
                    if (rate < 0) {
                        rate = 0;

                    }

                default:
                    break;
            }
        });
        lowerCircle.requestFocus();


        animation.play();


        primaryStage.setTitle("Swinging Pendulum");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);

    }

    private void moveCircle() {

        for (double i = angle; i < stopAngle; i = i + step) {
            x = cX + Math.cos(angle) * radius;
            y = cY + Math.sin(angle) * radius;
            lowerCircle.setCenterX(x);
            lowerCircle.setCenterY(y);
            if (angle > 2.0) {

                flag = true;
            }
            if (angle < 1.0) {
                flag = false;
            }
        }



        if (flag) {
            angle -= step;
        }
        if (!flag) {
            angle += step;
        }





    }



}
