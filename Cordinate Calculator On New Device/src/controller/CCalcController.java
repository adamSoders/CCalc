package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.CordinateCalculator;
import model.ExportBehaviour;
import model.ExportBehaviourFactory;
import model.FileDeserializer;
import model.FileExporter;
import model.FileLoader;
import model.Measurement;
import model.Measurements;
import view.CCalcWindow;

public class CCalcController {

	private CCalcWindow window; 
	private Measurements measurements;
	private FileExporter fileExporter;
	private FileLoader fileLoader;
	private ExportBehaviourFactory exportBehaviourFactory;

	public CCalcController() { 
		
		// Sets up the model of the application
		measurements = new Measurements(); 
		fileExporter = new FileExporter();
		fileLoader = new FileLoader();
		exportBehaviourFactory = new ExportBehaviourFactory(); 
		
	}
	
	public void registerWindow(CCalcWindow window) { 
		this.window = window;
	}
	
	public void setDegreesInRotation(int degrees) {
		CordinateCalculator.setDegreesInRotation(degrees);
	}
	
	
	public Measurement userAddsMeasurement(int direction, double distance) {
		if (CordinateCalculator.degreesHasBeenSet()) {
			Measurement m = measurements.addMeasurement(direction, distance);
			
			return m;
		} else {
			return null;
		}
	}
	
	public Measurement userAddsMeasurementToIndex(int index, int direction, double distance) { 
		
		Measurement m = measurements.addMeasurementToIndex(index - 1, direction, distance);
		
		return m;
		
	}
	
	public double claculateNorthing(Measurement m) {
		return CordinateCalculator.calculateNorthing(m.getDirection(), m.getDistance());
	}
	
	public double calculateEasting(Measurement m) {
		return CordinateCalculator.calculateEasting(m.getDirection(), m.getDistance());
	}
		 

	public Measurement deleteMeasurement(Measurement selectedMeasurement) {
		return measurements.deleteMeasurement(selectedMeasurement);
		
	}

	public void onMeasurementDeleted(Measurement m) { // Removes "vector" of measurement from total, updates table and displays new total
		measurements.changeEasting(-calculateEasting(m));
		measurements.changeNorthing(-claculateNorthing(m));
		window.displayResults(measurements.getEasting(), measurements.getNorthing());
		updateTable();
		
		
	}
	
	public void onDegreesChanged() { 
		for (Measurement m : measurements.getMeasurements()) { // Updates direction of measurements
			m.setDirection(m.getOgDirection() % CordinateCalculator.getDegreesInRotation());
		
		}
		
		// Recalculates total easting and northing with new directions and degrees in rotation, displays result and updates table
		measurements.setEasting(CordinateCalculator.calculateTotalEasting(measurements.getMeasurements()));
		measurements.setNorthing(CordinateCalculator.calculateTotalNorthing(measurements.getMeasurements()));
		window.displayResults(measurements.getEasting(), measurements.getNorthing());
		updateTable();
	}
	
	public void onMeasurementAdded(Measurement m) { // Adds new vector and displays result
		measurements.changeEasting(calculateEasting(m));
		measurements.changeNorthing(claculateNorthing(m));
		window.displayResults(measurements.getEasting(), measurements.getNorthing());
		updateTable();
	}
	
	private void updateTable() { // Updates the table with a fresh set of measurements in an ObservableList
		window.getTable().setItems(this.measurements.getMeasurements());
		window.getTable().refresh();
	}
	
	public Measurements getMeasurements() {
		return this.measurements;
	}

	public void saveFile(File selectedFile) throws IOException { // Sets exportbehaviour and exports file
		if (selectedFile != null) {
			fileExporter.setExportBehaviour(exportBehaviourFactory.createExportBehaviour(selectedFile));
			fileExporter.exportFile(selectedFile, measurements, CordinateCalculator.getDegreesInRotation());
		}

		
		
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements.setMeasurements(measurements);
		
	}
	
	public void onStateLoaded() { // Updates displayed state by setting the degrees, calculates new vectors and displays the measurements
		window.setDegreesInField(CordinateCalculator.getDegreesInRotation());
		
		measurements.setEasting(CordinateCalculator.calculateTotalEasting(measurements.getMeasurements()));
		measurements.setNorthing(CordinateCalculator.calculateTotalNorthing(measurements.getMeasurements()));
		window.displayResults(measurements.getEasting(), measurements.getNorthing());
		updateTable();
	}

	public void loadFile(File selectedFile) throws IOException { // Extracts state from serialized file
		
		if (selectedFile != null) {
			if (selectedFile.getName().endsWith(".ser")) {
				fileLoader.setLoadBehaviour(new FileDeserializer()); 
				// Add shit if you'd like
			} else {
					showFeedBackInWindow("Unsupported file extension");
				throw new IOException("Unsupported file extension");
				
			}
			
			List<Object> state = fileLoader.loadFile(selectedFile);
			
			 if (state != null && !state.isEmpty()) {
		            setState(state);
		        } else {
		            showFeedBackInWindow("Failed to load state. The file might be corrupted or empty.");
		        }
		} else {
			window.showFeedback("no file selected");
		}
		
		
		
	}

	public void setState(List<Object> state) { // Sets state of program from list of serialized state and updates window
        if (state == null || state.size() < 2) {
        	showFeedBackInWindow("Invalid state data");
            throw new IllegalArgumentException("Invalid state data");
        }

        List<Measurement> measurements = (ArrayList<Measurement>) state.get(0);
        int degreesInRotation = (int) state.get(1);

        setMeasurements(measurements);
        setDegreesInRotation(degreesInRotation);
		onStateLoaded();
	}

	public void showFeedBackInWindow(String feedback) {
		window.showFeedback(feedback);
	}

	public boolean degreesHasBeenSet() {
		return CordinateCalculator.degreesHasBeenSet();
	}
	
	
	
	
}
