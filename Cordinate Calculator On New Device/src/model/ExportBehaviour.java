package model;

import java.io.File;

import javafx.collections.ObservableList;

public interface ExportBehaviour { // A way of exporting the state of the program

	public void exportFile(File selectedFile, Measurements measurements, int degreesInRotation);
}
