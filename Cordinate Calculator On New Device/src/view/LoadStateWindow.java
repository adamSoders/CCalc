package view;

import java.io.File;
import java.io.IOException;

import controller.CCalcController;
import javafx.stage.FileChooser;

public class LoadStateWindow {
	
	CCalcController controller;
	
	public LoadStateWindow(CCalcController controller) throws IOException { // GUI that enables user to load state from files
		this.controller = controller;
		
		// Creates FileChooser window
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load state");
		
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized file", "*.ser"));
		
		File selectedFile = fileChooser.showOpenDialog(null);
		controller.loadFile(selectedFile);
	
		
	}
}
