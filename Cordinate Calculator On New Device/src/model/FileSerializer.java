package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class FileSerializer implements ExportBehaviour {
	
	public FileSerializer() {

		}
	
		public void exportFile(File selectedFile, Measurements measurements, int degreesInRotation) { // Serializes state by throwing the measurements and degrees in rotation into list
		
			try {
				List<Measurement> serializableMeasurements = new ArrayList<Measurement>();
				for (Measurement m : measurements) {
					serializableMeasurements.add(m);
				}
				
				FileOutputStream fileStream = new FileOutputStream(selectedFile);
				ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
				List<Object> state = new ArrayList<Object>();
				state.add(serializableMeasurements);
				state.add((Integer) degreesInRotation);
				objectStream.writeObject(state);
			
				objectStream.close();
				fileStream.close();
			
			} catch (IOException i) {
		         i.printStackTrace();
			}
		
		
		}
	}
