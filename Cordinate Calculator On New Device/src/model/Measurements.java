package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;

public class Measurements implements Iterable<Measurement>, Serializable { // Manages list of measurements and their total difference eastwards and norhowards

	private ObservableList<Measurement> measurements; 
	private int count; // keeps track of what id to give added measurement
	private double easting;
	private double northing;
	
	public Measurements() {
		
		measurements = FXCollections.observableArrayList();
		count = 1;
	}
	
	public Measurement addMeasurement(int direction, double distance) {
			Measurement m = new Measurement(direction, distance, count);
			m.setDirection(m.getDirection() % CordinateCalculator.getDegreesInRotation()); // starts over if reached DegreesInRotation 
			measurements.add(m);
			count++;
			return m;
		
	}
	
	public Measurement deleteMeasurement(Measurement m) {
		if (m != null) {
			decrementIds(m); 
			measurements.remove(m);
			count--;
			return m;
		}
		
		else return null;
		
	}
	
	private void decrementIds(Measurement selectedM) { // Decrements id's of measurements after specific measurement in list
		for (Measurement m : measurements) {
			if (m.getId() > selectedM.getId()) {
				m.decrementId();
			}
		}
	}
	

	@Override
	public Iterator<Measurement> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int IndexOfMeasurment(Measurement m) {
		int i = 0;
		for(Measurement measurment : measurements) {
			if(m.equals(measurment)) {
				return i;
			}
			
			i++;
		}
		
		return -1;
	}
	
	public ObservableList<Measurement> getMeasurements() {
		return measurements;
	}

	public Measurement addMeasurementToIndex(int index, int direction, double distance) {
		// TODO Auto-generated method stub
		Measurement m = new Measurement(direction, distance, index + 1);
		m.setDirection(m.getDirection() % CordinateCalculator.getDegreesInRotation());
		
		/* Adds empty slot to measurements, each measurement starting from the end until the one in the slot where 
		 * new measurement is to be added, is moved one index forwards and their id's are incremented correspondingly
		 */
		measurements.add(null);
		
		for (int i = measurements.size() - 2; i >= index; i--) {
			measurements.get(i).incrementId();
			measurements.set(i+1, measurements.get(i));	
		}
		
		measurements.set(index, m);
		count++;
		return m;
	}
	
	public double getEasting() {
		return this.easting;
	}
	
	public void setEasting(double easting) {
		this.easting = easting;
	}
	
	public double getNorthing() {
		return this.northing;
	}
	
	public void setNorthing(double northing) { 
		this.northing = northing;
	}
	
	public void changeEasting(double differance) {
		this.easting += differance;
	}
	
	public void changeNorthing(double differance) {
		this.northing += differance;
	}

	public void setMeasurements(List<Measurement> measurements) {
		for (Measurement m : measurements)
		this.measurements.add(m);
		
	}
	
}
