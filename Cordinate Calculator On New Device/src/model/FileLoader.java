package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import controller.CCalcController;

public class FileLoader { // Contains a loadbehaviour that chucks out a state of the program
	
	private LoadBehaviour loadBehaviour;

	public FileLoader() { 
		
	}
	
	public List<Object> loadFile(File selectedFile) {
		return loadBehaviour.getState(selectedFile);
	}
	
	public void setLoadBehaviour(LoadBehaviour l) {
		this.loadBehaviour = l;
	}
}
