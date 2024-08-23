package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class FileDeserializer implements LoadBehaviour {

	@Override
	public List<Object> getState(File selectedFile) { // Extracts state from serialized file
		// TODO Auto-generated method stub
		try {
			List<Object> state;
	         FileInputStream fileStream = new FileInputStream(selectedFile);
	         ObjectInputStream objectStream = new ObjectInputStream(fileStream);
	         state = (List<Object>) objectStream.readObject();
	         objectStream.close();
	         fileStream.close();
	         return state;
	         
		} catch (IOException i) {
	         i.printStackTrace();
	         return null;
	         
	    } catch (ClassNotFoundException c) {
	    	 c.printStackTrace();
	    	 return null;
	      }
	}

}
