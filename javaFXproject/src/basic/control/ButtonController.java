package basic.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ButtonController implements Initializable {
	@FXML
	private CheckBox chk1;
	@FXML
	private CheckBox chk2;
	@FXML
	private ImageView checkImageView;
	@FXML
	private ToggleGroup group;
	@FXML
	private ImageView radioImageView;
	@FXML
	private Button btnExit;
	@FXML
	private RadioButton rad1, rad2, rad3;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				if (oldValue != null && newValue != null) {
					System.out.println(oldValue.getUserData()); // 이전값 출력
					System.out.println(newValue.getUserData()); // 현재값 출력
					radioImageView.setImage(new Image("basic/images/" + newValue.getUserData().toString() + ".png"));

				}
			}
		});
		rad1.setOnMouseClicked(new EventHandler<MouseEvent>() { // rad1 클릭

			@Override
			public void handle(MouseEvent me) {
				// TODO Auto-generated method stub
				System.out.println("rad1 clicked.");
			}
		});
		rad2.setOnMouseClicked((a) -> System.out.println("rad2 clicked.")); // rad2 클릭
		rad2.setSelected(true); // rad3 클릭
//
//		chk1.setOnAction((arg0) -> handleChkAction());   //람다식 (밑에꺼랑 같은 의미)
//		chk2.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				handleChkAction();
//			}
//
//		});
		btnExit.setOnAction((event) -> Platform.exit());
		
	} // end of intialize

	protected void handleChkAction() {
		String imgName = "";
		if(chk1.isSelected() && chk2.isSelected()) {
			imgName = "geek-glasses-hair.gif";
		}else if(chk1.isSelected()) {
			imgName = "geek-glasses.gif";
		}else if(chk2.isSelected()) {
			imgName = "geek-hair.gif";
		}else {
			imgName = "geek.gif";
		}
		checkImageView.setImage(new Image("/basic/images/" + imgName));
	}
}