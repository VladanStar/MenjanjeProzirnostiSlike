package sample;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SliderDemo extends Application {

    //kreiranje objekata Slider
    Slider opacityLevel = new Slider(0, 1, 1);
    Slider sepiaTone = new Slider(0, 1, 1);
    Slider scaling = new Slider(0.5, 1, 1);
    //U okviru samog projekta mora da se nalazi slika jamaica.jpg
    Image image = new Image(getClass().getResourceAsStream("jamaica.jpg"));

    //kreiranje polja za slajdere
    Text opacityCaption = new Text("Prozirnost: ");
    Text sepiaCaption = new Text("Nijanse boja:");
    Text scalingCaption = new Text("Veličina:");

    //Kreiranje teksta za vrednost slajdera
    Text opacityValue = new Text(Double.toString(opacityLevel.getValue()));
    Text sepiaValue = new Text(Double.toString(sepiaTone.getValue()));
    Text scalingValue = new Text(Double.toString(scaling.getValue()));

    //objekat klase koji služi za postavljanje sepia efekta.
    SepiaTone sepiaEffect = new SepiaTone();

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Slider Demo");
        scene.setFill(Color.DARKGRAY);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(70);

        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(300);
        imgView.setFitWidth(600);
        imgView.setEffect(sepiaEffect);
        GridPane.setConstraints(imgView, 0, 0);
        GridPane.setColumnSpan(imgView, 3);
        grid.getChildren().add(imgView);
        scene.setRoot(grid);

        GridPane.setConstraints(opacityCaption, 0, 1);
        grid.getChildren().add(opacityCaption);

        //Listener za promenu vrednosti slidera opacity
        opacityLevel.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                imgView.setOpacity(new_val.doubleValue());
                opacityValue.setText(String.format("%.2f", new_val));
            }
        });

        GridPane.setConstraints(opacityLevel, 1, 1);
        grid.getChildren().add(opacityLevel);

        GridPane.setConstraints(opacityValue, 2, 1);
        grid.getChildren().add(opacityValue);

        GridPane.setConstraints(sepiaCaption, 0, 2);
        grid.getChildren().add(sepiaCaption);

        //Listener za promenu vrednosti slidera sepia
        sepiaTone.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                sepiaEffect.setLevel(new_val.doubleValue());
                sepiaValue.setText(String.format("%.2f", new_val));
            }
        });
        GridPane.setConstraints(sepiaTone, 1, 2);
        grid.getChildren().add(sepiaTone);

        GridPane.setConstraints(sepiaValue, 2, 2);
        grid.getChildren().add(sepiaValue);

        GridPane.setConstraints(scalingCaption, 0, 3);
        grid.getChildren().add(scalingCaption);

        //Listener za promenu vrednosti slidera scaling-a
        scaling.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                imgView.setScaleX(new_val.doubleValue());
                imgView.setScaleY(new_val.doubleValue());
                scalingValue.setText(String.format("%.2f", new_val));
            }
        });
        GridPane.setConstraints(scaling, 1, 3);
        grid.getChildren().add(scaling);

        GridPane.setConstraints(scalingValue, 2, 3);
        grid.getChildren().add(scalingValue);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}