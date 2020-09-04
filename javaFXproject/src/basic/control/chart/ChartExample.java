package basic.control.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChartExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox a = FXMLLoader.load(getClass().getResource("ChartControl.fxml"));

		Scene scene = new Scene(a);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}