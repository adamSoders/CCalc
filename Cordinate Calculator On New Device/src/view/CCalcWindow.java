package view;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.CCalcController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.CordinateCalculator;
import model.Measurement;
import model.Measurements;

public class CCalcWindow extends Application implements Initializable { // Main window of application

	// Columns in TableView that display id, direction and distance of each measurement
	
	@FXML
	private TableColumn<Measurement, Integer> direction; 

	@FXML
	private TableColumn<Measurement, Float> distance;

	@FXML
	private TableColumn<Measurement, Integer> id;

	@FXML
	private TableView<Measurement> table;
	
	// TextFields for user input of direction, distance and degrees in rotation
	
	@FXML
	private TextField txtDirection; 
	
	@FXML
	private TextField txtDistance;
	
	@FXML
	private TextField txtDegreesInRotation;
	
	@FXML 
	private Button btnSetDegrees; // Button that sets degrees found in textfield
	
	@FXML
	private Text systemFeedback; // Text in bottom left corner that shows system feedback, ex. errors, degrees set etc..
	
	// Text that show total difference eastwards and northwards
	
	@FXML
	private Text easting;
	
	@FXML
	private Text northing;
	
	// Buttons that allow saving of current state and loading of state
	
	@FXML
	private Button saveStateButton;
	
	@FXML
	private Button loadStateButton;
	
	@FXML
	private Button helpButton; // Opens help menu
	
	private CCalcController controller;
	
	private static DecimalFormat df; // Doubles are shown with two decimals
	
	private Window mainWindow; // exists due to failed attempt at closing subwindows once owner window is closed
	
	// private HelpMenuWindow helpMenuWindow; // A subwindow to main window
	
	public CCalcWindow() {
		 
		// Controller (CMV) is created, current window is registered as the window that the controller updates, 
		// Decimalformat is set up
		controller = new CCalcController();
		controller.registerWindow(this);
		df = new DecimalFormat("0.0");
			
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // Sets up columns in table
		// TODO Auto-generated method stub
		distance.setCellValueFactory(new PropertyValueFactory<Measurement, Float>("distance"));
		direction.setCellValueFactory(new PropertyValueFactory<Measurement, Integer>("direction"));
		id.setCellValueFactory(new PropertyValueFactory<Measurement, Integer>("id"));
	}

	@Override
	public void start(Stage primaryStage) throws Exception { // Failed attempt at closing subwindows once owner window is closed, might update later..
		// TODO Auto-generated method stub
		mainWindow = new Window("CCalcWindow.fxml");
		

	}

	@FXML
	private void btnInsert(ActionEvent e) { // Inserts measurement into system
		if (controller.degreesHasBeenSet()) { // Checks if user has set degrees
			if (!(txtDirection.getText().isEmpty() || txtDistance.getText().isEmpty())) { // Checks if both fields contain something
				if (isNumeric(txtDirection.getText()) && isNumeric(txtDistance.getText())) { // Checks if both fields contain numeric values
					// Converts user input into direction and distance of measurement
					int direction = Integer.parseInt(txtDirection.getText());
					double distance = Integer.parseInt(txtDistance.getText());
					
					if (!MeasurementIsSelected()) { // Adds Measurement last to list if no measurement selected
						controller.onMeasurementAdded(controller.userAddsMeasurement(direction, distance));
			
						} else { // Adds Measurement above selected measurement
						
						int indexOfSelectedMeasurement = getSelectedMeasurement().getId();
						controller.onMeasurementAdded(controller.userAddsMeasurementToIndex(indexOfSelectedMeasurement, direction, distance));
		
		 			}
					
					// Clears user input if successfully added
					txtDirection.clear();
					txtDistance.clear();
					
				// Shows feedback if something went wrong
				} else {
					showFeedback("enter numeric value");
				}
				
				
			} else {
				showFeedback("enter direction and distance");
			}
			
		} else {
			showFeedback("enter degrees in rotation");
		}
		
	}
	
	@FXML
	private void btnRemove(ActionEvent e) { // Removes selected measurement
		Measurement m = controller.deleteMeasurement(getSelectedMeasurement());
		controller.onMeasurementDeleted(m); 

	}
	
	@FXML
	private void btnDeselect(ActionEvent e) { // Deselects measurement
		table.getSelectionModel().select(null);
	}
	
	@FXML
	private void btnSetDegreesInRotation(ActionEvent event) { // Sets degrees in rotation
		if (!(txtDegreesInRotation.getText().isEmpty()) && isNumeric(txtDegreesInRotation.getText())) { // Checks if user input is numeric
			String degrees = txtDegreesInRotation.getText().replace(" ", ""); // Removes spaces
			int degreesNum = Integer.parseInt(degrees);
			controller.setDegreesInRotation(degreesNum); // Controller updates model
			showFeedback("Degrees set: " + CordinateCalculator.getDegreesInRotation()); // Shows that degrees has been changed
			controller.onDegreesChanged();

		}
		
		else {
			showFeedback("enter numeric value");
		}
	}
	
	@FXML
	private void btnSaveState(ActionEvent event) { // Ork int kommenter någå mer...
		SaveStateWindow saveWindow = new SaveStateWindow(this.controller);
		
	}
	
	@FXML
	private void btnLoadState(ActionEvent event) throws IOException {
		LoadStateWindow loadStateWindow = new LoadStateWindow(this.controller);
	}
	
	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	/*
	@FXML
	private void btnOpenHelpMenu(ActionEvent event) throws IOException { 
		helpMenuWindow = new HelpMenuWindow();
		
	}
	*/
	
	public TableView<Measurement> getTable() {
		return table;
	}
	
	public void setTable(ObservableList<Measurement> measurements) {
		this.table.setItems(measurements);
	}
	
	public Measurement getSelectedMeasurement() {
		return table.getSelectionModel().getSelectedItem();
	}
	
	private boolean MeasurementIsSelected() {
		if (getSelectedMeasurement() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void showFeedback(String feedback) { // Displays feedback text for 2 seconds
		systemFeedback.setText(feedback);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), 
				ae -> systemFeedback.setText("")));
		timeline.play();
	
	}
	
	public void displayResults(double e, double n) {
		
		easting.setText("ΔE: " + df.format(e));
		northing.setText("ΔN: " + df.format(n));
	}

	public void setDegreesInField(int degreesInRotation) {
		// TODO Auto-generated method stub
		txtDegreesInRotation.setText(Integer.toString(degreesInRotation));
	}
	
	
	 
}
