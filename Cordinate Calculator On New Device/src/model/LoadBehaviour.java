package model;

import java.io.File;
import java.util.List;

public interface LoadBehaviour { // Implemented by different loadbehaviours that return a state of the program

	public List<Object> getState(File selectedFile);

}
