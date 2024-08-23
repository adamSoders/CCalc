package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;

public class HtmlEXporter implements ExportBehaviour {
	
	BufferedWriter buf;

	@Override
	public void exportFile(File selectedFile, Measurements measurements, int degreesInRotation) {
		try {
			buf = new BufferedWriter(new FileWriter(selectedFile));
			writeStateToFile(measurements, degreesInRotation);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	} 
	
	private void writeStateToFile(Measurements measurements, int degreesInRotation) {
		try {
			buf.write("degrees in rotation: " + degreesInRotation +"<br>");
			buf.write("total easting: " + measurements.getEasting() + ", total northing: " + measurements.getNorthing() + "<br>");
			for (Measurement m : measurements.getMeasurements()) {
				buf.write("id: " + m.getId() + ", dir: " + m.getDirection() + ", dist: " + m.getDistance() + "<br>");
			} 
			buf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
