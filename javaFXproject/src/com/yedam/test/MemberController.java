package com.yedam.test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemberController implements Initializable {
	@FXML
	TableView<Member> tableView;
	@FXML
	Button btnAdd1, btnAdd2, btnLineChart;

	ObservableList<Member> list;

	@Override // Member.java 모델클래스
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Member, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("age"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("address"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("height"));

		tc = tableView.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("weight"));

		list = FXCollections.observableArrayList(); // 배열선언

		tableView.setItems(list);
		// 추가버튼.
		btnAdd1.setOnAction(event -> handleBtnAdd1Action());
//		btnAdd1.setOnAction(new EventHandler<ActionEvent>() {s
//			@Override
//			public void handle(ActionEvent event) {
//				handleBtnAdd1Action();
//			}
//		});
		// 종료버튼.
		btnAdd2.setOnAction(event -> Platform.exit());

//		 모델 클래스에서 이름을 더블클릭하면 MemberList로 이동

		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String name = tableView.getSelectionModel().getSelectedItem().getName();
				if (event.getClickCount() == 2) {
					handleDoubleClickAction(name);
				}
			}

			// DoubleClick 메소드.
			private void handleDoubleClickAction(String name) {
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(btnAdd1.getScene().getWindow());
			
				try {
					Parent parent = FXMLLoader.load(getClass().getResource("MemberList.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
					
					Button btnLineChart = (Button) parent.lookup("#btnLineChart");
					btnLineChart.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							Stage stage = new Stage(StageStyle.UTILITY);
							stage.initModality(Modality.WINDOW_MODAL);
//							stage.initOwner(btnLineChart.getScene().getWindow());
//							
							try {
								Parent parent = FXMLLoader.load(getClass().getResource("MemberList.fxml"));
								Scene scene = new Scene(parent);
								stage.setScene(scene);
								stage.show();
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
					});

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	} // end of Initializable.

	public void handleBtnAdd1Action() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd1.getScene().getWindow());
		stage.setTitle("회원입력");

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddList.fxml"));
			TextField txtName = (TextField) parent.lookup("#txtName");
			TextField txtAge = (TextField) parent.lookup("#txtAge");
			TextField txtAddress = (TextField) parent.lookup("#txtAddress");
			TextField txtHeight = (TextField) parent.lookup("#txtHeight");
			TextField txtWeight = (TextField) parent.lookup("#txtWeight");

			// 위의 TextiField와 동일
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");

			btnFormAdd.setOnAction(event -> {// UI(AddList.fxml)에서 저장 버튼을 눌렀을시 이벤트정의

				Member member = new Member(txtName.getText(), Integer.parseInt(txtAge.getText()), txtAddress.getText(),
						Integer.parseInt(txtHeight.getText()), Integer.parseInt(txtWeight.getText()));

				list.add(member);
				stage.close(); // 저장 완료되면 해당 stage창 닫기
			});
			btnFormCancel.setOnAction(event -> { // 취소버튼을 눌렀을시 텍스트필드에 입력한값들 초기화
				txtName.clear();
				txtAge.clear();
				txtAddress.clear();
				txtHeight.clear();
				txtWeight.clear();
			});

			Scene s = new Scene(parent);
			stage.setScene(s);
			stage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}