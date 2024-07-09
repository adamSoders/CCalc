package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Measurments implements Iterable<Measurment>{

	private List<Measurment> measurments;
	private int count;
	
	public Measurments() {
		
		measurments = new ArrayList<Measurment>();
		count = 1;
	}
	
	public Measurment addMeasurment(int direction, double distance) {
			Measurment m = new Measurment(direction, distance, count);
			m.setDirection(m.getDirection() % CordinateCalculator.getDegreesInRotation());
			measurments.add(m);
			count++;
			return m;
		
	}
	
	public void deleteMeasurment(Measurment m) {
		if (m != null) {
			decrementIds(m);
			measurments.remove(m);
			count--;
		}
		
	}
	
	private void decrementIds(Measurment selectedM) {
		for (Measurment m : measurments) {
			if (m.getId() > selectedM.getId()) {
				m.decrementId();
			}
		}
	}
	

	@Override
	public Iterator<Measurment> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int IndexOfMeasurment(Measurment m) {
		int i = 0;
		for(Measurment measurment : measurments) {
			if(m.equals(measurment)) {
				return i;
			}
			
			i++;
		}
		
		return -1;
	}
	
	public List<Measurment> getMeasurments() {
		return measurments;
	}

	public Measurment addMeasurmentToIndex(int index, int direction, double distance) {
		// TODO Auto-generated method stub
		Measurment m = new Measurment(direction, distance, index + 1);
		m.setDirection(m.getDirection() % CordinateCalculator.getDegreesInRotation());
		measurments.set(index, m);
		count++;
		return m;
	}
}
