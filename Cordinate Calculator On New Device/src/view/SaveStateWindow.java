package view;

import java.io.File;
import java.io.IOException;

import controller.CCalcController;
import javafx.stage.FileChooser;

public class SaveStateWindow {

		CCalcController controller;
		
		
		public SaveStateWindow(CCalcController controller) { // GUI that enables user to save state to serialized files
			this.controller = controller;
			
			// Creates FileChooser window
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save state");
			
			fileChooser.getExtensionFilters().addAll( // Only saved as .ser or .htm at the moment
				     new FileChooser.ExtensionFilter("Serialized file", "*.ser"), 
				     new FileChooser.ExtensionFilter("HTML file", "*.htm"));
			
			File selectedFile = fileChooser.showSaveDialog(null);
			
			try {
				controller.saveFile(selectedFile); // If user exports file, the task is first delegated to the controller
			} catch(IOException e) {
				controller.showFeedBackInWindow("an error occured while saving file");
			}
				
			
		}
		
	}

