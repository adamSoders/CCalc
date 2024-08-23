package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window {

	private Stage stageDisplayed;
	// private List<Window> subWindows;
	
	public Window(String fxmlFile) throws IOException { // Creates and displays window from fxml file
		
		// subWindows = new ArrayList<Window>();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
		Pane p = loader.load();
		
		stageDisplayed = new Stage();
		stageDisplayed.setScene(new Scene(p));
		stageDisplayed.show();
		
		/* 
		if (owner != null) {
			owner.addSubWindow(this);
		}
		
		stageDisplayed.setOnCloseRequest((WindowEvent event) -> closeAllSubWindows(this)); // Doesn't work *rage emoji*
		*/
		
	}

	/*
	private void closeAllSubWindows(Window w) { // Recursively closes all subwindows of closed window and deletes them from subwindow list
        for (Window window : w.getSubWindows()) {
            window.getStage().close();  
            closeAllSubWindows(window); 
        }
        w.getSubWindows().clear();  
    }
	
	
	private void addSubWindow(Window window) {
		this.subWindows.add(window);
	}
	
	public List<Window> getSubWindows() {
		return subWindows;
	}
	*/
	
	public Stage getStage() {
		return stageDisplayed;
	}
	
}
