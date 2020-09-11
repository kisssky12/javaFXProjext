package com.yedam.test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

	Connection conn = ConnectionDB.getDB();



	@Override // Member.java 모델클래스
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			
			System.out.println("dd");
		Statement stmt = conn.createStatement();
		String sql;

	//select
		sql="select * from member";
		ResultSet rs = stmt.executeQuery(sql);

	//	int count = 0;
		while(rs.next()) 
		{
			
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			int height = rs.getInt("height");
			int weight = rs.getInt("weight");

			System.out.println(name);

		
		}
		}
		catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());

		}
		
			
			
			
			
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

		// 종료버튼.
		btnAdd2.setOnAction(event -> Platform.exit());

		// 모델 클래스에서 이름을 더블클릭하면 MemberList로 이동
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

					// 차트 버튼.
					Button btnLineChart = (Button) parent.lookup("#btnLineChart");
					btnLineChart.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							Stage stage = new Stage(StageStyle.UTILITY);
							stage.initModality(Modality.WINDOW_MODAL);
							stage.initOwner(btnAdd1.getScene().getWindow());

							try {
								Parent parent = FXMLLoader.load(getClass().getResource("Chart.fxml"));
								Scene scene = new Scene(parent);
								stage.setScene(scene);
								stage.show();

								// 종료버튼.
								Button btnLineChart = (Button) parent.lookup("#btnClose");
								btnLineChart.setOnAction(a -> {
									stage.close();
								});

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

			btnFormAdd.setOnAction(event -> {

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