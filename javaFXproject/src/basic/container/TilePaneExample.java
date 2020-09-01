package basic.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane root = new TilePane();

//		ImageView iv1 = new ImageView();
//		iv1.setImage(new Image("/basic/images/fruit1.jpg"));
//		ImageView iv2 = new ImageView();
//		iv2.setImage(new Image("/basic/images/fruit2.jpg"));
//		ImageView iv3 = new ImageView();
//		iv3.setImage(new Image("/basic/images/fruit3.jpg"));
//		ImageView iv4 = new ImageView();
//		iv4.setImage(new Image("/basic/images/fruit4.jpg"));
//		ImageView iv5 = new ImageView();
//		iv5.setImage(new Image("/basic/images/fruit5.jpg"));
		
		ImageView[] ary = new ImageView[5];
		for (int i = 0; i < 5; i++) {
			ImageView iv = new ImageView();
			iv.setImage(new Image("/basic/images/fruit" + (i + 1)));
			ary[i] = iv;
			root.getChildren().add(ary[i]);
		}
		
//		root.getChildren().add(iv1);
//		root.getChildren().add(iv2);
//		root.getChildren().add(iv3);
//		root.getChildren().add(iv4);
//		root.getChildren().add(iv5);

		Scene scene = new Scene(root); // 스테이지(신(컨테이너))
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Appmain");

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
