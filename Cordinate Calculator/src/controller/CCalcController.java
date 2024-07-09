package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.CordinateCalculator;
import model.Measurment;
import model.Measurments;
import view.CCalcWindow;

public class CCalcController {

	private CCalcWindow window;
	private Measurments measurments;

	public CCalcController() {
		
		measurments = new Measurments();
		
	}
	
	public void registerWindow(CCalcWindow window) {
		this.window = window;
	}
	
	public void setDegreesInRotation(int degrees) {
		CordinateCalculator.setDegreesInRotation(degrees);
	}
	
	
	public Measurment userAddsMeasurment(int direction, double distance) {
		if (CordinateCalculator.getDegreesInRotation() > 0 && CordinateCalculator.getDegreesInRotation() != 0) {
			Measurment m = measurments.addMeasurment(direction, distance);
			calculateCordinates(m);
			return m;
		} else {
			System.out.println("Enter degrees in rotation");
			return null;
		}
	}
	
	public Measurment userAddsMeasurmentToIndex(int index, int direction, double distance) {
		
		measurments.getMeasurments().add(null);
		
		for(int i = measurments.getMeasurments().size() - 2; i >= index; i--) {
			measurments.getMeasurments().set(i + 1, measurments.getMeasurments().get(i));
			measurments.getMeasurments().get(i + 1).incrementId();
		}
		
		return measurments.addMeasurmentToIndex(index, direction, distance);
	}
	
	public void calculateCordinates(Measurment m) {
		
		
	}

	public void deleteMeasurment(Measurment selectedMeasurment) {
		measurments.deleteMeasurment(selectedMeasurment);
		// TODO Auto-generated method stub
		
	}

	public void onMeasurmentDeleted() {
		window.getTable().getItems().remove(window.getSelectedMeasurment());
		// TODO Auto-generated method stub
		
	}
	
	
}
