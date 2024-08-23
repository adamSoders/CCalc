package model;

import java.io.File;

import javafx.collections.ObservableList;

public class FileExporter {

	private ExportBehaviour exportBehaviour;
	
	public FileExporter() { // exportbehaviour exports state to file
		
	}
	
	public void exportFile(File selectedFile, Measurements measurements, int degreesInRotation) {
		exportBehaviour.exportFile(selectedFile, measurements, degreesInRotation);
	}
	
	public void setExportBehaviour(ExportBehaviour e) {
		this.exportBehaviour = e;
	}
}
