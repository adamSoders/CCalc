package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.CCalcController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CordinateCalculator;
import model.Measurment;
import model.Measurments;

public class CCalcWindow extends Application implements Initializable{

	@FXML
	private TableColumn<Measurment, Integer> direction;

	@FXML
	private TableColumn<Measurment, Float> distance;

	@FXML
	private TableColumn<Measurment, Integer> id;

	@FXML
	private TableView<Measurment> table;
	
	@FXML
	private TextField txtDirection;
	
	@FXML
	private TextField txtDistance;
	
	@FXML
	private TextField txtDegreesInRotation;
	
	private CCalcController controller;
	
	private Measurments measurments;
	
	public CCalcWindow() {
		
		measurments = new Measurments();  
		controller = new CCalcController();
		controller.registerWindow(this);
			
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		distance.setCellValueFactory(new PropertyValueFactory<Measurment, Float>("distance"));
		direction.setCellValueFactory(new PropertyValueFactory<Measurment, Integer>("direction"));
		id.setCellValueFactory(new PropertyValueFactory<Measurment, Integer>("id"));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CCalcWindow.fxml"));
		Pane p = loader.load();
		primaryStage.setScene(new Scene(p));
		primaryStage.show();	

	}
	
	@FXML
	private void btnInsert(ActionEvent e) {
		if (!(txtDirection.getText().isEmpty() || txtDistance.getText().isEmpty())) {
			if (isNumeric(txtDirection.getText()) && isNumeric(txtDistance.getText())) {
				int direction = Integer.parseInt(txtDirection.getText());
				double distance = Integer.parseInt(txtDistance.getText());
				
				if (!MeasurmentIsSelected()) {
					Measurment newMeasurment = controller.userAddsMeasurment(direction, distance);
					if(newMeasurment != null) {
						table.getItems().add(newMeasurment);
					}
					
				} else {
					int indexOfSelectedMeasurment = measurments.IndexOfMeasurment(getSelectedMeasurment());
					
					controller.userAddsMeasurmentToIndex(indexOfSelectedMeasurment, direction, distance);
					
					table.getItems().clear();
					
					for(Measurment m : measurments.getMeasurments()) {
						table.getItems().add(m);
					}
		
				}
						
				txtDirection.clear();
				txtDistance.clear();
			}
		}
	}
	
	@FXML
	private void btnRemove(ActionEvent e) {
		controller.deleteMeasurment(getSelectedMeasurment());
		controller.onMeasurmentDeleted();
	}
	
	@FXML
	private void btnDeselect(ActionEvent e) {
		table.getSelectionModel().select(null);
	}
	
	@FXML
	private void setDegreesInRotation(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (!(txtDegreesInRotation.getText().isEmpty()) && isNumeric(txtDegreesInRotation.getText())) {
				String degrees = txtDegreesInRotation.getText().replace(" ", "");
				int degreesNum = Integer.parseInt(degrees);
				controller.setDegreesInRotation(degreesNum);
				System.out.println(CordinateCalculator.getDegreesInRotation());
			}
		}
	}
	
	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public TableView<Measurment> getTable() {
		return table;
	}
	
	public Measurment getSelectedMeasurment() {
		return table.getSelectionModel().getSelectedItem();
	}
	
	private boolean MeasurmentIsSelected() {
		if (getSelectedMeasurment() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
	
	
	

}
